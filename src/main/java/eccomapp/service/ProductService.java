package eccomapp.service;

import eccomapp.dao.ProductDao;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.ProductModel;
import eccomapp.util.Validator;

import java.sql.Connection;
import java.util.Scanner;
import java.util.UUID;

/**
 * The Product service class sends the data to Product Dao class to manipulate the database
 * according to needs.
 */
public class ProductService {
    private int quantity;
    private String prodName, prodType, prodDescription;
    private float cost, totalCost;
    private Validator validator ;
    private ProductEntity productEntity ;
    private ProductDao productDao ;
    private Scanner sc ;
    public ProductService()
    {
        sc=new Scanner(System.in);
        productDao=new ProductDao();
        validator=new Validator();
    }
    public ProductService( ProductDao productDao, Validator validator, ProductEntity productEntity)
    {
        this.productDao=productDao;
        this.validator=validator;
        this.productEntity=productEntity;
    }

    /**
     * This method add the product ,quantity and total cost of product  in database
     *
     * @param connection for connecting to database
     * @param logger     for logging
     * @throws ApplicationRuntimeException
     */
    public void addproduct(Connection connection, ProductEntity productEntity) throws ApplicationRuntimeException, InvalidInputException {
        productEntity.setProductid(UUID.randomUUID());
        validator.validateQuantity(productEntity.getQuantity());
        totalCost = productEntity.getCost() * productEntity.getQuantity();
        productEntity.setTotalCost(totalCost);
        productDao.addProduct(productEntity, connection);
    }

    /**
     * This method deletes the product from database
     *
     * @param connection for connection to database
     * @param name       name of product to delete
     * @param logger     for logging
     */
    public void deleteProduct(Connection connection, String prodName) {
        productDao.deleteProduct( connection, prodName);
        int addedquantity = 0;
        addedquantity = productDao.getQuantity(connection, prodName);
        validator.addQuantity(addedquantity);
    }

    /**
     * This method update the product name
     *
     * @param connection    for connection
     * @param newName       for new name of product
     * @param oldName       for old name of product
     */

    public void updateProductName(Connection connection, String newName, String oldName) throws ApplicationRuntimeException,InvalidInputException {
        productDao.updateProductName(connection, newName, oldName);

    }

    /**
     * This method update the product quantity and total cost according to the quantity
     *
     * @param connection for connection
     * @param name       for name of product to update the quantity
     * @param quantity   for the new quantity
     * @param logger     for logging
     */
    public void updateProductQuantity(Connection connection,String prodName,int quantity) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateQuantity(quantity);
        productDao.updateQuantity(connection, prodName,quantity);
    }


    public ProductModel displayProducts(String productName, Connection connection) throws ApplicationRuntimeException,InvalidInputException {
        return productDao.displayProductsToDb(productName, connection);
    }
}
