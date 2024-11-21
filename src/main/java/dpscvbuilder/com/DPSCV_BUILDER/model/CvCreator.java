package dpscvbuilder.com.DPSCV_BUILDER.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "cv_creators")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CvCreator {

    private String email;

    private String title;

    private LocalDateTime birthday;

    private String city;

    private String name;

    private boolean visible = true;

    private String profilePicture;

    @Field
    private String description;

    private String phone;

    @Field
    private String address;

    @Field
    private String facebook;

    @Field
    private String twitter;

    @Field
    private String linkedIn;

    private String currency;

    private String minSalary;

    private String maxSalary;

    private UserType userType;

}
