package eccomapp.dao;

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
    private static Connection  connection= Mockito.mock(Connection.class);
    private static PreparedStatement preparedStatement=Mockito.mock(PreparedStatement.class);
    private static ResultSet resultSet=Mockito.mock(ResultSet.class);
    private static Logger logger=Mockito.mock(Logger.class);
    private static OrderDao orderDao;
    @BeforeAll
    public static void setup()
    {
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
