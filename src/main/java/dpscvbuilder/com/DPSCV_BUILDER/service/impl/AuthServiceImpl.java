package dpscvbuilder.com.DPSCV_BUILDER.service.impl;
import dpscvbuilder.com.DPSCV_BUILDER.config.security.CustomUserDetailsService;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.ChangePasswordRequestDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.LoginRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.RefreshTokenRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.response.LoginResponseDto;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DreamHireException;
import dpscvbuilder.com.DPSCV_BUILDER.model.User;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import dpscvbuilder.com.DPSCV_BUILDER.service.AuthService;
import dpscvbuilder.com.DPSCV_BUILDER.service.JwtService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String accessToken = generateAccessToken(loginRequest.getEmail());
            String refreshToken = generateRefreshToken(loginRequest.getEmail());
            User user = getUser(loginRequest.getEmail());
            return new LoginResponseDto(accessToken, refreshToken, user);
        }else throw new DreamHireException(ErrorEnum.ERROR_INVALID_EMAIL_OR_PASSWORD,
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
            User user = userRepo.findById(userId).get();
            if(passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())){
                user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
                userRepo.save(user);
                return "Password is changed successfully";
            }else{
                throw new DreamHireException(ErrorEnum.ERROR_INVALID_PASSWORD, "Password is incorrect! Try again!");
            }
        }else {
            throw new DreamHireException(ErrorEnum.ERROR_NOT_FOUND, "User is not found with id:" + userId);
        }
    }


    private String generateAccessToken(String email) {
        return jwtService.generateAccessToken(email);
    }

    private User getUser(String email) {
        return userRepo.findByEmail(email).get();
    }

    private String generateRefreshToken(String token){
        String refreshToken = jwtService.generateRefreshToken(token);
        return refreshToken;
    }
}
