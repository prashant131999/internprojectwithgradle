package eccomapp.service;

import eccomapp.cache.Cache;
import eccomapp.dao.UserDao;
import eccomapp.entity.UserEntity;
import eccomapp.exception.InvalidInputException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class UserService {

    private static Logger logger=java.util.logging.Logger.getLogger(UserService.class.getName());
    public  static boolean  validateNames(String name) throws InvalidInputException
    {
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (Character.isDigit(ch)) {
                throw new InvalidInputException(400,"check input");
            }
        }
        return true;

    }
    public static boolean validateMobileNumber(String mobileNumber ) throws InvalidInputException
    {
        if(mobileNumber.length()==10)
        {
            return true ;
        }
        throw new InvalidInputException(400,"invalid mobile number");

    }
    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public  void validateEmailAddress(String email) throws InvalidInputException
    {
        if(!(isValidEmail(email)))
        {
            throw new InvalidInputException(400,"invalid email address");
        }
    }
    public  void validate(String fname, String lname, String email, String mobileNumber, String address,
                     String dateOfBirth, UserEntity userEntityObj,Connection connection) throws InvalidInputException
    {
        UserDao userDao=new UserDao();
        Cache cache=new Cache(10);
        if(cache.contains(mobileNumber))
        {
            logger.info("User is already created");
        }
        else {
            try {
                userDao.createNewUser(fname,lname,email,mobileNumber,address,dateOfBirth,userEntityObj, connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            cache.put(mobileNumber, userEntityObj);
        }
        logger.info("User created");


    }


}
