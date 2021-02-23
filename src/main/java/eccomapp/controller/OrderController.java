package eccomapp.controller;

import eccomapp.dao.OrderDao;
import eccomapp.entity.OrderEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class OrderController {
    Scanner sc = new Scanner(System.in);
    private String listOfProduct,dateCreated,dateDelivered,quantitylist;
    private int totalCost,orderid;

    public void createOrder(Connection connection,Logger logger,String number)
    {
        OrderEntity orderEntityObj =new OrderEntity();
        System.out.println("Enter the  product name");
        listOfProduct=sc.next();
        orderEntityObj.setListOfProduct(listOfProduct);
        OrderDao orderDao=new OrderDao();
        try {
            orderDao.addOrder(connection,listOfProduct,number);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info("Order placed");

    }
    public void displayOrder(Connection connection,Logger logger)
    {
        OrderDao orderDao=new OrderDao();
        try {
            orderDao.display(connection, logger);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void deleteOrder(Connection connection,Logger logger,String name)
    {
     OrderDao orderDao=new OrderDao();
        try {
            orderDao.deleteOrder(connection,logger,name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void controllerOrder(Connection connection, Logger logger)
    {
        System.out.println("Enter 1 for display cart");
        System.out.println("Enter 2 for place order ");
        System.out.println("Enter 3 for delete order ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                displayOrder(connection,logger);
                break;
            case 2:
                System.out.println("Enter the mobile number of user");
                String number=sc.next();
                createOrder(connection,logger,number);
                break;
            case 3:
                System.out.println("Enter the name of product to delete");
                String name=sc.next();
                deleteOrder(connection,logger,name);
                break;
            case 4:
                break;
        }
    }
}
