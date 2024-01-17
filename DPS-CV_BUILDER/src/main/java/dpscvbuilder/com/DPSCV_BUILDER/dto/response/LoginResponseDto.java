package dpscvbuilder.com.DPSCV_BUILDER.dto.response;

import dpscvbuilder.com.DPSCV_BUILDER.model.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String token;
    private SystemUser user;
}
