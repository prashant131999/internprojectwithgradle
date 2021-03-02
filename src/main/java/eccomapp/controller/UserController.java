package eccomapp.controller;

import eccomapp.entity.UserEntity;
import eccomapp.service.UserService;
import eccomapp.util.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Scanner;

/**The UserController Class presents the menu .
 *
 */
public class UserController {
    private Scanner sc = new Scanner(System.in);
    private UserEntity userEntity = new UserEntity();
    private Validator validator = new Validator();
   private UserService userService=new UserService();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    javax.validation.Validator validators = factory.getValidator();
    static java.sql.Connection connection = eccomapp.util.Connection.create();
    /**
     * This method create the user in the database by taking input
     *
     * @param connection for connecting to database
     * @param logger     for logging
     */
    private String fname, lname, email, address, dateOfBirth, dateCreated, dateLastUpdated, mobileNumber;
    private int userid;
//    public void createuser( ) throws ApplicationRuntimeException, InvalidInputException {
//        logger.info("Enter the first name");
//        fname = sc.next();
//        validator.validateNames(fname);
//        userEntity.setFname(fname);
//        logger.info("Enter the last name");
//        lname = sc.next();
//        validator.validateNames(fname);
//        userEntity.setLname(lname);
//        logger.info("Enter the mobile number");
//        mobileNumber = sc.next();
//        validator.validateMobileNumber(mobileNumber);
//        userEntity.setMobileNumber(mobileNumber);
//        logger.info("Enter the email");
//        email = sc.next();
//        validator.validateEmailAddress(email);
//        userEntity.setEmail(email);
//        logger.info("Enter the address");
//        address = sc.next();
//        userEntity.setAddress(address);
//        logger.info("date of birth in format dd-mm-yyyy");
//        dateOfBirth = sc.next();
//        userEntity.setDateOfBirth(dateOfBirth);
//        Set<ConstraintViolation<UserEntity>> constraintViolations=validators.validate(userEntity);
//        if(constraintViolations.size()>0)
//        {
//            throw new InvalidInputException(400,constraintViolations.iterator().next().getMessage());
//        }
//        userService.addUser(connection);
//    }
//    /**
//     * This method deletes the user from database by taking
//     * email id of user
//     *
//     * @param connection for connecting to database
//     * @param logger     for logging
//     */
//
//    public void deleteUserDetail(Connection connection, Logger logger) throws ApplicationRuntimeException, InvalidInputException {
//        logger.info("Enter the email id of user to delete");
//        email = sc.next();
//        validator.validateEmailAddress(email);
//        userEntity.setEmail(email);
//        userService.deleteUser(connection,logger);
//    }
//    /**
//     * This method update the address of user by taking mobile number from user
//     *
//     * @param connection for connected to database
//     * @param logger     for logging
//     */
//    public void updateUserDetail(Connection connection, Logger logger) throws ApplicationRuntimeException {
//        logger.info("Enter the mobile number of user whose address you want to update");
//        String mob = sc.next();
//        logger.info("Enter the new address");
//        address = sc.next();
//        userEntity.setAddress(address);
//        userEntity.setMobileNumber(mob);
//        userService.updateUser(connection,logger);
//    }
//
//
//
//    /**This method presents menu for taking input according to which it does the operation
//     *
//     * @param connection for connecting to database
//     * @param logger for logging
//     */
//    public void controllerUser(Connection connection, Logger logger)  {
//        logger.info("Enter 1 to create user");
//        logger.info("Enter 2 to delete user");
//        logger.info("Enter 3 to update user detail ");
//        int choice = sc.nextInt();
//        switch (choice) {
//            case 1:
//                try {
//                    createuser(connection, logger);
//                }catch(ApplicationRuntimeException e)
//                {
//                    logger.warning(e.getErrorCode()+"  "+e.getErrorMessage());
//                } catch (InvalidInputException e) {
//                    e.logError();
//                }
//                break;
//            case 2:
//                try {
//                    deleteUserDetail(connection, logger);
//                }catch (ApplicationRuntimeException e){
//                    e.logError();
//                } catch (InvalidInputException e) {
//                    e.logError();
//                }
//
//                break;
//            case 3:
//                try {
//                    updateUserDetail(connection, logger);
//                }catch (ApplicationRuntimeException e){
//                    e.logError();
//                }
//                break;
//            default:
//                break;
//        }
//    }
}
