package dpscvbuilder.com.DPSCV_BUILDER.exception;


import dpscvbuilder.com.DPSCV_BUILDER.util.enums.ErrorEnum;

public class DpsCvBuilderValidationException extends DpsCvBuilderException {
    public DpsCvBuilderValidationException(String errorCode, String description, ErrorEnum errorEnum) {
        super(errorCode, description, errorEnum);
    }

    public DpsCvBuilderValidationException(String message) {
        super(message);
    }

    public DpsCvBuilderValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DpsCvBuilderValidationException(ErrorEnum dreamHireErrorEnum) {
        super(dreamHireErrorEnum);
    }

    public DpsCvBuilderValidationException(ErrorEnum dreamHireErrorEnum, String customDescription) {
        super(dreamHireErrorEnum, customDescription);
    }
}
