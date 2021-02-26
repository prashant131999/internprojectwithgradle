package eccomapp.service;

import eccomapp.dao.OrderDao;
import eccomapp.dao.ProductDao;
import eccomapp.dao.UserDao;
import eccomapp.entity.OrderEntity;
import eccomapp.entity.UserEntity;
import eccomapp.util.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class OrderServiceTest {
    static UserDao userDao;
    static OrderService orderService;
    static Connection connection;
    static Logger logger;
    static OrderDao orderDao;
    static ProductDao productDao;
    static OrderEntity orderEntity;
    static Validator validator;
    static UserEntity userEntity;
    @BeforeAll
    public static void setup() {
        Scanner sc =new Scanner(System.in);
        connection = Mockito.mock(Connection.class);
        logger = Mockito.mock(Logger.class);
        orderDao = Mockito.mock(OrderDao.class);
        productDao = Mockito.mock(ProductDao.class);
        orderEntity = Mockito.mock(OrderEntity.class);
        validator = Mockito.mock(Validator.class);
         userEntity = Mockito.mock(UserEntity.class);
         userDao = Mockito.mock(UserDao.class);
        orderService = new OrderService(sc,orderDao, productDao, orderEntity, validator, userDao, userEntity);
    }

    @Test
    public void testdelete() {
        orderService.deleteOrder(connection, logger, "Prashant");
    }
    @Test
    public void testDisplay()
    {
        orderService.displayOrder(connection,logger);
    }

}
