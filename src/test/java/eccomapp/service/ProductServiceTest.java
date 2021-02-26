package eccomapp.service;

import eccomapp.dao.ProductDao;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.mockito.Mockito.when;

public class ProductServiceTest {
    static ProductService productService;
    static Connection connection;
    static Logger logger;
    static Validator validator ;
    static ProductEntity productEntity ;
    static ProductDao productDao ;
    static Scanner sc ;
    @BeforeAll
    public static void setup() {
        //Scanner sc=new Scanner(System.in);
        connection = Mockito.mock(Connection.class);
        logger = Mockito.mock(Logger.class);
        productDao = Mockito.mock(ProductDao.class);
        validator = Mockito.mock(Validator.class);
        productEntity=Mockito.mock(ProductEntity.class);
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
