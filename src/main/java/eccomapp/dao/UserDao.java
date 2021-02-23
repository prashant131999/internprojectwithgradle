package eccomapp.dao;

import eccomapp.entity.UserEntity;
import eccomapp.exception.InvalidInputException;

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
        }
        catch (SQLException e) {
            throw new InvalidInputException(400, "Wrong Input");
        }

    }

    public UUID getID(Connection connection, String number) throws SQLException {
        String sql = "SELECT customer_id FROM customer WHERE mobile_number=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, number);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            UUID id = (UUID) rs.getObject(1);
            return id;
        }
        return null;
    }

    public void updateUser(UserEntity userEntity, Connection connection) throws InvalidInputException {
        try {
            String sql = "UPDATE customer SET address=? WHERE mobile_number=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userEntity.getAddress());
            statement.setString(2, userEntity.getMobileNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidInputException(400, "Wrong Input");
        }
    }

    public boolean emailPresent(UserEntity userEntity, Connection connection) throws InvalidInputException {
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
        }catch (SQLException e)
        {
            throw new InvalidInputException(400,"wrong mail");
        }

return false;
    }

    public void deleteUser(UserEntity userEntity, Connection connection)throws InvalidInputException {
        try {
            String sql = "DELETE FROM customer WHERE email=?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);
            statement.setString(1, userEntity.getEmail());
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new InvalidInputException(400,"wrong mail entered");
        }

    }
}
