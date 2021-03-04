package eccomapp.dao;

import eccomapp.entity.UserEntity;
import eccomapp.exception.ApplicationRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TestUserDao {
    private UserDao userDao;
    private Connection connection = Mockito.mock(Connection.class);
    private UserEntity userEntity = Mockito.mock(UserEntity.class);
    private PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    private ResultSet resultSet = Mockito.mock(ResultSet.class);

    @BeforeEach
    public  void setup() {
        userDao = new UserDao();
    }

    @Test
    public void testCreateUser() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        userDao.createNewUser(userEntity, connection);
    }

    @Test
    public void testGetId() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(userDao.getID(connection, "prashant@gmail.com")).thenReturn(UUID.randomUUID());
    }

    @Test
    public void testUpdate() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        userDao.updateUser(userEntity, connection);
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
        userDao.deleteUser(userEntity, connection);
    }

    @Test
    public void testWrongEmail() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            userDao.emailPresent(userEntity, connection);
        }
        catch (ApplicationRuntimeException e)
        {
            assertEquals("wrong mail",e.getErrorMessage());
        }

    }
    @Test
    public void testWrongUpdate() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
            userDao.updateUser(userEntity,connection);
        } catch (ApplicationRuntimeException e) {
            assertEquals("wrong mail",e.getErrorMessage());
        }
    }
    @Test
    public void testWrongCreateUser() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
            userDao.createNewUser(userEntity,connection);
        } catch (ApplicationRuntimeException e) {
            assertEquals("Server error",e.getErrorMessage());
        }
    }
    @Test
    public void testWrongDeleteUser() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeUpdate()).thenThrow(new SQLException());
            userDao.deleteUser(userEntity, connection);
        } catch (ApplicationRuntimeException e) {
            assertEquals("wrong mail entered",e.getErrorMessage());
        }
    }
    @Test
    public void testWrongGetId() throws SQLException {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenThrow(new SQLException());
            userDao.getID(connection,"prashant@gmail.com");
        } catch (ApplicationRuntimeException e) {
            assertEquals("wrong mail",e.getErrorMessage());
        }
    }


}
