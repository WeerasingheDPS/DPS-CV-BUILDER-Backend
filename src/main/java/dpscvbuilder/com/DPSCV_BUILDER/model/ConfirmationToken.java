package dpscvbuilder.com.DPSCV_BUILDER.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "confirmation_token")
@Builder
public class ConfirmationToken {

    private String id;

    private String token;

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    private Boolean used;

    @Field(name = "user_email")
    private String userEmail;
}
