package eccomapp.dao;

import eccomapp.entity.OrderEntity;
import eccomapp.exception.ApplicationRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

/**The OrderDao class add the order ,delete order from database according to the inputs given in prepared
 *
 */
public class OrderDao {
    /** This method add the order to order database
     *
     * @param connection for connection
     * @param name for product name
     * @param number for mobile number
     * @throws ApplicationRuntimeException
     */
    public void addOrder(Connection connection,String name,String number) throws ApplicationRuntimeException {
        try {
            UserDao userDao=new UserDao();
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
            throw new ApplicationRuntimeException(400,"invalid input",e.getCause());
        }
    }

    /**This method delete order from the database using product name
     *
     * @param connection for connection to database
     * @param logger for logging
     * @param name for product name
     * @throws ApplicationRuntimeException
     */
    public void deleteOrder(Connection connection,Logger logger,String name) throws ApplicationRuntimeException {
        try {
            String sql = "DELETE FROM orders WHERE product_list=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
            logger.info("Order is deleted");
        }catch (SQLException e)
        {
            throw new ApplicationRuntimeException(400,"wrong product name",e.getCause());
        }
    }

}
