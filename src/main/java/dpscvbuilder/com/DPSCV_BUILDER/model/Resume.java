package dpscvbuilder.com.DPSCV_BUILDER.model;

import com.google.auto.value.AutoValue;
import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.MainContent;
import dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume.PersonalData;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "resumes")
public class Resume{

    private String id;

    @Indexed(unique = true)
    @Field(name = "user_id")
    private String userId;

    private PersonalData personalData;

    private List<MainContent>  mainContents;
}
