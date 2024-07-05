package dpscvbuilder.com.DPSCV_BUILDER.controller;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.ChangePasswordRequestDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.LoginRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.RefreshTokenRequest;
import dpscvbuilder.com.DPSCV_BUILDER.dto.response.LoginResponseDto;
import dpscvbuilder.com.DPSCV_BUILDER.service.AuthService;
import dpscvbuilder.com.DPSCV_BUILDER.util.response.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping( "/login")
    public ResponseEntity<StandardResponse> loginUser(@RequestBody LoginRequest loginRequest){
        LoginResponseDto loginResponse = authService.login(loginRequest);
        return new ResponseEntity<StandardResponse>(
                StandardResponse
                        .builder()
                        .result(loginResponse)
                        .success(true)
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping( "/refresh_token")
    public ResponseEntity<StandardResponse> refreshToken(@RequestBody RefreshTokenRequest refreshToken){
        String token = authService.refreshToken(refreshToken);
        return new ResponseEntity<StandardResponse>(
                StandardResponse
                        .builder()
                        .result(token)
                        .success(true)
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping( "/change_password/{userId}")
    public ResponseEntity<StandardResponse> changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequest, @PathVariable String userId){
        String message = authService.changePassword(changePasswordRequest, userId);
        return new ResponseEntity<StandardResponse>(
                StandardResponse
                        .builder()
                        .result(message)
                        .success(true)
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping( "/confirm")
    public ResponseEntity<String> verifyEmail(@RequestParam String token ){
        String message = authService.verifyEmail(token);
        return new ResponseEntity<>(
                message,
                HttpStatus.OK);
    }
}
