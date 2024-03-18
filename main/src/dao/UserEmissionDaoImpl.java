package dao;

import model.UserEmission;
import util.ConnectionFactory;
import util.CRUDQueries;
import util.OperationQueries;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class UserEmissionDaoImpl implements UserEmissionDao {
    // CRUD operations
    @Override
    public UserEmission createUserEmission(UserEmission userEmission) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.CREATE_USER_EMISSION.getQuery())) {
            statement.setInt(1, userEmission.getUserId());
            statement.setInt(2, userEmission.getActivityId());
            statement.setDouble(3, userEmission.getQuantity());
            statement.setDouble(4, userEmission.getEmission());
            statement.setDate(5, Date.valueOf(userEmission.getDate()));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating user emission failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEmission;
    }

    @Override
    public UserEmission readUserEmissionById(int emissionId) {
        UserEmission userEmission = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.READ_USER_EMISSION_BY_ID.getQuery())) {
            statement.setInt(1, emissionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userEmission = extractUserEmissionFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEmission;
    }

    @Override
    public Set<UserEmission> readAllUserEmissions() {
        Set<UserEmission> userEmissions = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(CRUDQueries.READ_ALL_USER_EMISSIONS.getQuery())) {
            while (resultSet.next()) {
                userEmissions.add(extractUserEmissionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEmissions;
    }

    @Override
    public boolean updateUserEmission(UserEmission userEmission) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.UPDATE_USER_EMISSION.getQuery())) {
            statement.setInt(1, userEmission.getUserId());
            statement.setInt(2, userEmission.getActivityId());
            statement.setDouble(3, userEmission.getQuantity());
            statement.setDouble(4, userEmission.getEmission());
            statement.setDate(5, Date.valueOf(userEmission.getDate()));
            statement.setInt(6, userEmission.getEmissionId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUserEmission(int emissionId) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.DELETE_USER_EMISSION.getQuery())) {
            statement.setInt(1, emissionId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to extract UserEmission object from ResultSet
    private UserEmission extractUserEmissionFromResultSet(ResultSet resultSet) throws SQLException {
        int emissionId = resultSet.getInt("emission_id");
        int userId = resultSet.getInt("user_id");
        int activityId = resultSet.getInt("activity_id");
        double quantity = resultSet.getDouble("quantity");
        double emission = resultSet.getDouble("emission");
        Date date = resultSet.getDate("date");
        return new UserEmission(emissionId, userId, activityId, quantity, emission, date.toLocalDate());
    }

    @Override
    public double calculateTotalEmissionsForUser(int userId) {
        double totalEmissions = 0;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OperationQueries.CALCULATE_TOTAL_EMISSIONS_FOR_USER.getQuery())) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalEmissions = resultSet.getDouble("total_emission");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalEmissions;
    }

    // Other operations
    @Override
    public Map<Integer, Double> calculateMonthlyEmissionsForUser(int userId) {
        Map<Integer, Double> monthlyEmissions = new HashMap<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OperationQueries.CALCULATE_MONTHLY_EMISSIONS_FOR_USER.getQuery())) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int month = resultSet.getInt("month");
                    double monthlyEmission = resultSet.getDouble("monthly_emission");
                    monthlyEmissions.put(month, monthlyEmission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyEmissions;
    }



}
