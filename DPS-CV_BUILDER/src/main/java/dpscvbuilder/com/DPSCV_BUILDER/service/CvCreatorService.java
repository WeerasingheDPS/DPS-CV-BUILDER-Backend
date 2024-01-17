package dpscvbuilder.com.DPSCV_BUILDER.service;


import dpscvbuilder.com.DPSCV_BUILDER.dto.CvCreatorDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.CvCreatorRegisterDto;

public interface CvCreatorService {
    CvCreatorDto register(CvCreatorRegisterDto registerDto);

}
