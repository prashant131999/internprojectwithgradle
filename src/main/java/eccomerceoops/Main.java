package eccomerceoops;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Product[] prod = new Product[4];

        //count the number of items
        int count = 0;
        int totalValueCart = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("List of products: \n" +
                    "1. Books\n" +
                    "2. Electronic\n" +
                    "3. Groceceries\n" +
                    "4. Petshop\n" +
                    "Enter the category of product you want:\n" +
                    "To end adding elements in cart type 'Finish'\n" +
                    "To view cart type 'Show'\n"
            );
            Scanner sc = new Scanner(System.in);
            String category = sc.nextLine();
            switch (category) {
                case "Electronic":
                    System.out.println("smart phones");
                    System.out.println("laptops");
                    System.out.println("Enter the Electronic item you want to add in cart");
                    Scanner sc1 = new Scanner(System.in);

                    String electronicitem = sc1.nextLine();
                    switch (electronicitem) {
                        case "smart phones":
                            System.out.println("Enter the quantity:");
                            sc1 = new Scanner(System.in);
                            int quant = sc1.nextInt();
                            prod[count++] = new Electronic("smart phones", 20000, "android",
                                    5, 1000.0F, 500, quant);
                            System.out.println("embedded  to cart");

                            break;
                        case "laptops":
                            System.out.println("Enter the quantity:");
                            sc1 = new Scanner(System.in);
                            quant = sc1.nextInt();
                            prod[count++] = new Electronic("laptops", 30000, "portable",
                                    3, 2000.0F, 999, quant);
                            System.out.println("embedded  to cart");

                            break;

                    }
                    break;
                case "Books":
                    System.out.println("max payne");
                    System.out.println("sherlock holmes");
                    System.out.println("Enter the Books name you want to add in cart");
                    Scanner sc2 = new Scanner(System.in);

                    String bookname = sc2.nextLine();
                    switch (bookname) {
                        case "max payne":
                            System.out.println("Enter the quantity:");
                            sc2 = new Scanner(System.in);
                            int quant = sc2.nextInt();
                            prod[count++] = new Books("max payne", 300, "fiction",
                                    1, 20.0F, 10, quant);
                            System.out.println("embedded  to cart\n");

                            break;
                        case "sherlock holmes":
                            System.out.println("Enter the quantity:");
                            sc2 = new Scanner(System.in);
                            quant = sc2.nextInt();
                            prod[count++] = new Books("sherlock holmes", 500, "sci-fi",
                                    1, 50.0F, 10, quant);
                            System.out.println("embedded  to cart\n");

                            break;

                    }
                    break;
                case "Grocceries":
                    System.out.println("Face wash");
                    System.out.println("Eggs");
                    System.out.println("Enter the Grocceries name you want to add in cart");
                    Scanner sc3 = new Scanner(System.in);

                    String groceriesitem = sc3.nextLine();
                    switch (groceriesitem) {
                        case "Face wash":
                            System.out.println("Enter the quantity:");
                            sc3 = new Scanner(System.in);
                            int quant = sc3.nextInt();
                            prod[count++] = new Groceries("Face wash", 175, "cleansing",
                                    (float) 0.1, 20.0F, 10, quant);
                            System.out.println("embedded  to cart\n");

                            break;
                        case "Eggs":
                            System.out.println("Enter the quantity:");
                            sc3 = new Scanner(System.in);
                            quant = sc3.nextInt();
                            prod[count++] = new Groceries("Eggs", 5, "non veg",
                                    (float) 0.01, 1.0F, 1, quant);
                            System.out.println("embedded  to cart\n");

                            break;

                    }
                    break;
                case "Petshop":
                    System.out.println("cats");
                    System.out.println("dogs");
                    System.out.println("Enter the Pet name you want to add in cart");
                    Scanner sc4 = new Scanner(System.in);

                    String pets = sc4.nextLine();
                    switch (pets) {
                        case "cats":
                            System.out.println("Enter the quantity:");
                            sc4 = new Scanner(System.in);
                            int quant = sc4.nextInt();
                            prod[count++] = new Petshop("cats", 4000, "brown",
                                    (float) 2, 200.0F, 10, quant);
                            System.out.println("embedded  to cart\n");

                            break;
                        case "dogs":
                            System.out.println("Enter the quantity:");
                            sc4 = new Scanner(System.in);
                            quant = sc4.nextInt();
                            prod[count++] = new Petshop("dogs", 5000, "pitbull",
                                    10, 500, 100, quant);
                            System.out.println("embedded  to cart\n");

                            break;

                    }
                    break;
                case "Finish":
                    flag = false;
                    break;
                case "Show":
                    if (count == 0) System.out.println("Cart is empty");
                    else {
                        for (int j = 0; j < count; j++) {
                            if (prod[j] != null) {
                                prod[j].promptsinfo();
                                System.out.println("Tax: " + prod[j].GetTax());
                                System.out.println("Shipping Charge: " + prod[j].GetShipping());
                                System.out.println("Quantity: " + prod[j].GetQuantity());
                                System.out.println("\n");
                                System.out.println("\n");
                                totalValueCart += prod[j].GetTotalPrice();
                            }
                        }


                    }
                    break;


            }
        }
        System.out.println("Your Purchased product");
        if (count == 0) {
            System.out.println("Cart is empty");
        }
        System.out.println("TotalCartValue: " + totalValueCart);


    }
}
