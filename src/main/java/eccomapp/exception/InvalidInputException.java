package eccomapp.exception;

//package ecommerce.exception;
import java.util.logging.Logger;

public class InvalidInputException extends RuntimeException{
    int errorCode;
    String errorMessage;
    private static Logger logger;
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/InternShip/src/main/resources/logging.properties");
        logger= java.util.logging.Logger.getLogger(InvalidInputException.class.getName());
    }
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


    public void getError() {
        logger.info(errorMessage+" "+errorCode);
    }
}
