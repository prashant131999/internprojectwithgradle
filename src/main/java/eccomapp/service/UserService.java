package eccomapp.service;

import eccomapp.cache.Cache;
import eccomapp.dao.UserDao;
import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;

import java.sql.Connection;

/**
 * The User service class sends the data to User Dao class to manipulate the database
 * according to needs.
 */

public class UserService {
    private UserEntity userEntity;
    private UserDao userDao;
    private Validator validator;
    private Cache cache ;

    public UserService(UserEntity userEntity,UserDao userDao,Validator validator,Cache cache)
    {
        this.userEntity=userEntity;
        this.userDao=userDao;
        this.validator=validator;
        this.cache=cache;
    }
    public UserService()
    {
        userEntity=new UserEntity();
        userDao=new UserDao();
        validator=new Validator();
        cache=new Cache(10);
    }


    /**
     * This method create the user in the database by taking input
     *
     * @param connection for connecting to database
     * @param logger     for logging
     */

    public void addUser(Connection connection, UserEntity userEntity) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateNames(userEntity.getFname());
        validator.validateNames(userEntity.getLname());
        validator.validateEmailAddress(userEntity.getEmail());
        validator.validateMobileNumber(userEntity.getMobileNumber());
        if (cache.contains(userEntity.getMobileNumber())) {
            return;
        } else {
            userDao.createNewUser(userEntity, connection);
            cache.put(userEntity.getMobileNumber(), userEntity);
        }
    }

    /**
     * This method deletes the user from database by taking
     * email id of user
     *
     * @param connection for connecting to database
     * @param logger     for logging
     */

    public void deleteUser(Connection connection, UserEntity userEntity) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateEmailAddress(userEntity.getEmail());
        if (cache.contains(userEntity.getEmail())) {
            cache.delete(userEntity.getEmail());
            userDao.deleteUser(userEntity, connection);
        }
        userDao.deleteUser(userEntity, connection);
    }


    /**This method
     *
     * @param connection for connecting to database
     * @param userEntity for userEntity object
     * @throws ApplicationRuntimeException
     */
    public void updateUser(Connection connection, UserEntity userEntity) throws ApplicationRuntimeException {
        if (cache.contains(userEntity.getMobileNumber())) {
            cache.put(userEntity.getAddress(), userEntity);
            userDao.updateUser(userEntity, connection);
        } else {
            userDao.updateUser(userEntity, connection);
        }

    }

}


