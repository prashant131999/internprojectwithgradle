package eccomapp.dao;

import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import eccomapp.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDao {
    public UserDao() {
    }

    public void createNewUser(UserEntity userEntity, Connection connection) throws RuntimeException {

        String sql = "INSERT INTO customer VAlUES(?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, userEntity.getUserid());
            statement.setString(2, userEntity.getFname());
            statement.setString(3, userEntity.getLname());
            statement.setString(4, userEntity.getAddress());
            statement.setString(5, userEntity.getMobileNumber());
            statement.setString(6, userEntity.getEmail());
            statement.setString(7, userEntity.getDateOfBirth());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Server error", e.getCause());
        }

    }

    public UUID getID(Connection connection, String email) throws ApplicationRuntimeException {
        UserEntity userEntity=new UserEntity();
        userEntity.setEmail(email);
        try {
                String sql = "SELECT customer_id FROM customer WHERE email=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    UUID id = (UUID) rs.getObject(1);
                    return id;
                }
            }
        catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "wrong mail", e.getCause());

        }
        return null;
    }

    public void updateUser(UserModel userModel, Connection connection) throws ApplicationRuntimeException {
        try {
            String sql = "UPDATE customer SET address=? WHERE email=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userModel.getAddress());
            statement.setString(2, userModel.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "wrong mobile number", e.getCause());
        }
    }

    public boolean emailPresent(UserEntity userEntity, Connection connection) throws ApplicationRuntimeException {
        try {
            String sql = "SELECT * FROM customer";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String email = rs.getString(6);
                if (email == userEntity.getEmail()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "wrong mail", e.getCause());
        }

        return false;
    }
    public void deleteUser(String email, Connection connection) throws ApplicationRuntimeException  {
        try {
            String sql = "DELETE FROM customer WHERE email=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "User Cant be deleted", e.getCause());
        }
    }
    public UserEntity displayUsersToDb(String email, Connection connection) throws ApplicationRuntimeException {

        try {
            String q = "select * from customer where email=?";
            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
                String fName = rs.getString(2);
                String lName = rs.getString(3);
                String address = rs.getString(4);
                String mobile = rs.getString(5);
                String emailId = rs.getString(6);
                String dob = rs.getString(7);
                UserEntity userEntity=new UserEntity(fName,lName,emailId,dob,address,mobile);
            return userEntity;


        } catch (SQLException e) {
            throw new ApplicationRuntimeException(500, "Customer not present in database", e);
        }

    }
    public boolean checkEmailPresence(String email,Connection connection) throws ApplicationRuntimeException {
        try {
            int count=0;
            String q = "select first_name from customer where email=?";
            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                count++;
            }
            if(count>0)
            {
                return true;
            }
        } catch (SQLException e) {
           throw new ApplicationRuntimeException(400,"Wrong email",e);
        }

      return false;
    }
    public boolean checkPresence(String mobileNumber,Connection connection) throws ApplicationRuntimeException {
        try {
            int count=0;
            String q = "select first_name from customer where mobile_number=?";
            PreparedStatement pstmt = connection.prepareStatement(q);
            pstmt.setString(1, mobileNumber);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                count++;
            }
            if(count>0)
            {
                return true;
            }
        } catch (SQLException e) {
            throw new ApplicationRuntimeException(400,"Wrong number",e.getCause());
        }

        return false;
    }
}
