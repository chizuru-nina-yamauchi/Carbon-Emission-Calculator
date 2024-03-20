package dao;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserEmissionDaoImplTest {

    private final UserEmissionDao userEmissionDao = new UserEmissionDaoImpl();

    @Test
    public void testCalculateTotalEmissionsForUser() {
        int userId = 1;
        double totalEmissions = userEmissionDao.calculateTotalEmissionsForUser(userId);
        // Add assertions to check the calculated total emissions
        assertTrue(totalEmissions >= 0); // Assuming emissions are non-negative
    }

    @Test
    public void testCalculateMonthlyEmissionsForUser() {
        int userId = 1;
        Map<Integer, Double> monthlyEmissions = userEmissionDao.calculateMonthlyEmissionsForUser(userId);
        assertNotNull(monthlyEmissions);
        assertFalse(monthlyEmissions.isEmpty());
    }

}