package eccomapp.dao;

import eccomapp.exception.ApplicationRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestOrderDao {
    private  Connection  connection= Mockito.mock(Connection.class);
    private  PreparedStatement preparedStatement=Mockito.mock(PreparedStatement.class);
    private  ResultSet resultSet=Mockito.mock(ResultSet.class);
    private  Logger logger=Mockito.mock(Logger.class);
    private  OrderDao orderDao;
    @BeforeEach
    public  void setup()
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
        orderDao.deleteOrder(connection,"prashant");
    }
    @Test
    public void testWrongAddOrder() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
            orderDao.addOrder(connection,"laptop","prashant@gmail.com");
        } catch (ApplicationRuntimeException e) {
            assertEquals("invalid input",e.getErrorMessage());
        }
    }
    @Test
    public void testWrongDeleteOrder() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
            orderDao.deleteOrder(connection,"laptop");
        } catch (ApplicationRuntimeException e) {
            assertEquals("wrong product name",e.getErrorMessage());
        }
    }

}
