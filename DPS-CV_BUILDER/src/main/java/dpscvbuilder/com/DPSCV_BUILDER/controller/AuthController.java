package dpscvbuilder.com.DPSCV_BUILDER.controller;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.LoginRequest;
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
}
