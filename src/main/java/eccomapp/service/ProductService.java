package eccomapp.service;

import eccomapp.dao.ProductDao;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The Product service class sends the data to Product Dao class to manipulate the database
 * according to needs.
 */
public class ProductService {
    private int quantity;
    private String prodName, prodType, prodDescription;
    private float cost, totalCost;
    Validator validator ;
    ProductEntity productEntity ;
    ProductDao productDao ;
    Scanner sc ;
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
    public void addproduct(Connection connection, Logger logger) throws ApplicationRuntimeException, InvalidInputException {
        logger.info("Enter the product name to add");
        prodName = sc.next();
        productEntity.setProdName(prodName);
        logger.info("Enter the product quantity");
        quantity = sc.nextInt();
        productEntity.setQuantity(quantity);
        validator.validateQuantity(productEntity.getQuantity());
        logger.info("Enter the cost of  product");
        cost = sc.nextFloat();
        totalCost = cost * productEntity.getQuantity();
        productEntity.setTotalCost(totalCost);
        productDao.addProduct(productEntity, connection);
        logger.info("Product added in the cart");


    }

    /**
     * This method deletes the product from database
     *
     * @param connection for connection to database
     * @param name       name of product to delete
     * @param logger     for logging
     */
    public void deleteProduct(Connection connection, String name, Logger logger) {
        productDao.deleteProduct( connection, name);
        int addedquantity = 0;
        addedquantity = productDao.getQuantity(connection, name);
        validator.addQuantity(addedquantity);
        logger.info("Product deleted");
    }

    /**
     * This method update the product name
     *
     * @param connection    for connection
     * @param newName       for new name of product
     * @param oldName       for old name of product
     * @param logger        for logging
     */

    public void updateProductName(Connection connection, String newName, String oldName,
                                  Logger logger) throws ApplicationRuntimeException {
        productEntity.setProdName(newName);
        productDao.updateProductName(connection, newName, oldName);
        logger.info("Product name updated");

    }

    /**
     * This method update the product quantity and total cost according to the quantity
     *
     * @param connection for connection
     * @param name       for name of product to update the quantity
     * @param quantity   for the new quantity
     * @param logger     for logging
     */
    public void updateProductQuantity(Connection connection, String name, int quantity,
                                      Logger logger) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateQuantity(productEntity.getQuantity());
        productDao.updateQuantity(connection, name, quantity, logger);
        logger.info("product quantity updated");
    }


}
