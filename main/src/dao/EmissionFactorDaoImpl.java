package dao;

import java.sql.*;

import model.EmissionFactor;
import util.CRUDQueries;
import util.ConnectionFactory;

import java.util.HashSet;
import java.util.Set;

public class EmissionFactorDaoImpl implements EmissionFactorDao {
    @Override
    public EmissionFactor createEmissionFactor(EmissionFactor emissionFactor) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.CREATE_EMISSION_FACTOR.getQuery())) {
            statement.setInt(1, emissionFactor.getEmissionFactorActivityId());
            statement.setDouble(2, emissionFactor.getEmissionFactor());
            statement.setString(3, emissionFactor.getEmissionFactorUnit());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating emission factor failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emissionFactor;
    }

    @Override
    public EmissionFactor readEmissionFactorById(int emissionFactorId) {
        EmissionFactor emissionFactor = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.READ_EMISSION_FACTOR_BY_ID.getQuery())) {
            statement.setInt(1, emissionFactorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    emissionFactor = extractEmissionFactorFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emissionFactor;
    }

    @Override
    public Set<EmissionFactor> readAllEmissionFactors() {
        Set<EmissionFactor> emissionFactors = new HashSet<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(CRUDQueries.READ_ALL_EMISSION_FACTORS.getQuery())) {
            while (resultSet.next()) {
                emissionFactors.add(extractEmissionFactorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emissionFactors;
    }

    @Override
    public boolean updateEmissionFactor(EmissionFactor emissionFactor) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.UPDATE_EMISSION_FACTOR.getQuery())) {
            statement.setInt(1, emissionFactor.getEmissionFactorActivityId());
            statement.setDouble(2, emissionFactor.getEmissionFactor());
            statement.setString(3, emissionFactor.getEmissionFactorUnit());
            statement.setInt(4, emissionFactor.getEmissionFactorId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEmissionFactor(int emissionFactorId) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CRUDQueries.DELETE_EMISSION_FACTOR.getQuery())) {
            statement.setInt(1, emissionFactorId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to extract EmissionFactor object from ResultSet
    private EmissionFactor extractEmissionFactorFromResultSet(ResultSet resultSet) throws SQLException {
        int emissionFactorId = resultSet.getInt("factor_id");
        int activityId = resultSet.getInt("activity_id");
        double factor = resultSet.getDouble("factor");
        String unit = resultSet.getString("unit");
        return new EmissionFactor(emissionFactorId, activityId, factor, unit);
    }
}
