package eccomapp.controller;

import eccomapp.exception.InvalidInputException;
import eccomapp.service.UserService;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserController {
    private static Logger logger=java.util.logging.Logger.getLogger(UserController.class.getName());
    Scanner sc = new Scanner(System.in);

    private String fname, lname, email, address, dateOfBirth, dateCreated, dateLastUpdated, mobileNumber;
    private int userid;

    UserService userService=new UserService();

    /**This method presents menu for taking input according to which it does the operation
     *
     * @param connection for connecting to database
     * @param logger for logging
     */
    public void controllerUser(Connection connection, Logger logger)  {
        System.out.println("Enter 1 to create user");
        System.out.println("Enter 2 to delete user");
        System.out.println("Enter 3 to update user detail ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                try {
                    userService.createuser(connection, logger);
                }catch(InvalidInputException e)
                {
                    logger.warning(e.getErrorCode()+"  "+e.getErrorMessage());
                }
                break;
            case 2:
                try {
                    userService.deleteuser(connection, logger);
                }catch (InvalidInputException e){
                    e.getError();
                }

                break;
            case 3:
                try {
                    userService.updateUser(connection, logger);
                }catch (InvalidInputException e){
                    e.getError();
                }
                break;
        }
    }
}
