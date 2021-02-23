package eccomapp.main;

import eccomapp.controller.OrderController;
import eccomapp.controller.ProductController;
import eccomapp.controller.UserController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static Logger logger;
    private static Connection connection;
    static {
        System.setProperty("java.util.logging.config.file",
                "/home/raramuri/IdeaProjects/testinggradle/src/main/resources/logging.properties");
        logger= java.util.logging.Logger.getLogger(Main.class.getName());
    }
    public static void getConnected()
    {
//        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:5432/ecomprashant",
                    "prashant", "prashants");

            if (connection != null) {
                System.out.println("Connection established");
            } else {
                System.out.println("Connection failed");
            }

        } catch (Exception E) {
            System.out.println(E);
        }

    }
    public static void main(String args[])
        {
     getConnected();
    Scanner sc = new Scanner(System.in);

    boolean flag = true;
        while(flag)
    {
        System.out.println("Enter 1 for user");
        System.out.println("Enter 2 for enter in  cart");
        System.out.println("Enter 3 for products ");
        System.out.println("Enter 4 for exit");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You are inside the user table");
                UserController userobj=new UserController();
                userobj.controllerUser(connection,logger);
                break;
            case 2:
                System.out.println("You are inside the order");
                OrderController orderobj=new OrderController();
                orderobj.controllerOrder(connection,logger);
                break;
            case 3:
                System.out.println("Inside cart");
                ProductController prodobj=new ProductController();
                prodobj.controllerProduct(connection,logger);
                break;
            case 4:
                flag = false;
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }
}
}