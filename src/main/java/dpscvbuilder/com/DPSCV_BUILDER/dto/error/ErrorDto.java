package dpscvbuilder.com.DPSCV_BUILDER.dto.error;

import dpscvbuilder.com.DPSCV_BUILDER.exception.DpsCvBuilderException;
import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private int errorId;

    private String errorCode;

    private String description;


    public static  ErrorDto generateFromDreamHireException(final DpsCvBuilderException ex){
        var error = ErrorDto.builder();

        error.errorCode(ex.getErrorCode())
                .description(StringUtils.hasText(ex.getDescription())? ex.getDescription(): ex.getMessage())
                .errorId(!ObjectUtils.isEmpty(ex.getErrorEnum().getErrorId())? ex.getErrorEnum().getErrorId() : 0);

        return error.build();
    }

    public static  ErrorDto generateFromErrorEnum(final ErrorEnum errorEnum){
        var error = ErrorDto.builder();
        error.errorCode(errorEnum.getErrorCode())
                .description(errorEnum.getDescription())
                .errorId(errorEnum.getErrorId());
        return error.build();
    }
}
