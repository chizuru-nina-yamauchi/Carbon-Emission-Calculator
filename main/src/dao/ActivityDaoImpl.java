package dao;

import model.Activity;
import util.ConnectionFactory;
import util.CRUDQueries;
import util.OperationQueries;

import java.sql.*;
import java.util.*;
/* Using 'PreparedStatement' for the queries that require parameters to prevent SQL injection
 * Using Statement(readAll method) for the queries that do not require parameters(: the queries structure remain constant)
 *
 * */

public class ActivityDaoImpl implements ActivityDao {
    // Methods for CRUD operations
    @Override
    public Activity createActivity(Activity activity) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.CREATE_ACTIVITY.getQuery())) {
            statement.setString(1, activity.getActivityName());
            statement.setString(2, activity.getActivityDescription());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating activity failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }

    @Override
    public Activity readActivityById(int activityId) {
        Activity activity = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.READ_ACTIVITY_BY_ID.getQuery())) {
            statement.setInt(1, activityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    activity = extractActivityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }

    @Override
    public Set<Activity> readAllActivities() {
        Set<Activity> activities = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(CRUDQueries.READ_ALL_ACTIVITIES.getQuery())) {
            while (resultSet.next()) {
                activities.add(extractActivityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public boolean updateActivity(Activity activity) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.UPDATE_ACTIVITY.getQuery())) {
            statement.setString(1, activity.getActivityName());
            statement.setString(2, activity.getActivityDescription());
            statement.setInt(3, activity.getActivityId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteActivity(int activityId) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.DELETE_ACTIVITY.getQuery())) {
            statement.setInt(1, activityId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to extract Activity object from ResultSet
    private Activity extractActivityFromResultSet(ResultSet resultSet) throws SQLException {
        int activityId = resultSet.getInt("activity_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        return new Activity(activityId, name, description);
    }
    // Methods for other operations
    @Override
    public Map<String, Double> compareEmissionsBetweenActivities() {
        Map<String, Double> activityEmissions = new HashMap<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OperationQueries.COMPARE_EMISSIONS_BETWEEN_ACTIVITIES.getQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String activityName = resultSet.getString("activity_name");
                double averageEmission = resultSet.getDouble("average_emission");
                activityEmissions.put(activityName, averageEmission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityEmissions;
    }

    @Override
    public Map<String, Double> listActivitiesAboveThreshold(double threshold) {
        Map<String, Double> activityEmissions = new HashMap<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OperationQueries.LIST_ACTIVITIES_ABOVE_THRESHOLD.getQuery())) {
            statement.setDouble(1, threshold);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String activityName = resultSet.getString("activity_name");
                    double emission = resultSet.getDouble("emission");
                    activityEmissions.put(activityName, emission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityEmissions;
    }

    @Override
    public Map<String, Double> aggregateEmissionsByActivityAndFilterByMinimumEmission(double minimumEmission) {
        Map<String, Double> activityEmissions = new HashMap<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OperationQueries.AGGREGATE_EMISSIONS_BY_ACTIVITY_AND_FILTER_BY_MINIMUM_EMISSION.getQuery())) {
            statement.setDouble(1, minimumEmission);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String activityName = resultSet.getString("activity_name");
                    double totalEmission = resultSet.getDouble("total_emission");
                    activityEmissions.put(activityName, totalEmission);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityEmissions;
    }

    @Override
    public List<String> identifyTop3ActivitiesWithHighestAverageEmissions() {
        List<String> topActivities = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(OperationQueries.IDENTIFY_TOP_3_ACTIVITIES_WITH_HIGHEST_AVERAGE_EMISSIONS.getQuery());
             ResultSet resultSet = statement.executeQuery()) {
            int count = 0;
            while (resultSet.next() && count < 3) {
                String activityName = resultSet.getString("activity_name");
                topActivities.add(activityName);
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topActivities;
    }






}
