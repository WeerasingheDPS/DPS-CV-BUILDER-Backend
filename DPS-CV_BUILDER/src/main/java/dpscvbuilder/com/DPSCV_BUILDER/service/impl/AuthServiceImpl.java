package dpscvbuilder.com.DPSCV_BUILDER.service.impl;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.LoginRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.response.LoginResponseDto;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DreamHireException;
import dpscvbuilder.com.DPSCV_BUILDER.model.SystemUser;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import dpscvbuilder.com.DPSCV_BUILDER.service.AuthService;
import dpscvbuilder.com.DPSCV_BUILDER.service.JwtService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String genarateToken(String email) {
        return jwtService.generateToken(email);
    }

    @Override
    public void validateToken(String token, UserDetails userDetails) {
        jwtService.validateToken(token, userDetails);
    }

    @Override
    public SystemUser getUser(String email) {
        return userRepo.findByEmail(email).get();
    }

    @Override
    public LoginResponseDto login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = genarateToken(loginRequest.getEmail());
            SystemUser user = getUser(loginRequest.getEmail());
            return new LoginResponseDto(token, user);
        }else throw new DreamHireException(ErrorEnum.ERROR_INVALID_EMAIL_OR_PASSWORD,
                "Invalid email or password!");

    }
}
