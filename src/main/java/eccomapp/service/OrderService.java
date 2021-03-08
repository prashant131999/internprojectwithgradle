package eccomapp.service;

import eccomapp.dao.OrderDao;
import eccomapp.dao.ProductDao;
import eccomapp.dao.UserDao;
import eccomapp.entity.OrderEntity;
import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.model.OrderDisplay;
import eccomapp.util.Validator;

import java.sql.Connection;
import java.util.Scanner;
import java.util.UUID;

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
     * @param email      for email of user
     */
    public void createOrder(Connection connection, String email,String listOfProduct) throws ApplicationRuntimeException, InvalidInputException {
        orderEntity.setOrderid(UUID.randomUUID());
        validator.validateEmailAddress(email);
        orderDao.addOrder(connection, listOfProduct, email);
    }

    /**
     * This method delete the order by taking name of product
     *
     * @param connection for connecting to database
     * @param name       for name of product
     */
    public void deleteOrder(Connection connection, String name) throws ApplicationRuntimeException,InvalidInputException {
        orderDao.deleteOrder(connection, name);

    }

    public OrderDisplay displayOrderDetail(String name, Connection connection)throws ApplicationRuntimeException,InvalidInputException {
        return orderDao.displayOrdersToDb(name, connection);
    }
    public void deleteOrderById(UUID custId,Connection connection)
    {
        orderDao.deleteOrderByCustIdToDb(custId,connection);
    }
}
