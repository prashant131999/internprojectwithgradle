package eccomapp.dao;

import eccomapp.entity.ProductEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

public class ProductDao {
    private int name;

    public void addProduct(ProductEntity productEntity, Connection connection) throws SQLException {

        String sql = "INSERT INTO products(prod_id, product_name, prod_quantity, product_cost) VAlUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,productEntity.getProdid());
        statement.setString(2, productEntity.getProdName());
        statement.setInt(3,productEntity.getQuantity());
        statement.setFloat(4,productEntity.getTotalCost());
        statement.executeUpdate();
    }
    public static UUID getID(ProductEntity productEntity, Connection connection,String name) throws SQLException {
        String sql="SELECT prod_id FROM products WHERE product_name=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,name);
        ResultSet rs= statement.executeQuery();
        while(rs.next())
        {
            UUID id= (UUID) rs.getObject(1);
            return id;
        }
        return null;
    }
    public  static int  getQuantity(ProductEntity productEntity, Connection connection,String name) throws SQLException {
        String sql="SELECT prod_quantity FROM products WHERE product_name=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,name);
        ResultSet rs= statement.executeQuery();
        while(rs.next())
        {
            int quant=rs.getInt(1);
            return quant;

        }
        return 0;
    }
    public void deleteProduct(ProductEntity productEntity,Connection connection,String name) throws SQLException
    {
        String sql="DELETE FROM products WHERE prod_id=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setObject(1,getID(productEntity,connection,name));
        statement.executeUpdate();
    }
    public void updateProductName(ProductEntity productEntity,Connection connection,String newName,
                                  String oldName) throws SQLException
    {
        String sql="UPDATE products SET product_name=? WHERE prod_id=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,newName);
        statement.setObject(2,getID(productEntity,connection,oldName));
        statement.executeUpdate();
    }

    public static float getTotalCost(Connection connection,String name) throws SQLException {
        ProductEntity productEntity=new ProductEntity();
        int oldquantity=getQuantity(productEntity,connection,name);
        String sql="SELECT product_cost FROM products WHERE product_name=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,name);
        ResultSet rs= statement.executeQuery();
        while(rs.next())
        {
            float cost= rs.getFloat(1);
            return (cost/oldquantity);
        }
        return 0;
    }
    public  float getTotalCostOrder(Connection connection,String name) throws SQLException {
        String sql="SELECT product_cost FROM products WHERE product_name=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,name);
        ResultSet rs= statement.executeQuery();
        while(rs.next())
        {
            float cost= rs.getFloat(1);
            return cost;
        }
        return 0;
    }
    public void updateQuantity(Connection connection, String name, int newQuantity,
                               Logger logger) throws SQLException {
        float cost=getTotalCost(connection,name);
        float totalCost=newQuantity*cost;
        String sql="UPDATE products SET prod_quantity=?,product_cost=? WHERE product_name=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1,newQuantity);
        statement.setFloat(2,totalCost);
        statement.setString(3,name);
        statement.executeUpdate();
    }

}
