package dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainContent {

    private int contentId;
    private String contentTitle;
    private List<SubContent> subContents;
}
