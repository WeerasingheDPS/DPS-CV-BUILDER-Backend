package dpscvbuilder.com.DPSCV_BUILDER.exception;

import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@AllArgsConstructor
public class DreamHireException extends RuntimeException{

    private String errorCode;

    private String description;

    private ErrorEnum errorEnum;

   public DreamHireException(String message){
       super(message);
   }

   public DreamHireException(String message, Throwable throwable){
       super(message, throwable);
   }

   public DreamHireException(ErrorEnum dreamHireErrorEnum){
       this(dreamHireErrorEnum, null);
   }

   public DreamHireException(ErrorEnum dreamHireErrorEnum, String customDescription){
       this.errorEnum = dreamHireErrorEnum;
       if (StringUtils.hasText(customDescription)){
           this.errorEnum.setDescription(customDescription);
       }
       this.errorCode = errorEnum.getErrorCode();
       this.description = errorEnum.getDescription();
   }
}
