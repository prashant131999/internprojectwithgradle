package eccomapp.service;

import eccomapp.cache.Cache;
import eccomapp.dao.UserDao;
import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;
import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;
/**
 * The User service class sends the data to User Dao class to manipulate the database
 * according to needs.
 */

public class UserService {

    Scanner sc = new Scanner(System.in);
    UserEntity userEntity = new UserEntity();
    UserDao userDao = new UserDao();
    Validator validator = new Validator();
    Cache cache = new Cache(10);
    private String fname, lname, email, address, dateOfBirth, dateCreated, dateLastUpdated, mobileNumber;
    private int userid;
    /**
     * This method create the user in the database by taking input
     *
     * @param connection for connecting to database
     * @param logger     for logging
     */
    public void createuser(Connection connection, Logger logger) throws ApplicationRuntimeException, InvalidInputException {
        logger.info("Enter the first name");
        fname = sc.next();
        validator.validateNames(fname);
        userEntity.setFname(fname);
        logger.info("Enter the last name");
        lname = sc.next();
        validator.validateNames(fname);
        userEntity.setLname(lname);
        logger.info("Enter the mobile number");
        mobileNumber = sc.next();
        validator.validateMobileNumber(mobileNumber);
        userEntity.setMobileNumber(mobileNumber);
        logger.info("Enter the email");
        email = sc.next();
        validator.validateEmailAddress(email);
        userEntity.setEmail(email);
        logger.info("Enter the address");
        address = sc.next();
        userEntity.setAddress(address);
        logger.info("date of birth in format dd-mm-yyyy");
        dateOfBirth = sc.next();
        userEntity.setDateOfBirth(dateOfBirth);
        Cache cache = new Cache(10);
        if (cache.contains(mobileNumber)) {
            logger.info("User is already created");
        } else {
            userDao.createNewUser(userEntity, connection);
            cache.put(mobileNumber, userEntity);
        }
        logger.info("User created");


    }

    /**
     * This method deletes the user from database by taking
     * email id of user
     *
     * @param connection for connecting to database
     * @param logger     for logging
     */

    public void deleteuser(Connection connection, Logger logger) throws ApplicationRuntimeException, InvalidInputException {
        logger.info("Enter the email id of user to delete");
        email = sc.next();
        validator.validateEmailAddress(email);
        userEntity.setEmail(email);
        UserDao userDao=new UserDao();
        Cache cache = new Cache(10);
        if (cache.contains(email)) {
            cache.delete(email);
            userDao.deleteUser(userEntity, connection);
            logger.info("user deleted");
        } else {
                if (userDao.emailPresent(userEntity, connection)) {
                    userDao.deleteUser(userEntity, connection);
                    logger.info("user deleted");
                } else {
                    logger.warning("You are not registered user");
                }
            }

        }


    /**
     * This method update the address of user by taking mobile number from user
     *
     * @param connection for connected to database
     * @param logger     for logging
     */
    public void updateUser(Connection connection, Logger logger) throws ApplicationRuntimeException {
        logger.info("Enter the mobile number of user whose address you want to update");
        String mob = sc.next();
        logger.info("Enter the new address");
        address = sc.next();
        userEntity.setAddress(address);
        userEntity.setMobileNumber(mob);
        if (cache.contains(mob)) {
            cache.delete(mob);
            cache.put(address, userEntity);
            userDao.updateUser(userEntity, connection);
            logger.info("user data updated");
        } else {
            userDao.updateUser(userEntity, connection);
            logger.info("user data updated");
        }

    }

}


