package eccomapp.service;

import eccomapp.dao.ProductDao;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.ProductModel;
import eccomapp.util.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.logging.Logger;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    private static ProductService productService;
    private static Connection  connection = Mockito.mock(Connection.class);
    private static Logger logger = Mockito.mock(Logger.class);
    private static Validator validator = Mockito.mock(Validator.class);
    private static ProductEntity productEntity=Mockito.mock(ProductEntity.class) ;
    private static ProductDao productDao = Mockito.mock(ProductDao.class);
    private static ProductModel productModel=Mockito.mock(ProductModel.class);
    @BeforeAll
    public static void setup() {
        productService = new ProductService(productDao,validator,productEntity);
    }
    @Test
    public void testAddProduct() throws InvalidInputException {
        doNothing().when(validator).validateQuantity(20);
        when(productEntity.getCost()).thenReturn(40.5F);
        when(productEntity.getQuantity()).thenReturn(2);
        productService.addproduct(connection,productEntity);
    }
    @Test
    public void testDeleteProduct()
    {
        when(productDao.getQuantity(connection,"prashant")).thenReturn(1);
        productService.deleteProduct(connection,"laptop");
    }
    @Test
    public void testUpdateProduct() throws InvalidInputException {
        productService.updateProductName(connection,"prajuwal","prashant");
    }
    @Test
    public void testUpdateQuantity() throws InvalidInputException {
        productService.updateProductQuantity(connection,"samsung",10);
    }
    @Test
    public void testDisplayProducts() throws InvalidInputException
    {
        when(productDao.displayProductsToDb("laptop",connection)).thenReturn(productModel);
        productService.displayProducts("laptop",connection);
    }
}
