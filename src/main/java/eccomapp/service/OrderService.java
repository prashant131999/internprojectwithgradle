package eccomapp.service;

import eccomapp.dao.OrderDao;
import eccomapp.entity.OrderEntity;
import eccomapp.exception.InvalidInputException;
import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class OrderService {
    Scanner sc = new Scanner(System.in);
    OrderDao orderDao = new OrderDao();
    OrderEntity orderEntityObj = new OrderEntity();
    private String listOfProduct, dateCreated, dateDelivered, quantitylist;
    private int totalCost, orderid;

    /**
     * This method place the order by taking product name and mobile number from user
     *
     * @param connection for connecting
     * @param logger     for logging
     * @param number     for mobile number
     */
    public void createOrder(Connection connection, Logger logger, String number) throws InvalidInputException {

        System.out.println("Enter the  product name");
        listOfProduct = sc.next();
        orderEntityObj.setListOfProduct(listOfProduct);
        OrderDao orderDao = new OrderDao();
        orderDao.addOrder(connection, listOfProduct, number);
        logger.info("Order placed");

    }

    /**
     * This method display the list of products to purchase from
     *
     * @param connection for connecting to database
     * @param logger     for logging
     */
    public void displayOrder(Connection connection, Logger logger) throws InvalidInputException {
        OrderDao orderDao = new OrderDao();
        orderDao.display(connection, logger);

    }

    /**
     * This method delete the order by taking name of product
     *
     * @param connection for connecting to database
     * @param logger     for logging
     * @param name       for name of product
     */
    public void deleteOrder(Connection connection, Logger logger, String name) throws InvalidInputException{
        orderDao.deleteOrder(connection, logger, name);

    }
}
