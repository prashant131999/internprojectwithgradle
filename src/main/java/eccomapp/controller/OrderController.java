package eccomapp.controller;

import eccomapp.entity.OrderEntity;

import java.util.Scanner;

public class OrderController {
    Scanner sc = new Scanner(System.in);
    private String listOfProduct,dateCreated,dateDelivered,quantitylist;
    private int totalCost,orderid;
    public OrderController(String listOfProduct,String dateCreated,String dateDelivered,int totalCost
            ,int orderid,String quantitylist)
    {
        super(listOfProduct, dateCreated, dateDelivered, totalCost, orderid, quantitylist);
    }
    OrderEntity[] orderobj=new OrderEntity[4];
    int count=0;
    public void createOrder()
    {
        System.out.println("Enter the order id");
        orderid=sc.nextInt();
        System.out.println("Enter the list of product");
        listOfProduct=sc.next();
        System.out.println("Enter the date it  was created");
        dateCreated=sc.next();
        System.out.println("Enter the date it  was delivered");
        dateDelivered=sc.next();
        System.out.println("Enter the total value of order");
        totalCost=sc.nextInt();
        System.out.println("Enter the quantity list");
        quantitylist=sc.next();

    }
    boolean flag = true;
        while(flag)
    {
        System.out.println("Enter 1 for create order");
        System.out.println("Enter 2 for delete order");
        System.out.println("Enter 3 for update order ");
        System.out.println("Enter 4 for exit");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("You are inside the create order");
                createOrder();
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
