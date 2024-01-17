package dpscvbuilder.com.DPSCV_BUILDER.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser extends BaseEntity{

    private String id;

    private String email;
    @JsonIgnore
    private String password;

    private String userType;
}
