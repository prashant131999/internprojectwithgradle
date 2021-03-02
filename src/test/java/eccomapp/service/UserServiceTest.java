package eccomapp.service;

import eccomapp.cache.Cache;
import eccomapp.dao.OrderDao;
import eccomapp.dao.ProductDao;
import eccomapp.dao.UserDao;
import eccomapp.entity.OrderEntity;
import eccomapp.entity.UserEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private static UserService userService;
    private static UserDao userDao = Mockito.mock(UserDao.class);
    private static Connection connection = Mockito.mock(Connection.class);
    private static OrderDao orderDao = Mockito.mock(OrderDao.class);
    private static ProductDao productDao = Mockito.mock(ProductDao.class);
    private static OrderEntity orderEntity = Mockito.mock(OrderEntity.class);
    private static Validator validator = Mockito.mock(Validator.class);
    private static UserEntity userEntity = Mockito.mock(UserEntity.class);
    private static Cache cache=Mockito.mock((Cache.class));

    @BeforeAll
    public static void setup() {
        userService = new UserService(userEntity,userDao,validator,cache);
    }
    @Test
    public void testAddUser() throws InvalidInputException {
        when(validator.validateNames("prashant")).thenReturn(true);
        when(validator.validateNames("sahrawat")).thenReturn(true);
        doNothing().when(validator).validateEmailAddress("prashant@gmail.com");
        when(validator.validateMobileNumber("9639402926")).thenReturn(true);
        when(cache.contains("prashant")).thenReturn(false);
        userService.addUser(connection,userEntity);
    }

    @Test
    public void testUpdateUser()
    {
        when(cache.contains("9639402926")).thenReturn(true);
        doNothing().when(cache).delete("9639402926");
        doNothing().when(cache).put("9639402926",userEntity);
        userService.updateUser(connection,userEntity);
    }
    @Test
    public void testDeleteUser() throws InvalidInputException {
        doNothing().when(validator).validateEmailAddress("prashant@gmail.com");
        when(cache.contains("prashant@gmail.com")).thenReturn(true);
        userService.deleteUser(connection,userEntity);


    }
}
