package eccomapp.controller;

import eccomapp.dao.ProductDao;
import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.ProductService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ProductController {
    private int quantity;
    private String prodName, prodType, prodDescription;
    private float cost, totalCost;


    Scanner sc = new Scanner(System.in);

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
            productService.validateQuantity(prodEntityObj.getQuantity());
        } catch (Throwable e) {
            e.printStackTrace();
        }
//        System.out.println("Enter the description");
//        prodDescription = sc.next();
//        prodEntityObj.setProdDescription(prodDescription);
        System.out.println("Enter the cost of  product");
        cost = sc.nextFloat();
        totalCost = cost * prodEntityObj.getQuantity();
        prodEntityObj.setTotalCost(totalCost);
        try {
            productDao.addProduct(prodEntityObj, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info("Product added in the cart");


    }

    public void deleteProduct(ProductEntity entity, Connection connection, String name, Logger logger) {
        ProductEntity productEntity = new ProductEntity();
        ProductService productService = new ProductService();
        ProductDao prodDaoObj = new ProductDao();
        try {
            prodDaoObj.deleteProduct(productEntity, connection,name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int addedquantity = 0;
        try {
            addedquantity = prodDaoObj.getQuantity(productEntity, connection,name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        productService.addQuantity(addedquantity);
        logger.info("Product deleted");
    }

    public void updateProductName(ProductEntity entity, Connection connection, String newName, String oldName, Logger logger) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProdName(newName);
        ProductDao productDao=new ProductDao();
        try {
            productDao.updateProductName(productEntity,connection,newName,oldName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info("Product name updated");
        //validate
//        System.out.println("Enter the product quantity");
//        String prodType = sc.next();
//        prodEntityObj.setProdType(prodType);
//        //validate
    }
    public void updateProductQuantity(Connection connection, String name, int quantity,Logger logger)
    {

        ProductDao productDao=new ProductDao();
        ProductService productService=new ProductService();
        ProductEntity prodEntityObj=new ProductEntity();
        try {
            productService.validateQuantity(prodEntityObj.getQuantity());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try {
            productDao.updateQuantity(connection,name,quantity,logger);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        logger.info("product quantity updated");
    }

    public void controllerProduct(Connection connection, Logger logger) {
        System.out.println("Enter 1 to add product");
        System.out.println("Enter 2 to delete product");
        System.out.println("Enter 3 to update product");
        int choice = sc.nextInt();
        switch (choice) {

            case 1:
                try {
                    addproduct(connection,logger);
                } catch (InvalidInputException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                ProductEntity prodEntity=new ProductEntity();
                System.out.println("Enter the name of product to delete");
                String namep=sc.next();
                deleteProduct(prodEntity,connection,namep,logger);
                break;
            case 3:
                //System.out.println("enter the quantity to update");
                //int updatedQuantity=sc.nextInt();
                System.out.println("Enter 1 to update product name");
                System.out.println("Enter 2 to update product quantity");
                int option=sc.nextInt();
                switch (option)
                {
                    case 1:
                        System.out.println("Enter the name of product to update");
                        String oldName = sc.next();
                        System.out.println("Enter the new name of product");
                        String newName=sc.next();
                        ProductEntity productEntity=new ProductEntity();
                        updateProductName(productEntity,connection,newName,oldName,logger);

                        break;
                    case 2:
                        System.out.println("Enter the name of product to update quantity");
                        String name=sc.next();
                        productEntity = new ProductEntity();
                        productEntity.setProdName(name);
                        String names=productEntity.getProdName();
                        System.out.println("enter the new quantity");
                        int quant=sc.nextInt();
                        updateProductQuantity(connection,names,quant,logger);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + option);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }

    }
}
