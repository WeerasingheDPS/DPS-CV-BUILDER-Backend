package dpscvbuilder.com.DPSCV_BUILDER.service.impl;

import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.CvCreatorMainContentDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.CvCreatorPersonalDataRequestDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.ResumeDto;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DreamHireException;
import dpscvbuilder.com.DPSCV_BUILDER.model.Resume;
import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.MainContent;
import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.PersonalData;
import dpscvbuilder.com.DPSCV_BUILDER.repository.ResumeRepo;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import dpscvbuilder.com.DPSCV_BUILDER.service.ResumeService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ResumeRepo resumeRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String savePersonalData(CvCreatorPersonalDataRequestDto personalDataRequestDto, String id) {
        if(userRepo.existsById(id)){
            if(resumeRepo.existsByUserId(id)){
                Resume resume = resumeRepo.findByUserId(id);
                resume.setPersonalData(modelMapper.map(personalDataRequestDto, PersonalData.class));
                resumeRepo.save(resume);
            }else {
                Resume newResume = new Resume();
                newResume.setPersonalData(modelMapper.map(personalDataRequestDto, PersonalData.class));
                newResume.setUserId(id);
                resumeRepo.save(newResume);
            }
            return "Saved Successfully";
        }else throw new DreamHireException(ErrorEnum.ERROR_NOT_FOUND, "User is Not_Found with userId: " + id);

    }

    @Override
    public String saveMainContentData(List<CvCreatorMainContentDto> mainContentDtos, String id) {
        if(userRepo.existsById(id)){
            if(resumeRepo.existsByUserId(id)){
                Resume resume = resumeRepo.findByUserId(id);
                resume.setMainContents(mapToMainContentList(mainContentDtos));
                resumeRepo.save(resume);
            }else {
                Resume newResume = new Resume();
                newResume.setMainContents(mapToMainContentList(mainContentDtos));
                newResume.setUserId(id);
                resumeRepo.save(newResume);
            }
            return "Saved Successfully";
        }else throw new DreamHireException(ErrorEnum.ERROR_NOT_FOUND, "User is Not_Found with userId: " + id);
    }

    @Override
    public Resume getResume(String userId) {
        Resume resume = resumeRepo.findByUserId(userId);
        //ResumeDto resumeDto = modelMapper.map(resume, ResumeDto.class);
        return resume;
    }

    private List<MainContent> mapToMainContentList(List<CvCreatorMainContentDto> mainContentDtos) {
        return modelMapper.map(mainContentDtos, new TypeToken<List<MainContent>>() {}.getType());
    }
}
