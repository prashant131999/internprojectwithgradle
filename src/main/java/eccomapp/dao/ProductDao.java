package eccomapp.dao;

import eccomapp.entity.ProductEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.model.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**The ProductDao add the products ,display products ,get id from product database.
 */

public class ProductDao {
    private int name;

    /**
     * This method add the product to the database by executing the prepared statement
     *
     * @param productEntity for entity object
     * @param connection    for connecting to database
     * @throws SQLException
     */
    public void addProduct(ProductEntity productEntity, Connection connection) throws ApplicationRuntimeException {
        try {
            String sql = "INSERT INTO products(prod_id, product_name, prod_quantity, product_cost) VAlUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, productEntity.getProdid());
            statement.setString(2, productEntity.getProdName());
            statement.setInt(3, productEntity.getQuantity());
            statement.setFloat(4, productEntity.getTotalCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(400, "invalid input",e.getCause());
        }

    }
    /**
     * This method returns the UUID of product by taking product name as input
     *
     * @param connection for connection
     * @param name for product name
     * @return UUID
     * @throws SQLException
     */
    public  UUID getID(Connection connection, ProductEntity productEntity) throws ApplicationRuntimeException {
        try {
            String sql = "SELECT prod_id FROM products WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, productEntity.getProdName());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UUID id = (UUID) rs.getObject("prod_id");
                return id;
            }
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(400, "wrong product name",e.getCause());
        }
        return null;

    }

    /**This method return the quantity of given product name
     *
     * @param connection for connection
     * @param name for product name
     * @return quantity
     * @throws ApplicationRuntimeException
     */
    public  int getQuantity(Connection connection, String name) throws ApplicationRuntimeException {
        try {
            String sql = "SELECT prod_quantity FROM products WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int quant = rs.getInt("prod_quantity");
                return quant;

            }
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(400, "wrong product name",e.getCause());
        }
        return 0;
    }

    /**This method deletes the product from database
     *
     * @param connection for connecting to database
     * @param name for product name
     * @throws ApplicationRuntimeException
     */
    public void deleteProduct(Connection connection, String prodName) throws ApplicationRuntimeException {
        try {
            String sql = "DELETE FROM products WHERE prod_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, getIDByName(connection, prodName));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(400, "product id",e.getCause());
        }
    }
    /**This method updates the product name from database
     *
     * @param productEntity for objects
     * @param connection for connecting to database
     * @param oldname for product name
     * @throws ApplicationRuntimeException
     */

    public void updateProductName(Connection connection, String newName,
                                  String oldName) throws ApplicationRuntimeException {
        try {
            String sql = "UPDATE products SET product_name=? WHERE prod_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setObject(2, getIDByName( connection, oldName));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(400, "wrong product id",e.getCause());
        }
    }

    public  float getTotalCost(Connection connection, String name) throws SQLException {
        int oldquantity = getQuantity( connection, name);
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
     * @throws ApplicationRuntimeException
     */
    public void updateQuantity(Connection connection, String prodName,int newQuantity) throws ApplicationRuntimeException {
        try {
            float cost = getTotalCost(connection, prodName);
            float totalCost = newQuantity * cost;
            String sql = "UPDATE products SET prod_quantity=?,product_cost=? WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newQuantity);
            statement.setFloat(2, totalCost);
            statement.setString(3, prodName);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new ApplicationRuntimeException(400, "product id",e.getCause());
        }
    }
    /**
     * This method returns the UUID of product by taking product name as input
     *
     * @param connection for connection
     * @param name for product name
     * @return UUID
     * @throws SQLException
     */
    public  UUID getIDByName(Connection connection, String name) throws ApplicationRuntimeException {
        try {
            String sql = "SELECT prod_id FROM products WHERE product_name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UUID id = (UUID) rs.getObject("prod_id");
                return id;
            }
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(400, "wrong product name",e.getCause());
        }
        return null;

    }
    public ProductModel displayProductsToDb(String name, Connection connection) throws ApplicationRuntimeException {

        try {
            String q = "select * from products where  product_name=?";
            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            UUID prodId = (UUID) rs.getObject(1);
            String prodName = rs.getString(2);
            int quant  = rs.getInt(3);
            float totalcost = rs.getFloat(4);
            ProductModel productModel=new ProductModel(prodName,quant,totalcost,prodId);
            return productModel;


        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Product not present in database", e);
        }

    }

}
