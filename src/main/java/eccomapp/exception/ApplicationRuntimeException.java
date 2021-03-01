package eccomapp.exception;

import java.util.logging.Logger;

public class ApplicationRuntimeException extends RuntimeException {
    int errorCode;
    String errorMessage;
    private static Logger logger;

    public ApplicationRuntimeException(int errorCode, String errorMessage, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public void logError() {
        logger.warning(errorCode+" "+errorMessage);
    }
}
