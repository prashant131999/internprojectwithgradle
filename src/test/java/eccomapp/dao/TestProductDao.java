package eccomapp.dao;

import eccomapp.entity.ProductEntity;
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

public class TestProductDao {
    static Connection connection;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    static ProductDao productDao;
    static ProductEntity productEntity;
    static Logger logger;
    @BeforeAll
    public static void setup()
    {
        connection= Mockito.mock(Connection.class);
        resultSet=Mockito.mock(ResultSet.class);
        preparedStatement=Mockito.mock(PreparedStatement.class);
        productEntity=Mockito.mock(ProductEntity.class);
        logger=Mockito.mock(Logger.class);
        productDao=new ProductDao();
    }
    @Test
    public void testAddProduct() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.addProduct(productEntity,connection);
    }
    @Test
    public void testDisplay() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        productDao.display(connection,logger);
    }
    @Test
    public void testGetUuid() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        productDao.getID(connection,"prashant");

    }
    @Test
    public  void testGetQuantity() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.getQuantity(connection,"prashant");
    }
    @Test
    public  void testDelte() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.deleteProduct(connection,"prashant");
    }
    @Test
    public  void testUpdate() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.updateProductName(connection,"prashant","prajuwal");
    }
    @Test
    public  void testUpdateProduct() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.updateQuantity(connection,"prashant",40,logger);
    }
    @Test
    public  void testGetTotalQuantity() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.getTotalCost(connection,"prashant");
    }
    @Test
    public  void testUpdateQuantity() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.updateQuantity(connection,"prashant",10,logger);
    }
    @Test
    public  void testGetTotalCostOrder() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.getTotalCostOrder(connection,"prashant");
    }

}
