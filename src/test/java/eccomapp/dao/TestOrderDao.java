package eccomapp.dao;

import eccomapp.entity.OrderEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestOrderDao {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static OrderDao orderDao;
    static OrderEntity orderEntity;
    static Logger logger;
    @BeforeAll
    public static void setup()
    {
        connection= Mockito.mock(Connection.class);
        resultSet=Mockito.mock(ResultSet.class);
        preparedStatement=Mockito.mock(PreparedStatement.class);
        logger=Mockito.mock(Logger.class);
        orderDao=new OrderDao();
    }
    @Test
    public  void testAddOrder() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.addOrder(connection,"prashant","6395379407");
    }
    @Test
    public  void testDeleteOrder() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        orderDao.deleteOrder(connection,logger,"prashant");
    }
}
