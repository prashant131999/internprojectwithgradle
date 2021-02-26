package eccomapp.exception;

import java.util.logging.Logger;

public class InvalidInputException extends Exception {
    int errorCode;
    String errorMessage;
    private static Logger logger;
    public InvalidInputException(int errorCode, String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public void logError() {
        System.out.println(errorCode+" "+errorMessage);
    }
}
