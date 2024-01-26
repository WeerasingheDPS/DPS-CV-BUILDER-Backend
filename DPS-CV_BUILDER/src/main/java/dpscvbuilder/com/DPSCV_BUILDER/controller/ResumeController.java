package dpscvbuilder.com.DPSCV_BUILDER.controller;


import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.CvCreatorMainContentDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.CvCreatorPersonalDataRequestDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.ResumeDto;
import dpscvbuilder.com.DPSCV_BUILDER.model.Resume;
import dpscvbuilder.com.DPSCV_BUILDER.service.ResumeService;
import dpscvbuilder.com.DPSCV_BUILDER.util.constant.UrlConstant;
import dpscvbuilder.com.DPSCV_BUILDER.util.response.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = UrlConstant.V1_RESUME)
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/personal_data/{userId}")
    public ResponseEntity<StandardResponse> savePersonalData(@PathVariable String userId, @RequestBody CvCreatorPersonalDataRequestDto personalDataRequestDto) {
        String message = resumeService.savePersonalData(personalDataRequestDto, userId);
        return new ResponseEntity<StandardResponse>(
                StandardResponse
                        .builder()
                        .result(message)
                        .success(true)
                        .build(), HttpStatus.CREATED
        );
    }

    @PostMapping("/main_contents/{userId}")
    public ResponseEntity<StandardResponse> saveMainContentData(@PathVariable String userId, @RequestBody List<CvCreatorMainContentDto> mainContentDtos) {
        String message = resumeService.saveMainContentData(mainContentDtos, userId);
        return new ResponseEntity<StandardResponse>(
                StandardResponse
                        .builder()
                        .result(message)
                        .success(true)
                        .build(), HttpStatus.CREATED
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<StandardResponse> getResume(@PathVariable String userId) {
     Resume resume = resumeService.getResume(userId);
        return new ResponseEntity<StandardResponse>(
                StandardResponse
                        .builder()
                        .result(resume)
                        .success(true)
                        .build(), HttpStatus.OK
        );
    }
}
