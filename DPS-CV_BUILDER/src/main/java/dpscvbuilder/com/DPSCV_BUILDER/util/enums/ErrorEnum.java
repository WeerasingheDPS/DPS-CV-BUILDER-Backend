package dpscvbuilder.com.DPSCV_BUILDER.util.enums;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public enum ErrorEnum {

    ERROR_INVALID_EMAIL_OR_PASSWORD(40001,"ERROR_INVALID_EMAIL_OR_PASSWORD", "Invalid Email or Password!", HttpStatus.BAD_REQUEST),
    ERROR_DUPLICATE_EMAIL(40002,"ERROR_DUPLICATE_EMAIL", "Duplicate Email:", HttpStatus.BAD_REQUEST),
    ERROR_INVALID_ID(40003,"ERROR_INVALID_ID", "Invalid Id:", HttpStatus.BAD_REQUEST),
    ERROR_DUPLICATE_ID(40004,"ERROR_DUPLICATE_ID", "Duplicate Id:", HttpStatus.BAD_REQUEST),
    ERROR_EXPIRE_TOKEN(40005,"ERROR_EXPIRE_TOKEN", "Token is expired!", HttpStatus.BAD_REQUEST),
    ERROR_NOT_FOUND(40006,"ERROR_NOT_FOUND", "Error Not Found!", HttpStatus.NOT_FOUND),

    ;
    @Getter
    @Setter
    private int errorId;
    // Getter methods
    @Setter
    @Getter
    private String errorCode;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private HttpStatus httpStatus;

    // Constructor for the enum
    ErrorEnum(int errorId, String errorCode, String description, HttpStatus httpStatus) {
        this.errorId = errorId;
        this.errorCode = errorCode;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    ErrorEnum(String errorCode, String description, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    private static Map<String, ErrorEnum> map;
    // Static block to initialize the map
    static {
        map = new HashMap<>();
        for (ErrorEnum error : ErrorEnum.values()) {
            map.put(error.getErrorCode(), error);
        }
    }

    // Method to get ErrorEnum by errorCode
    public static ErrorEnum getByErrorCode(String errorCode) {
        return map.get(errorCode);
    }

}
