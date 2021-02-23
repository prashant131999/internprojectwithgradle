package eccomapp.controller;

import eccomapp.exception.InvalidInputException;
import eccomapp.service.OrderService;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

public class OrderController {
    OrderService orderService=new OrderService();
    Scanner sc=new Scanner(System.in);
    /**This method presents the order menu
     *
     * @param connection for connecting to database
     * @param logger for logging
     */
    public void controllerOrder(Connection connection, Logger logger)
    {
        System.out.println("Enter 1 for display products to place order");
        System.out.println("Enter 2 for place order ");
        System.out.println("Enter 3 for delete order ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                try {
                    orderService.displayOrder(connection, logger);
                }catch (InvalidInputException e){
                    e.getError();
                }
                break;
            case 2:
                System.out.println("Enter the mobile number of user");
                String number=sc.next();
                try {
                    orderService.createOrder(connection, logger, number);
                }catch (InvalidInputException e){
                    e.getError();
                }
                break;
            case 3:
                System.out.println("Enter the name of product to delete");
                String name=sc.next();
                try {
                    orderService.deleteOrder(connection, logger, name);
                }catch (InvalidInputException e){
                    e.getError();
                }
                break;
        }
    }
}
