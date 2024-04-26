package dpscvbuilder.com.DPSCV_BUILDER.exception;

import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@AllArgsConstructor
public class DpsCvBuilderException extends RuntimeException{

    private String errorCode;

    private String description;

    private ErrorEnum errorEnum;

   public DpsCvBuilderException(String message){
       super(message);
   }

   public DpsCvBuilderException(String message, Throwable throwable){
       super(message, throwable);
   }

   public DpsCvBuilderException(ErrorEnum dreamHireErrorEnum){
       this(dreamHireErrorEnum, null);
   }

   public DpsCvBuilderException(ErrorEnum dreamHireErrorEnum, String customDescription){
       this.errorEnum = dreamHireErrorEnum;
       if (StringUtils.hasText(customDescription)){
           this.errorEnum.setDescription(customDescription);
       }
       this.errorCode = errorEnum.getErrorCode();
       this.description = errorEnum.getDescription();
   }
}
