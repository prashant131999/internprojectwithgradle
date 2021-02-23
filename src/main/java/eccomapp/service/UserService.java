package eccomapp.service;

import eccomapp.cache.Cache;
import eccomapp.dao.UserDao;
import eccomapp.entity.UserEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserService {

    private static Logger logger = java.util.logging.Logger.getLogger(UserService.class.getName());
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
    public void createuser(Connection connection, Logger logger) throws InvalidInputException {
        System.out.println("Enter the first name");
        fname = sc.next();
        userEntity.setFname(fname);
        validator.validateNames(userEntity.getFname());
        System.out.println("Enter the last name");
        lname = sc.next();
        userEntity.setLname(lname);
        validator.validateNames(userEntity.getLname());
        System.out.println("Enter the mobile number");
        mobileNumber = sc.next();
        userEntity.setMobileNumber(mobileNumber);
        validator.validateMobileNumber(userEntity.getMobileNumber());
        System.out.println("Enter the email");
        email = sc.next();
        userEntity.setEmail(email);
        validator.validateEmailAddress(userEntity.getEmail());
        System.out.println("Enter the address");
        address = sc.next();
        userEntity.setAddress(address);
        System.out.println("date of birth");
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

    public void deleteuser(Connection connection, Logger logger) throws InvalidInputException {
        System.out.println("Enter the email id of user to delete");
        email = sc.next();
        userEntity.setEmail(email);

        validator.validateEmailAddress(userEntity.getEmail());


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
    public void updateUser(Connection connection, Logger logger) throws InvalidInputException {
        System.out.println("Enter the mobile number of user whose address you want to update");
        String mob = sc.next();
        System.out.println("Enter the new address");
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


