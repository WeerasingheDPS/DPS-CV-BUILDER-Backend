package dpscvbuilder.com.DPSCV_BUILDER.service.impl;

import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.CvCreatorMainContentDto;
import dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume.CvCreatorPersonalDataRequestDto;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DpsCvBuilderException;
import dpscvbuilder.com.DPSCV_BUILDER.model.Resume;
import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.MainContent;
import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.PersonalData;
import dpscvbuilder.com.DPSCV_BUILDER.repository.ResumeRepo;
import dpscvbuilder.com.DPSCV_BUILDER.repository.UserRepo;
import dpscvbuilder.com.DPSCV_BUILDER.service.PdfGenerateService;
import dpscvbuilder.com.DPSCV_BUILDER.service.ResumeService;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final UserRepo userRepo;

    private final ResumeRepo resumeRepo;

    private final ModelMapper modelMapper;

    private final PdfGenerateService pdfGenerateService;

    private final SpringTemplateEngine springTemplateEngine;

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
        }else throw new DpsCvBuilderException(ErrorEnum.ERROR_NOT_FOUND, "User is Not_Found with userId: " + id);

    }

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
        }else throw new DpsCvBuilderException(ErrorEnum.ERROR_NOT_FOUND, "User is Not_Found with userId: " + id);
    }

    public Resume getResume(String userId) {

        Resume resume = resumeRepo.findByUserId(userId);
        //ResumeDto resumeDto = modelMapper.map(resume, ResumeDto.class);
        return resume;
    }

    public byte[] download(Resume resume, String userId) {
        saveResume(resume, userId);
        Context context = new Context();
        Map<String, Object> data = new HashMap<>();
        data.put("resume", resume);
        context.setVariables(data);
        String html = springTemplateEngine.process("index", context);
        byte[] resumePdf = pdfGenerateService.generate(html);
        return resumePdf;
    }

    private void saveResume(Resume resume, String userId){

        if(userRepo.existsById(userId)){
            if(resumeRepo.existsByUserId(userId)){
                Resume savedResume = resumeRepo.findByUserId(userId);
                savedResume.setMainContents(resume.getMainContents());
                savedResume.setPersonalData(resume.getPersonalData());
                resumeRepo.save(savedResume);
            }else {
                resume.setUserId(userId);
                resumeRepo.save(resume);
            }
        }
    }

    private List<MainContent> mapToMainContentList(List<CvCreatorMainContentDto> mainContentDtos) {
        return modelMapper.map(mainContentDtos, new TypeToken<List<MainContent>>() {}.getType());
    }
}
