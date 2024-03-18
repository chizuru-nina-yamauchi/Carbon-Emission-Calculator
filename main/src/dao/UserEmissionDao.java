package dao;

import model.UserEmission;

import java.util.Map;
import java.util.Set;

public interface UserEmissionDao {
    // CRUD operations
    UserEmission createUserEmission(UserEmission userEmission);
    UserEmission readUserEmissionById(int emissionId);
    Set<UserEmission> readAllUserEmissions();
    boolean updateUserEmission(UserEmission userEmission);
    boolean deleteUserEmission(int emissionId);

    // Other operations
    double calculateTotalEmissionsForUser(int userId);
    Map<Integer, Double> calculateMonthlyEmissionsForUser(int userId);
}
