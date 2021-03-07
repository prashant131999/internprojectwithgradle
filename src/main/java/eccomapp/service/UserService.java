package eccomapp.service;

import eccomapp.cache.Cache;
import eccomapp.dao.UserDao;
import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.UserModel;
import eccomapp.util.Validator;

import java.sql.Connection;
import java.util.UUID;

/**
 * The User service class sends the data to User Dao class to manipulate the database
 * according to needs.
 */

public class UserService {
    private UserEntity userEntity;
    private UserDao userDao;
    private Validator validator;
    private Cache cache;
    private UserModel userModel;

    public UserService(UserEntity userEntity, UserDao userDao, Validator validator, Cache cache, UserModel userModel) {
        this.userEntity = userEntity;
        this.userDao = userDao;
        this.validator = validator;
        this.cache = cache;
        this.userModel=userModel;
    }

    public UserService() {
        userEntity = new UserEntity();
        userDao = new UserDao();
        validator = new Validator();
        cache = new Cache(10);
        userModel=new UserModel();
    }


    /**
     * This method create the user in the database by taking input
     *
     * @param connection for connecting to database
     * @UserEntity for taking inputs from user entity
     */

    public void addUser(Connection connection, UserEntity userEntity) throws ApplicationRuntimeException, InvalidInputException {
        userEntity.setUserid(UUID.randomUUID());
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

    public void deleteUser(Connection connection, String email) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateEmailAddress(email);
        if (cache.contains(email)) {
            cache.delete(email);
            userDao.deleteUser(email, connection);
        }
        else {
            userDao.deleteUser(email, connection);
        }

    }


    /**
     * This method
     *
     * @param connection for connecting to database
     * @param userEntity for userEntity object
     * @throws ApplicationRuntimeException
     */
    public void updateUser(Connection connection, UserModel userModel) throws ApplicationRuntimeException,InvalidInputException {
            if (cache.contains(userModel.getEmail())) {
                cache.put(userModel.getAddress(), userEntity);
                userDao.updateUser(userModel, connection);
            } else {
                userDao.updateUser(userModel, connection);
            }


    }
    public UserEntity displayUsers(String email,Connection connection)throws ApplicationRuntimeException,InvalidInputException {
        return userDao.displayUsersToDb(email, connection);
    }

}


