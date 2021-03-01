package eccomapp.service;

import eccomapp.dao.OrderDao;
import eccomapp.dao.ProductDao;
import eccomapp.dao.UserDao;
import eccomapp.entity.OrderEntity;
import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.util.Validator;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The OrderService class sends the create ,display,and delete order
 * to database
 */
public class OrderService {
    private Scanner sc;
    private OrderDao orderDao;
    private ProductDao productDao;
    private OrderEntity orderEntity;
    private Validator validator;
    private UserDao userDao;
    private UserEntity userEntity;
    private String listOfProduct, dateCreated, dateDelivered, quantitylist;
    private int totalCost, orderid;

    public OrderService( OrderDao orderDao, ProductDao productDao, OrderEntity orderEntity, Validator validator
            , UserDao userDao, UserEntity userEntity) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.orderEntity = orderEntity;
        this.validator = validator;
        this.userDao = userDao;
        this.userEntity = userEntity;
    }

    public OrderService() {
        sc = new Scanner(System.in);
        orderDao = new OrderDao();
        productDao = new ProductDao();
        orderEntity = new OrderEntity();
        validator = new Validator();
        userDao = new UserDao();
        userEntity = new UserEntity();
    }

    /**
     * This method place the order by taking product name and email from user
     *
     * @param connection for connecting
     * @param logger     for logging
     * @param email      for email of user
     */
    public void createOrder(Connection connection, Logger logger, String email) throws ApplicationRuntimeException, InvalidInputException {
        validator.validateEmailAddress(email);
        logger.info("Enter the  product name");
        listOfProduct = sc.next();
        userEntity.setEmail(email);
        if (userDao.emailPresent(userEntity, connection)) {
            orderEntity.setListOfProduct(listOfProduct);
            orderDao.addOrder(connection, listOfProduct, email);
            logger.info("Order placed");
        } else {
            logger.warning("You are not registered customer");
        }

    }

    /**
     * This method display the list of products to purchase from
     *
     * @param connection for connecting to database
     * @param logger     for logging
     */
    public void displayOrder(Connection connection, Logger logger) throws ApplicationRuntimeException {
        productDao.display(connection, logger);

    }

    /**
     * This method delete the order by taking name of product
     *
     * @param connection for connecting to database
     * @param logger     for logging
     * @param name       for name of product
     */
    public void deleteOrder(Connection connection, Logger logger, String name) throws ApplicationRuntimeException {
        orderDao.deleteOrder(connection, logger, name);

    }
}
