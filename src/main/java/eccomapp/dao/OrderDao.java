package eccomapp.dao;

import eccomapp.controller.OrderController;
import eccomapp.entity.OrderEntity;
import eccomapp.exception.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderDao {
    /** This method add the order to order database
     *
     * @param connection for connection
     * @param name for product name
     * @param number for mobile number
     * @throws InvalidInputException
     */
    public void addOrder(Connection connection,String name,String number) throws InvalidInputException {
        try {
            OrderController orderController = new OrderController();
            UserDao userDao = new UserDao();
            UUID id = userDao.getID(connection, number);
            ProductDao productDao = new ProductDao();
            OrderEntity orderEntity = new OrderEntity();
            float totalCost = productDao.getTotalCostOrder(connection, name);
            String sql = "INSERT INTO orders VALUES(?, ?, ?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, orderEntity.getOrderid());
            statement.setObject(2, id);
            statement.setString(3, name);
            statement.setFloat(4, totalCost);
            statement.executeUpdate();
            System.out.println("Total value of order is " + totalCost);
        }
        catch (SQLException e)
        {
            throw new InvalidInputException(400,"invalid input");
        }
    }

    /** This method display the products
     *
     * @param connection for connection
     * @param logger for logging
     * @throws InvalidInputException
     */
    public void display(Connection connection, Logger logger) throws InvalidInputException {
        try {
            String sql = "SELECT * FROM products ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            int count = 1;
            while (rs.next()) {
                String name = rs.getString(2);
                int quant = rs.getInt(3);
                float totalcost = rs.getFloat(4);
                logger.info("products are  " + count + " " + name + " " + "quantity is  " + quant + "total cost   " + totalcost);
                count++;
            }
        }catch (SQLException e)
        {
            throw new InvalidInputException(400,"invalid input");
        }
    }

    /**This method delete order from the database using product name
     *
     * @param connection for connection to database
     * @param logger for logging
     * @param name for product name
     * @throws InvalidInputException
     */
    public void deleteOrder(Connection connection,Logger logger,String name) throws InvalidInputException {
        try {
            String sql = "DELETE FROM orders WHERE product_list=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
            logger.info("Order is deleted");
        }catch (SQLException e)
        {
            throw new InvalidInputException(400,"wrong product name");
        }
    }

}
