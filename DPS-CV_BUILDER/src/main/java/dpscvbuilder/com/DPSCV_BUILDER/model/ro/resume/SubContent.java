package dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SubContent {

    private int subContentId;
    private String title;
    private String subTitle;
    private String city;
    private String country;
    private Date startDate;
    private Date endDate;
    private String description;
    private String link;
    private boolean showStartYearOnly;
    private boolean showEndYearOnly;
    private boolean present;
}
