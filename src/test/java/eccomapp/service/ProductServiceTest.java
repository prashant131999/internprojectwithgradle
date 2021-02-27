package eccomapp.service;

import eccomapp.dao.ProductDao;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private static ProductService productService;
    private static Connection  connection = Mockito.mock(Connection.class);
    private static Logger logger = Mockito.mock(Logger.class);
    private static Validator validator = Mockito.mock(Validator.class);
    private static ProductEntity productEntity=Mockito.mock(ProductEntity.class) ;
    private static ProductDao productDao = Mockito.mock(ProductDao.class);
    @BeforeAll
    public static void setup() {
        productService = new ProductService(productDao,validator,productEntity);
    }
    @Test
    public void testDeleteProduct()
    {
        when(productDao.getQuantity(connection,"prashant")).thenReturn(1);
        productService.deleteProduct(connection,"prashant",logger);
    }
    @Test
    public void testUpdateProduct()
    {
        productService.updateProductName(connection,"prajuwal","prashant",logger);
    }
    @Test
    public void testUpdateQuantity() throws InvalidInputException {
        productService.updateProductQuantity(connection,"prashant",12,logger);
    }
}
