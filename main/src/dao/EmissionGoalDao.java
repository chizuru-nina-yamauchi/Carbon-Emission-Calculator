package dao;

import model.EmissionGoal;

import java.util.Set;

public interface EmissionGoalDao {
    // CRUD operations
    EmissionGoal createEmissionGoal(EmissionGoal emissionGoal);
    EmissionGoal readEmissionGoalById(int emissionGoalId);
    Set<EmissionGoal> readAllEmissionGoals();
    boolean updateEmissionGoal(EmissionGoal emissionGoal);
    boolean deleteEmissionGoal(int emissionGoalId);

    // Other operations
    Set<Integer> findUsersExceededEmissionGoals();
    Set<EmissionGoal> findEmissionGoalsWithStatus(String status);




}
