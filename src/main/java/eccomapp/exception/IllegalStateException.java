package eccomapp.exception;


import java.util.logging.Logger;

public class IllegalStateException extends Exception {
    private static Logger logger;
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/testinggradle/src/test/resources/logging.properties");
        logger= java.util.logging.Logger.getLogger(InvalidInputException.class.getName());
    }
    public IllegalStateException(String errorMessage){
        logger.info(errorMessage);
    }
}
