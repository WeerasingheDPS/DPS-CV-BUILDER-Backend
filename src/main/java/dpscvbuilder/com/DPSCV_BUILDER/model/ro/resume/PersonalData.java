package dpscvbuilder.com.DPSCV_BUILDER.model.ro.resume;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class PersonalData {

    private String email;

    private String name;

    @Field(name = "profile_picture")
    private String profilePicture;

    private String jobTitle;

    private String phone;

    @Field
    private String address;

    private String twitter;

    private String linkedIn;

    private String github;

    private String website;

    private String discord;

    @Field(name = "linked_in_label")
    private String linkedInLabel;

    @Field(name = "twitter_label")
    private String twitterLabel;

    @Field(name = "github_label")
    private String githubLabel;

    @Field(name = "website_label")
    private String websiteLabel;

    @Field(name = "discord_label")
    private String discordLabel;
}
