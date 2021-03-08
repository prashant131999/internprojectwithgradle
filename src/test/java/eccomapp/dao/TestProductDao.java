package eccomapp.dao;

import eccomapp.entity.ProductEntity;
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

public class TestProductDao {
    private  Connection connection= Mockito.mock(Connection.class);
    private  PreparedStatement preparedStatement=Mockito.mock(PreparedStatement.class);
    private  ResultSet resultSet=Mockito.mock(ResultSet.class);
    private  ProductEntity productEntity=Mockito.mock(ProductEntity.class);
    private  Logger logger=Mockito.mock(Logger.class);
    private  ProductDao productDao;
    @BeforeEach
    public  void setup()
    {
        productDao=new ProductDao();
    }
    @Test
    public void testAddProduct() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.addProduct(productEntity,connection);
    }
    @Test
    public void testGetUuid() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        productDao.getID(connection,productEntity);

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
        productDao.deleteProduct(connection,"laptop");
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
        productDao.updateQuantity(connection,"samsung",20);
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
        productDao.updateQuantity(connection,"laptop",10);
    }
    @Test
    public  void testGetTotalCostOrder() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        productDao.getTotalCostOrder(connection,"prashant");
    }
    @Test
    public void testWrongGetId() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            productDao.getID(connection,productEntity);
        } catch (ApplicationRuntimeException e) {
            assertEquals("wrong product name",e.getErrorMessage());
        }
    }
    @Test
    public void testWrongGetQuantity() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            productDao.getQuantity(connection,"laptop");
        } catch (ApplicationRuntimeException e) {
            assertEquals("wrong product name",e.getErrorMessage());
        }
    }
    @Test
    public void testWrongDeleteProduct() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
            productDao.deleteProduct(connection,"Bottle");
        } catch (ApplicationRuntimeException e) {
            assertEquals("product id",e.getErrorMessage());
        }
    }
    @Test
    public void testDisplayUserToDb() throws SQLException, ApplicationRuntimeException {

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        productDao.displayProductsToDb("prashant@gmail.com", connection);
    }

}
