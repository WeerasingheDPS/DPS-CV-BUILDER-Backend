package dpscvbuilder.com.DPSCV_BUILDER.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CvCreatorDto {
    private String id;
    private String title;
    private String email;
    private LocalDateTime birthday;
    private String city;
    private String name;
    private boolean visible;
    private String profilePicture;
    private String description;
    private String phone;
    private String address;
    private String facebook;
    private String twitter;
    private String linkedIn;
    private String currency;
    private String minSalary;
    private String maxSalary;
}
