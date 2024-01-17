package dpscvbuilder.com.DPSCV_BUILDER.exception;


import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;

public class DreamHireValidationException extends DreamHireException{
    public DreamHireValidationException(String errorCode, String description, ErrorEnum errorEnum) {
        super(errorCode, description, errorEnum);
    }

    public DreamHireValidationException(String message) {
        super(message);
    }

    public DreamHireValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DreamHireValidationException(ErrorEnum dreamHireErrorEnum) {
        super(dreamHireErrorEnum);
    }

    public DreamHireValidationException(ErrorEnum dreamHireErrorEnum, String customDescription) {
        super(dreamHireErrorEnum, customDescription);
    }
}
