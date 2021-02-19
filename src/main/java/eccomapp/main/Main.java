package eccomapp.main;

import eccomapp.controller.OrderController;
import eccomapp.controller.ProductController;
import eccomapp.controller.UserController;

import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);

    boolean flag = true;
        while(flag)
    {
        System.out.println("Enter 1 for user");
        System.out.println("Enter 2 for price on order");
        System.out.println("Enter 3 for products ");
        System.out.println("Enter 4 for exit");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You are inside the user table");
                UserController userobj=new UserController();

                break;
            case 2:
                System.out.println("You are inside the order");
                OrderController orderobj=new OrderController();
                break;
            case 3:
                System.out.println("Inside product");
                ProductController prodobj=new ProductController();
                break;
            case 4:
                flag = false;
                break;
        }
    }
}