package dpscvbuilder.com.DPSCV_BUILDER.util.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import dpscvbuilder.com.DPSCV_BUILDER.dto.error.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StandardResponse<T> {

    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorDto failure;


}
