package dao;

import model.User;
import util.ConnectionFactory;
import util.CRUDQueries;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    @Override
    public User createUser(User user) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.CREATE_USER.getQuery())) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User readUserById(int userId) {
        User user = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.READ_USER_BY_ID.getQuery())) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Set<User> readAllUsers() {
        Set<User> users = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(CRUDQueries.READ_ALL_USERS.getQuery())) {
            while (resultSet.next()) {
                users.add(extractUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.UPDATE_USER.getQuery())) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.DELETE_USER.getQuery())) {
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to extract User object from ResultSet
    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password_hash");
        return new User(userId, username, email, password);
    }

}
