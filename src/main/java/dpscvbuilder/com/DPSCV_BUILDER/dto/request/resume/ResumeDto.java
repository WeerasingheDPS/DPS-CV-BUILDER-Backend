package dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume;


import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.MainContent;
import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.PersonalData;
import lombok.Data;

import java.util.List;

@Data
public class ResumeDto {

    private String id;

    private int userId;

    private PersonalData personalData;

    private List<MainContent> mainContents;
}
