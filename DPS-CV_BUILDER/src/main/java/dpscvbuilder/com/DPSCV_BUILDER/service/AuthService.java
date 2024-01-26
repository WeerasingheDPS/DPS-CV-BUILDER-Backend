package dpscvbuilder.com.DPSCV_BUILDER.service;


import dpscvbuilder.com.DPSCV_BUILDER.dto.request.LoginRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.RefreshTokenRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.response.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequest loginRequest);

    String refreshToken(RefreshTokenRequest refreshToken);
}
