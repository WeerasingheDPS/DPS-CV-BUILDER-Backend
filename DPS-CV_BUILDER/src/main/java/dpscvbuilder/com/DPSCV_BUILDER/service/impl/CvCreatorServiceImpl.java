package dpscvbuilder.com.DPSCV_BUILDER.service.impl;

import dpscvbuilder.com.DPSCV_BUILDER.dto.CvCreatorDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.CvCreatorRegisterDto;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DreamHireException;
import dpscvbuilder.com.DPSCV_BUILDER.model.CvCreator;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import dpscvbuilder.com.DPSCV_BUILDER.service.CvCreatorService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.UserType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CvCreatorServiceImpl implements CvCreatorService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CvCreatorDto register(CvCreatorRegisterDto registerDto) {
        if(userRepo.existsByEmailAndUserType(registerDto.getEmail(), "ROLE_" + UserType.CV_CREATOR)){
            throw new DreamHireException(ErrorEnum.ERROR_DUPLICATE_EMAIL,
                    "You are already registered with this email: " +
                            registerDto.getEmail());
        }
        CvCreator cvCreator = new CvCreator();
        cvCreator.setEmail(registerDto.getEmail());
        cvCreator.setUserType("ROLE_" + UserType.CV_CREATOR);
        cvCreator.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        CvCreator savedCvCreator = userRepo.save(cvCreator);
        CvCreatorDto cvCreatorDto = modelMapper.map(savedCvCreator, CvCreatorDto.class);
        return cvCreatorDto;
    }

}
