package dao;

import model.EmissionGoal;
import util.ConnectionFactory;
import util.CRUDQueries;
import util.OperationQueries;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EmissionGoalDaoImpl implements EmissionGoalDao {
    // CRUD operations

    @Override
    public EmissionGoal createEmissionGoal(EmissionGoal emissionGoal) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.CREATE_EMISSION_GOAL.getQuery())) {
            statement.setInt(1, emissionGoal.getUserId());
            statement.setDouble(2, emissionGoal.getTargetEmission());
            statement.setDate(3, Date.valueOf(emissionGoal.getStartDate()));
            statement.setDate(4, Date.valueOf(emissionGoal.getEndDate()));
            statement.setString(5, emissionGoal.getStatus());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating emission goal failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emissionGoal;
    }

    @Override
    public EmissionGoal readEmissionGoalById(int emissionGoalId) {
        EmissionGoal emissionGoal = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.READ_EMISSION_GOAL_BY_ID.getQuery())) {
            statement.setInt(1, emissionGoalId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    emissionGoal = extractEmissionGoalFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emissionGoal;
    }

    @Override
    public Set<EmissionGoal> readAllEmissionGoals() {
        Set<EmissionGoal> emissionGoals = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(CRUDQueries.READ_ALL_EMISSION_GOALS.getQuery())) {
            while (resultSet.next()) {
                emissionGoals.add(extractEmissionGoalFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emissionGoals;
    }

    @Override
    public boolean updateEmissionGoal(EmissionGoal emissionGoal) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.UPDATE_EMISSION_GOAL.getQuery())) {
            statement.setInt(1, emissionGoal.getUserId());
            statement.setDouble(2, emissionGoal.getTargetEmission());
            statement.setDate(3, Date.valueOf(emissionGoal.getStartDate()));
            statement.setDate(4, Date.valueOf(emissionGoal.getEndDate()));
            statement.setString(5, emissionGoal.getStatus());
            statement.setInt(6, emissionGoal.getEmissionGoalId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmissionGoal(int emissionGoalId) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.DELETE_EMISSION_GOAL.getQuery())) {
            statement.setInt(1, emissionGoalId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to extract EmissionGoal object from ResultSet
    private EmissionGoal extractEmissionGoalFromResultSet(ResultSet resultSet) throws SQLException {
        int emissionGoalId = resultSet.getInt("goal_id");
        int userId = resultSet.getInt("user_id");
        double targetEmission = resultSet.getDouble("target_emission");
        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
        LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
        String status = resultSet.getString("status");
        return new EmissionGoal(emissionGoalId, userId, targetEmission, startDate, endDate, status);
    }
    // Other operations
    @Override
    public Set<Integer> findUsersExceededEmissionGoals() {
        Set<Integer> userIds = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OperationQueries.FIND_USERS_EXCEEDED_EMISSION_GOALS.getQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                userIds.add(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIds;
    }

    @Override
    public Set<EmissionGoal> findEmissionGoalsWithStatus(String status) {
        Set<EmissionGoal> emissionGoals = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OperationQueries.FIND_EMISSION_GOALS_WITH_STATUS.getQuery())) {
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Assuming you have a method to extract EmissionGoal from ResultSet
                    emissionGoals.add(extractEmissionGoalFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emissionGoals;
    }


}
