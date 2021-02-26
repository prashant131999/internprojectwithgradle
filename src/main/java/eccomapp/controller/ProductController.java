package eccomapp.controller;

import eccomapp.entity.ProductEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.exception.InvalidInputException;
import eccomapp.service.ProductService;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

/**The ProductController menu presents the product menu.
 *
 */
public class ProductController {
    ProductService productService=new ProductService();
    ProductEntity productEntity=new ProductEntity();
    /**This method presents the menu to choose the operation to perform
     *
     * @param connection for connecting to database
     * @param logger for logging
     */
    public void controllerProduct(Connection connection, Logger logger) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter 1 to add product");
        System.out.println("Enter 2 to delete product");
        System.out.println("Enter 3 to update product");
        int choice = sc.nextInt();
        switch (choice) {

            case 1:
                try {
                    productService.addproduct(connection,logger);
                } catch (ApplicationRuntimeException e){
                    e.logError();
                } catch (InvalidInputException e) {
                    e.logError();
                }
                break;
            case 2:
                //ProductEntity prodEntity=new ProductEntity();
                System.out.println("Enter the name of product to delete");
                String namep=sc.next();
                try {
                    productService.deleteProduct( connection, namep, logger);
                }catch (ApplicationRuntimeException e){
                    e.logError();
                }
                break;
            case 3:
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
                        try {
                            productService.updateProductName( connection, newName, oldName, logger);
                        }catch (ApplicationRuntimeException e){
                            e.logError();
                        }

                        break;
                    case 2:
                        System.out.println("Enter the name of product to update quantity");
                        String name=sc.next();
                        productEntity = new ProductEntity();
                        productEntity.setProdName(name);
                        String names=productEntity.getProdName();
                        System.out.println("enter the new quantity");
                        int quant=sc.nextInt();
                        try {
                            productService.updateProductQuantity(connection, names, quant, logger);
                        }catch (ApplicationRuntimeException e){
                            e.logError();
                        } catch (InvalidInputException e) {
                            e.logError();
                        }
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
