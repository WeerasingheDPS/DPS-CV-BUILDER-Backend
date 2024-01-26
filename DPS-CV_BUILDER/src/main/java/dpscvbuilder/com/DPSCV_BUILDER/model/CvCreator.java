package dpscvbuilder.com.DPSCV_BUILDER.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "cv_creators")
@AllArgsConstructor
@NoArgsConstructor
public class CvCreator extends User {


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

}
