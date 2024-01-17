package dpscvbuilder.com.DPSCV_BUILDER.service;


import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.CvCreatorMainContentDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.CvCreatorPersonalDataRequestDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.ResumeDto;

import java.util.List;

public interface ResumeService {
    String savePersonalData(CvCreatorPersonalDataRequestDto personalDataRequestDto, String id);

    String saveMainContentData(List<CvCreatorMainContentDto> mainContentDtos, String id);

    ResumeDto getResume(String userId);
}
