package dpscvbuilder.com.DPSCV_BUILDER.model;

import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.MainContent;
import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.PersonalData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "resumes")
public class Resume extends BaseEntity {

    private String id;

    @Indexed(unique = true)
    @Field(name = "user_id")
    private String userId;

    private PersonalData personalData;

    private List<MainContent>  mainContents;
}
