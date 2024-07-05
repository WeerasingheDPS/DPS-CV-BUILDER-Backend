package dpscvbuilder.com.DPSCV_BUILDER.service.impl;
import dpscvbuilder.com.DPSCV_BUILDER.config.security.CustomUserDetailsService;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.ChangePasswordRequestDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.LoginRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.RefreshTokenRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.response.LoginResponseDto;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DpsCvBuilderException;
import dpscvbuilder.com.DPSCV_BUILDER.model.ConfirmationToken;
import dpscvbuilder.com.DPSCV_BUILDER.model.SystemUser;
import dpscvbuilder.com.DPSCV_BUILDER.repository.ConfirmationTokenRepo;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import dpscvbuilder.com.DPSCV_BUILDER.service.AuthService;
import dpscvbuilder.com.DPSCV_BUILDER.service.JwtService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final JwtService jwtService;

    private final UserRepo userRepo;

    private final ConfirmationTokenRepo tokenRepo;

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    @Value("${email.verification.expire.duration}")
    private int expireValue;

    @Override
    public LoginResponseDto login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String accessToken = generateAccessToken(loginRequest.getEmail());
            String refreshToken = generateRefreshToken(loginRequest.getEmail());
            SystemUser systemUser = getUser(loginRequest.getEmail());
            return new LoginResponseDto(accessToken, refreshToken, systemUser);
        }else throw new DpsCvBuilderException(ErrorEnum.ERROR_INVALID_EMAIL_OR_PASSWORD,
                "Invalid email or password!");

    }


    public String refreshToken(RefreshTokenRequest refreshToken) {
        String email = jwtService.extractUsername(refreshToken.getRefreshToken());
        if(jwtService.validateToken(refreshToken.getRefreshToken(), userDetailsService.loadUserByUsername(email))){
            String token = jwtService.generateAccessToken(email);
            return token;
        }
        return null;
    }

    public String changePassword(ChangePasswordRequestDto changePasswordRequest, String userId) {
        if(userRepo.existsById(userId)){
            SystemUser systemUser = userRepo.findById(userId).get();
            if(passwordEncoder.matches(changePasswordRequest.getOldPassword(), systemUser.getPassword())){
                systemUser.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                userRepo.save(systemUser);
                return "Password is changed successfully";
            }else{
                throw new DpsCvBuilderException(ErrorEnum.ERROR_INVALID_PASSWORD, "Password is incorrect! Try again!");
            }
        }else {
            throw new DpsCvBuilderException(ErrorEnum.ERROR_NOT_FOUND, "User is not found with id:" + userId);
        }
    }

    @Override
    public String verifyEmail(String token) {
        Optional<ConfirmationToken> optional = tokenRepo.findByToken(token);
        if (optional.isEmpty()){
            return "Email Verification is failed!, Please Try again!";
        }else if (LocalDateTime.now().plusMinutes(expireValue).isAfter(optional.get().getCreatedAt()) && !optional.get().getUsed()) {
            //update the confirmation token
            ConfirmationToken confirmationToken = optional.get();
            confirmationToken.setUsed(true);
            tokenRepo.save(confirmationToken);

            //update system user
            Optional<SystemUser> optionalUser = userRepo.findByEmail(confirmationToken.getUserEmail());
            SystemUser systemUser = optionalUser.get();
            systemUser.setEnabled(true);
            userRepo.save(systemUser);

            return "Your Email is Verified. Please login";
        }else {
            return "Email Verification is failed! Token has been already used, Please Try again!";
        }
    }


    private String generateAccessToken(String email) {
        return jwtService.generateAccessToken(email);
    }

    private SystemUser getUser(String email) {
        return userRepo.findByEmail(email).get();
    }

    private String generateRefreshToken(String token){
        String refreshToken = jwtService.generateRefreshToken(token);
        return refreshToken;
    }
}
