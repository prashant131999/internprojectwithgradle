package eccomapp.dao;

import eccomapp.entity.OrderEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.model.OrderDisplay;

import java.sql.*;
import java.util.UUID;

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
    public void addOrder(Connection connection,String name,String email) throws ApplicationRuntimeException {
        try {
            UserDao userDao=new UserDao();
            UUID id = userDao.getID(connection, email);
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
        }
        catch (SQLException e)
        {
            throw new ApplicationRuntimeException(400,"invalid input",e.getCause());
        }
    }

    /**This method delete order from the database using product name
     *
     * @param connection for connection to database
     * @param name for product name
     * @throws ApplicationRuntimeException
     */
    public void deleteOrder(Connection connection,String name) throws ApplicationRuntimeException {
        try {
            String sql = "DELETE FROM orders WHERE product_list=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
        }catch (SQLException e)
        {
            throw new ApplicationRuntimeException(400,"wrong product name",e.getCause());
        }
    }
    public OrderDisplay displayUsersToDb(String name, Connection connection) throws ApplicationRuntimeException {

        try {
            String q = "select * from orders where product_list=?";
            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            UUID orderId = (UUID) rs.getObject(1);
            UUID customerId = (UUID) rs.getObject(2);
            String prodName = rs.getString(3);
            float totalValue = rs.getFloat(4);
            OrderDisplay orderDisplay=new OrderDisplay(prodName,totalValue,customerId,orderId);
            return orderDisplay;


        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Order not present in database", e);
        }

    }

}
