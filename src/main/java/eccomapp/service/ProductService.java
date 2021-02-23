package eccomapp.service;

import eccomapp.dao.ProductDao;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class ProductService {
    private int quantity;
    private String prodName, prodType, prodDescription;
    private float cost, totalCost;

    Validator validator = new Validator();
    ProductEntity productEntity = new ProductEntity();

    Scanner sc = new Scanner(System.in);

    /**
     * This method add the product ,quantity and total cost of product  in database
     *
     * @param connection for connecting to database
     * @param logger     for logging
     * @throws InvalidInputException
     */
    public void addproduct(Connection connection, Logger logger) throws InvalidInputException {

        ProductEntity prodEntityObj = new ProductEntity();
        ProductService productService = new ProductService();
        ProductDao productDao = new ProductDao();
        System.out.println("Enter the product name to add");
        prodName = sc.next();
        prodEntityObj.setProdName(prodName);
        System.out.println("Enter the product quantity");
        quantity = sc.nextInt();
        prodEntityObj.setQuantity(quantity);
        try {
            validator.validateQuantity(prodEntityObj.getQuantity());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("Enter the cost of  product");
        cost = sc.nextFloat();
        totalCost = cost * prodEntityObj.getQuantity();
        prodEntityObj.setTotalCost(totalCost);
        productDao.addProduct(prodEntityObj, connection);
        logger.info("Product added in the cart");


    }

    /**
     * This method deletes the product from database
     *
     * @param entity     for entity objects
     * @param connection for connection to database
     * @param name       name of product to delete
     * @param logger     for logging
     */
    public void deleteProduct(ProductEntity entity, Connection connection, String name, Logger logger) {
        ProductEntity productEntity = new ProductEntity();
        ProductService productService = new ProductService();
        ProductDao prodDaoObj = new ProductDao();
        prodDaoObj.deleteProduct(productEntity, connection, name);
        int addedquantity = 0;
        addedquantity = prodDaoObj.getQuantity(productEntity, connection, name);
        validator.addQuantity(addedquantity);
        logger.info("Product deleted");
    }

    /**
     * This method update the product name
     *
     * @param productEntity for entity object
     * @param connection    for connection
     * @param newName       for new name of product
     * @param oldName       for old name of product
     * @param logger        for logging
     */

    public void updateProductName(ProductEntity productEntity, Connection connection, String newName, String oldName,
                                  Logger logger) throws InvalidInputException {

        productEntity.setProdName(newName);
        ProductDao productDao = new ProductDao();
        productDao.updateProductName(productEntity, connection, newName, oldName);
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
                                      Logger logger) throws InvalidInputException {

        ProductDao productDao = new ProductDao();
        ProductService productService = new ProductService();
        ProductEntity prodEntityObj = new ProductEntity();
        validator.validateQuantity(prodEntityObj.getQuantity());
        productDao.updateQuantity(connection, name, quantity, logger);
        logger.info("product quantity updated");
    }


}
