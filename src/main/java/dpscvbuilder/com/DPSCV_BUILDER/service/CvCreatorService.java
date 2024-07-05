package dpscvbuilder.com.DPSCV_BUILDER.service;


import dpscvbuilder.com.DPSCV_BUILDER.dto.CvCreatorDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.SystemUserDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.CvCreatorRegisterDto;

public interface CvCreatorService {
    SystemUserDto register(CvCreatorRegisterDto registerDto);

}
