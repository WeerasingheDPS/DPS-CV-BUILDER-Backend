package dpscvbuilder.com.DPSCV_BUILDER.controller;

import dpscvbuilder.com.DPSCV_BUILDER.dto.CvCreatorDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.CvCreatorRegisterDto;
import dpscvbuilder.com.DPSCV_BUILDER.service.CvCreatorService;
import dpscvbuilder.com.DPSCV_BUILDER.util.constant.UrlConstant;
import dpscvbuilder.com.DPSCV_BUILDER.util.response.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = UrlConstant.V1_CV_CREATOR)
public class CvCreatorController {

    private final CvCreatorService cvCreatorService;

    @PostMapping("/register")
    public ResponseEntity<StandardResponse> register(@RequestBody CvCreatorRegisterDto registerDto) {
        CvCreatorDto cvCreator = cvCreatorService.register(registerDto);
        return new ResponseEntity<StandardResponse>(
                StandardResponse
                        .builder()
                        .result(cvCreator)
                        .success(true)
                        .build(), HttpStatus.CREATED
        );
    }

}
