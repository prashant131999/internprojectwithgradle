package eccomapp.exception;


import java.util.logging.Logger;

public class IllegalStateException extends Exception {
    private static Logger logger;
    public IllegalStateException(String errorMessage){
        logger.info(errorMessage);
    }
}
