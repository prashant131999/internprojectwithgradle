package eccomapp.controller;

import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.UserService;
import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

/**The UserController Class presents the menu .
 *
 */
public class UserController {
    Scanner sc = new Scanner(System.in);
    UserService userService=new UserService();

    /**This method presents menu for taking input according to which it does the operation
     *
     * @param connection for connecting to database
     * @param logger for logging
     */
    public void controllerUser(Connection connection, Logger logger)  {
        logger.info("Enter 1 to create user");
        logger.info("Enter 2 to delete user");
        logger.info("Enter 3 to update user detail ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                try {
                    userService.createuser(connection, logger);
                }catch(ApplicationRuntimeException e)
                {
                    logger.warning(e.getErrorCode()+"  "+e.getErrorMessage());
                } catch (InvalidInputException e) {
                    e.logError();
                }
                break;
            case 2:
                try {
                    userService.deleteuser(connection, logger);
                }catch (ApplicationRuntimeException e){
                    e.logError();
                } catch (InvalidInputException e) {
                    e.logError();
                }

                break;
            case 3:
                try {
                    userService.updateUser(connection, logger);
                }catch (ApplicationRuntimeException e){
                    e.logError();
                }
                break;
        }
    }
}
