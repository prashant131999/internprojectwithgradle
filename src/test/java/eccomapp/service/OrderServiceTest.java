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
import java.util.logging.Logger;

public class OrderServiceTest {
    private static UserDao userDao = Mockito.mock(UserDao.class);
    private static Connection connection = Mockito.mock(Connection.class);
    private static Logger logger = Mockito.mock(Logger.class);
    private static OrderDao orderDao = Mockito.mock(OrderDao.class);
    private static ProductDao productDao = Mockito.mock(ProductDao.class);
    private static OrderEntity orderEntity = Mockito.mock(OrderEntity.class);
    private static Validator validator = Mockito.mock(Validator.class);
    private static UserEntity userEntity = Mockito.mock(UserEntity.class);
    private static OrderService orderService;

    @BeforeAll
    public static void setup() {
        orderService = new OrderService(orderDao, productDao, orderEntity, validator, userDao, userEntity);
    }

    @Test
    public void testdelete() {
        orderService.deleteOrder(connection, logger, "Prashant");
    }

    @Test
    public void testDisplay() {
        orderService.displayOrder(connection, logger);
    }

}
