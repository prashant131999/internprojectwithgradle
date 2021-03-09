package eccomapp.service;

import eccomapp.cache.Cache;
import eccomapp.dao.UserDao;
import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.UserAddModel;
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
    private OrderService orderService;

    public UserService(UserEntity userEntity, UserDao userDao, Validator validator, Cache cache, UserModel userModel
    ,OrderService orderService) {
        this.userEntity = userEntity;
        this.userDao = userDao;
        this.validator = validator;
        this.cache = cache;
        this.userModel=userModel;
        this.orderService=orderService;
    }

    public UserService() {
        userEntity = new UserEntity();
        userDao = new UserDao();
        validator = new Validator();
        cache = new Cache(10);
        userModel=new UserModel();
        orderService=new OrderService();
    }


    /**
     * This method create the user in the database by taking input
     *
     * @param connection for connecting to database
     * @UserEntity for taking inputs from user entity
     */

    public void addUser(Connection connection, UserAddModel userAddModel) throws ApplicationRuntimeException, InvalidInputException {
        userAddModel.setUserid(UUID.randomUUID());
        validator.validateNames(userAddModel.getFname());
        validator.validateNames(userAddModel.getLname());
        validator.validateEmailAddress(userAddModel.getEmail());
        validator.validateMobileNumber(userAddModel.getMobileNumber());
        if (cache.contains(userAddModel.getMobileNumber())) {
            return;
        } else {
            userDao.createNewUser(userAddModel, connection);
            cache.put(userEntity.getMobileNumber(), userAddModel);
        }
    }

    /**
     * This method deletes the user from database by taking
     * email id of user
     *
     * @param connection for connecting to database
     */

    public void deleteUser(Connection connection, String email) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateEmailAddress(email);
        if (cache.contains(email)) {
            cache.delete(email);
        }
        UUID custId=userDao.getID(connection,email);
        orderService.deleteOrderById(custId,connection);
            userDao.deleteUser(email, connection);


    }


    /**
     * This method
     *
     * @param connection for connecting to database
     * @throws ApplicationRuntimeException
     */
    public void updateUser(Connection connection, UserModel userModel) throws ApplicationRuntimeException,InvalidInputException {
        validator.validateEmailAddress(userModel.getEmail());
            if (cache.contains(userModel.getEmail())) {
                cache.put(userModel.getAddress(), userEntity);
                userDao.updateUser(userModel, connection);
            } else {
                userDao.updateUser(userModel, connection);
            }


    }

    /**This method calls the dao and takes entity as a return from dao
     *
     * @param email for email of user
     * @param connection for connection
     * @return UserEntity
     * @throws ApplicationRuntimeException
     * @throws InvalidInputException
     */
    public UserEntity displayUsers(String email,Connection connection)throws ApplicationRuntimeException,InvalidInputException {
        validator.validateEmailAddress(email);
        return userDao.displayUsersToDb(email, connection);
    }

}


