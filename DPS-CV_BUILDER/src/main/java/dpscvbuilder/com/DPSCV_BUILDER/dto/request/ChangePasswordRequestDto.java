package dpscvbuilder.com.DPSCV_BUILDER.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    private String oldPassword;
    private String newPassword;
}
