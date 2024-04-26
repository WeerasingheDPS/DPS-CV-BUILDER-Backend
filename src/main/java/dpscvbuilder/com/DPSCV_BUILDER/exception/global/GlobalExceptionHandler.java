package dpscvbuilder.com.DPSCV_BUILDER.exception.global;

import dpscvbuilder.com.DPSCV_BUILDER.dto.error.ErrorDto;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DpsCvBuilderException;
import dpscvbuilder.com.DPSCV_BUILDER.exception.DpsCvBuilderValidationException;
import dpscvbuilder.com.DPSCV_BUILDER.util.response.StandardResponse;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum.ERROR_INVALID_EMAIL_OR_PASSWORD;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DpsCvBuilderException.class})
    public ResponseEntity<StandardResponse> handleDreamHireException(DpsCvBuilderException ex){

        ErrorDto errorDto = ErrorDto.generateFromDreamHireException(ex);
        HttpStatus httpStatus = ex.getErrorEnum().getHttpStatus() != null ? ex.getErrorEnum().getHttpStatus() : HttpStatus.BAD_REQUEST;

        return new ResponseEntity<StandardResponse>(
               StandardResponse.builder()
                       .success(false)
                       .result(null)
                       .failure(errorDto)
                       .build(),
                httpStatus
        );
    }

    @ExceptionHandler({DpsCvBuilderValidationException.class})
    public ResponseEntity<StandardResponse> handleDreamHireValidationException(DpsCvBuilderValidationException ex){

        ErrorDto errorDto = ErrorDto.generateFromDreamHireException(ex);
        HttpStatus httpStatus = ex.getErrorEnum().getHttpStatus() != null? ex.getErrorEnum().getHttpStatus() : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<StandardResponse>(
                StandardResponse.builder()
                        .success(false)
                        .result(null)
                        .failure(errorDto)
                        .build(),
                httpStatus
        );
    }

    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<StandardResponse> handleRunTimeException(SignatureException ex){

        ErrorDto errorDto = ErrorDto.builder().description(ex.getMessage()).build();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<StandardResponse>(
                StandardResponse.builder()
                        .success(false)
                        .result(null)
                        .failure(errorDto)
                        .build(),
                httpStatus
        );
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<StandardResponse> handleException(Exception ex){

        ErrorDto errorDto = ErrorDto.builder().description(ex.getMessage()).build();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<StandardResponse>(
                StandardResponse.builder()
                        .success(false)
                        .result(null)
                        .failure(errorDto)
                        .build(),
                httpStatus
        );
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<StandardResponse> handleBadCredentialsException(BadCredentialsException ex){

        ErrorDto errorDto = ErrorDto.generateFromErrorEnum(ERROR_INVALID_EMAIL_OR_PASSWORD);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<StandardResponse>(
                StandardResponse.builder()
                        .success(false)
                        .result(null)
                        .failure(errorDto)
                        .build(),
                httpStatus
        );
    }
}
