package dao;

import model.EmissionGoal;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmissionGoalDaoImplTest {
    private final EmissionGoalDao emissionGoalDao = new EmissionGoalDaoImpl();
    @Test
    public void testFindUsersExceededEmissionGoals() {
        Set<Integer> userIds = emissionGoalDao.findUsersExceededEmissionGoals();
        assertNotNull(userIds);
    }

    @Test
    public void testFindEmissionGoalsWithStatus() {
        String status = "ONGOING";
        Set<EmissionGoal> emissionGoals = emissionGoalDao.findEmissionGoalsWithStatus(status);
        assertNotNull(emissionGoals);
    }

}