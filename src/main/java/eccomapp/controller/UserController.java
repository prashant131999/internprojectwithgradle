package eccomapp.controller;

import eccomapp.dao.UserDao;
import eccomapp.entity.UserEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserController {
    private static Logger logger=java.util.logging.Logger.getLogger(UserController.class.getName());
    Scanner sc = new Scanner(System.in);

    private String fname, lname, email, address, dateOfBirth, dateCreated, dateLastUpdated, mobileNumber;
    private int userid;




    public void createuser(Connection connection,Logger logger) {
        UserService userServobj = new UserService();
        System.out.println("Enter the first name");
        fname = sc.next();
        UserEntity userEntityObj=new UserEntity();
        UserDao userDaoObject=new UserDao();
        userEntityObj.setFname(fname);
        try{
            userServobj.validateNames(userEntityObj.getFname());
        }
        catch(Throwable e)
        {
           e.printStackTrace();
        }
        System.out.println("Enter the last name");
        lname = sc.next();
        userEntityObj.setLname(lname);
        try{
            userServobj.validateNames(userEntityObj.getLname());
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
        System.out.println("Enter the mobile number");
        mobileNumber = sc.next();
        userEntityObj.setMobileNumber(mobileNumber);
        try{
            userServobj.validateMobileNumber(userEntityObj.getMobileNumber());
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
        System.out.println("Enter the email");
        email = sc.next();
        userEntityObj.setEmail(email);
        try{
            userServobj.validateEmailAddress(userEntityObj.getEmail());
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }

        System.out.println("Enter the address");
        address = sc.next();
        userEntityObj.setAddress(address);
        System.out.println("date of birth");
        dateOfBirth = sc.next();
        userEntityObj.setDateOfBirth(dateOfBirth);
//        Cache cache=new Cache(4);
//        if(cache.contains(mobileNumber))
//        {
//            logger.info("User is already created");
//        }
//        else {
//            try {
//                userDaoObject.createNewUser(userEntityObj, connection);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            cache.put(mobileNumber, userEntityObj);
//        }
//        logger.info("User created");
        try {
            userServobj.validate(fname,lname,email,mobileNumber,address,dateOfBirth,userEntityObj,connection);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }

    }

    public void deleteuser(Connection connection,Logger logger) {
        UserService userServobj = new UserService();
        System.out.println("Enter the email id of user to delete");
        email=sc.next();
        UserEntity userEntityObj=new UserEntity();
        UserDao userDaoObject=new UserDao();
        userEntityObj.setEmail(email);
        try{
            userServobj.validateEmailAddress(userEntityObj.getEmail());
        }
        catch(Throwable e)
        {
            e.printStackTrace();
        }
        userDaoObject.deleteUser(userEntityObj,connection);
        logger.info("user deleted");

    }
    public void updateUser(Connection connection,Logger logger) {
        System.out.println("Enter the new address");
        address=sc.next();
        UserEntity userEntityObj=new UserEntity();
        userEntityObj.setAddress(address);
        UserDao userDaoObject=new UserDao();
        try {
            userDaoObject.updateUser(userEntityObj,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info("user data updated");

    }
    public void controllerUser(Connection connection, Logger logger) {
        System.out.println("Enter 1 to create user");
        System.out.println("Enter 2 to delete user");
        System.out.println("Enter 3 to update user detail ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                createuser(connection,logger);
                break;
            case 2:
                deleteuser(connection,logger);
                break;
            case 3:
                updateUser(connection,logger);
                break;
        }
    }
}
