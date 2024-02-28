package dpscvbuilder.com.DPSCV_BUILDER.dto.request.resume;

import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.SubContent;
import lombok.Data;

import java.util.List;

@Data
public class CvCreatorMainContentDto {

    private int contentId;
    private String contentTitle;
    private List<SubContent> subContents;
}
