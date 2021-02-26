package eccomapp.dao;

import eccomapp.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestUserDao {
    static UserDao userDao;
    static Connection connection;
    static UserEntity userEntity;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet;
    @BeforeAll
    public static void setup() {
        connection= Mockito.mock(Connection.class);
        userEntity=Mockito.mock(UserEntity.class);
        preparedStatement=Mockito.mock(PreparedStatement.class);
        resultSet=Mockito.mock(ResultSet.class);
        userDao=new UserDao();
    }
    @Test
    public void testCreateUser() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        userDao.createNewUser(userEntity,connection);
    }
    @Test
    public void testGetId() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(userDao.getID(connection,"prashant@gmail.com")).thenReturn(UUID.randomUUID());
    }
    @Test
    public void testUpdate() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        userDao.updateUser(userEntity,connection);
    }
    @Test
    public void testEmailPresent() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(connection.prepareStatement("")).thenReturn(preparedStatement);
        userDao.emailPresent(userEntity, connection);
    }
    @Test
    public void testDeleteUser() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        userDao.deleteUser(userEntity,connection);
    }
}
