package dpscvbuilder.com.DPSCV_BUILDER.service;


import dpscvbuilder.com.DPSCV_BUILDER.dto.request.LoginRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.response.LoginResponseDto;
import dpscvbuilder.com.DPSCV_BUILDER.model.SystemUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    String genarateToken(String email);


    void validateToken(String token, UserDetails userDetails);

    SystemUser getUser(String email);

    LoginResponseDto login(LoginRequest loginRequest);
}
