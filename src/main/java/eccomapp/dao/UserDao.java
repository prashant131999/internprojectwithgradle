package eccomapp.dao;

import eccomapp.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDao {
    public UserDao(){
    }
    public void createNewUser(String fname,String lname,String email,
                              String mobileNumber,String address,
                              String dateOfBirth, UserEntity userEntity,Connection connection) throws SQLException {

            String sql = "INSERT INTO customer VAlUES(?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1,userEntity.getUserid());
            statement.setString(2, fname);
            statement.setString(3, lname);
            statement.setString(4, address);
            statement.setString(5, mobileNumber);
            statement.setString(6, email);
            statement.setString(7, dateOfBirth);
            statement.executeUpdate();
        }

    }
    public  UUID getID(Connection connection, String number) throws SQLException {
        String sql="SELECT customer_id FROM customer WHERE mobile_number=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,number);
        ResultSet rs= statement.executeQuery();
        while(rs.next())
        {
            UUID id= (UUID) rs.getObject(1);
            return id;
        }
        return null;
    }
    public void updateUser(UserEntity userEntity,Connection connection) throws SQLException {
      String sql="UPDATE customer SET address=? WHERE mobile_number='9639402926'";
      PreparedStatement statement=connection.prepareStatement(sql);
      statement.setString(1,userEntity.getAddress());
      statement.executeUpdate();
    }
    public void deleteUser(UserEntity userEntity,Connection connection)
    {
        String sql="DELETE FROM customer WHERE email=?";
        PreparedStatement statement= null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.setString(1,userEntity.getEmail());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
