package dpscvbuilder.com.DPSCV_BUILDER.service.impl;

import dpscvbuilder.com.DPSCV_BUILDER.dto.SystemUserDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.CvCreatorRegisterDto;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DpsCvBuilderException;
import dpscvbuilder.com.DPSCV_BUILDER.model.ConfirmationToken;
import dpscvbuilder.com.DPSCV_BUILDER.model.CvCreator;
import dpscvbuilder.com.DPSCV_BUILDER.model.SystemUser;
import dpscvbuilder.com.DPSCV_BUILDER.repository.ConfirmationTokenRepo;
import dpscvbuilder.com.DPSCV_BUILDER.repository.CvCreatorRepo;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import dpscvbuilder.com.DPSCV_BUILDER.service.CvCreatorService;
import dpscvbuilder.com.DPSCV_BUILDER.service.EmailService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CvCreatorServiceImpl implements CvCreatorService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final CvCreatorRepo cvCreatorRepo;

    private final ConfirmationTokenRepo confirmationTokenRepo;

    private final EmailService emailService;
    @Override
    public SystemUserDto register(CvCreatorRegisterDto registerDto) {
        if(userRepo.existsByEmailAndUserType(registerDto.getEmail(), "ROLE_" + UserType.CV_CREATOR)){
            throw new DpsCvBuilderException(ErrorEnum.ERROR_DUPLICATE_EMAIL,
                    "You are already registered with this email: "
                            + registerDto.getEmail())
                    ;
        }
        // save system user
        SystemUser systemUser = new SystemUser();
        systemUser.setEmail(registerDto.getEmail());
        systemUser.setUserType("ROLE_" + UserType.CV_CREATOR);
        systemUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        SystemUser savedSystemUser = userRepo.save(systemUser);

        //save cv creator
        CvCreator cvCreator = new CvCreator();
        cvCreator.setEmail(registerDto.getEmail());
        cvCreator.setUserType("ROLE_" + UserType.CV_CREATOR);
        cvCreatorRepo.save(cvCreator);

        SystemUserDto systemUserDto = modelMapper.map(savedSystemUser, SystemUserDto.class);
        ConfirmationToken confirmationToken = createConfirmationToken(savedSystemUser.getEmail());
        confirmationTokenRepo.save(confirmationToken);
        System.out.println(confirmationToken.toString());

        //send verify email
        sendAccountVerificationEmail(confirmationToken);
        return systemUserDto;
    }

    private void sendAccountVerificationEmail(ConfirmationToken confirmationToken) {
        try {
            emailService.sendVerificationEmail(confirmationToken);
        }catch (Exception ex){
            log.info("Error sending verification email for : {} error-> {}", confirmationToken.getUserEmail(), ex.getMessage());
        }

    }

    private ConfirmationToken createConfirmationToken(String email) {

        String token = UUID.randomUUID().toString();
        return ConfirmationToken.builder()
                .token(token)
                .userEmail(email)
                .createdAt(LocalDateTime.now())
                .used(false)
                .build();
    }

}
