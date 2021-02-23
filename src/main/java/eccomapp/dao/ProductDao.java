package eccomapp.dao;

import eccomapp.entity.ProductEntity;
import eccomapp.exception.InvalidInputException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

public class ProductDao {
    private int name;

    /**
     * This method add the product to the database by executing the prepared statement
     *
     * @param productEntity for entity object
     * @param connection    for connecting to database
     * @throws SQLException
     */
    public void addProduct(ProductEntity productEntity, Connection connection) throws InvalidInputException {
        try {
            String sql = "INSERT INTO products(prod_id, product_name, prod_quantity, product_cost) VAlUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, productEntity.getProdid());
            statement.setString(2, productEntity.getProdName());
            statement.setInt(3, productEntity.getQuantity());
            statement.setFloat(4, productEntity.getTotalCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidInputException(400, "invalid input");
        }

    }

    /**
     * This method returns the UUID of product by taking product name as input
     *
     * @param productEntity for product entity object
     * @param connection for connection
     * @param name for product name
     * @return UUID
     * @throws SQLException
     */
    public static UUID getID(ProductEntity productEntity, Connection connection, String name) throws InvalidInputException {
        try {
            String sql = "SELECT prod_id FROM products WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UUID id = (UUID) rs.getObject(1);
                return id;
            }
        } catch (SQLException e) {
            throw new InvalidInputException(400, "wrong product name");
        }
        return null;

    }

    /**This method return the quantity of given product name
     *
     * @param productEntity for objects
     * @param connection for connection
     * @param name for product name
     * @return quantity
     * @throws InvalidInputException
     */
    public static int getQuantity(ProductEntity productEntity, Connection connection, String name) throws InvalidInputException {
        try {
            String sql = "SELECT prod_quantity FROM products WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int quant = rs.getInt(1);
                return quant;

            }
        } catch (SQLException e) {
            throw new InvalidInputException(400, "wrong product name");
        }
        return 0;
    }

    /**This method deletes the product from database
     *
     * @param productEntity for objects
     * @param connection for connecting to database
     * @param name for product name
     * @throws InvalidInputException
     */
    public void deleteProduct(ProductEntity productEntity, Connection connection, String name) throws InvalidInputException {
        try {
            String sql = "DELETE FROM products WHERE prod_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, getID(productEntity, connection, name));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidInputException(400, "product id");
        }
    }
    /**This method updates the product name from database
     *
     * @param productEntity for objects
     * @param connection for connecting to database
     * @param oldname for product name
     * @throws InvalidInputException
     */

    public void updateProductName(ProductEntity productEntity, Connection connection, String newName,
                                  String oldName) throws InvalidInputException {
        try {
            String sql = "UPDATE products SET product_name=? WHERE prod_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setObject(2, getID(productEntity, connection, oldName));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidInputException(400, "wrong product id");
        }
    }

    public static float getTotalCost(Connection connection, String name) throws SQLException {
        ProductEntity productEntity = new ProductEntity();
        int oldquantity = getQuantity(productEntity, connection, name);
        String sql = "SELECT product_cost FROM products WHERE product_name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            float cost = rs.getFloat(1);
            return (cost / oldquantity);
        }
        return 0;
    }

    public float getTotalCostOrder(Connection connection, String name) throws SQLException {
        String sql = "SELECT product_cost FROM products WHERE product_name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            float cost = rs.getFloat(1);
            return cost;
        }
        return 0;
    }
    /**This method updates the  product quantity from database
     *
     * @param connection for connecting to database
     * @param name for product name
     * @param newQuantity for new quantity
     * @param logger for logging
     * @throws InvalidInputException
     */
    public void updateQuantity(Connection connection, String name, int newQuantity,
                               Logger logger) throws InvalidInputException {
        try {
            float cost = getTotalCost(connection, name);
            float totalCost = newQuantity * cost;
            String sql = "UPDATE products SET prod_quantity=?,product_cost=? WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newQuantity);
            statement.setFloat(2, totalCost);
            statement.setString(3, name);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new InvalidInputException(400, "product id");
        }
    }

}
