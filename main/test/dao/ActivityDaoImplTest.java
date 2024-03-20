package dao;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoImplTest {

    private ActivityDao activityDao = new ActivityDaoImpl();

    @Test
    public void testCompareEmissionsBetweenActivities() {
        Map<String, Double> activityEmissions = activityDao.compareEmissionsBetweenActivities();
        assertNotNull(activityEmissions);
        assertFalse(activityEmissions.isEmpty());
    }

    @Test
    public void testListActivitiesAboveThreshold() {
        double threshold = 0.5;
        Map<String, Double> activityEmissions = activityDao.listActivitiesAboveThreshold(threshold);
        assertNotNull(activityEmissions);
    }

    @Test
    public void testAggregateEmissionsByActivityAndFilterByMinimumEmission() {
        double minimumEmission = 1.0;
        Map<String, Double> activityEmissions = activityDao.aggregateEmissionsByActivityAndFilterByMinimumEmission(minimumEmission);
        assertNotNull(activityEmissions);

    }

    @Test
    public void testIdentifyTop3ActivitiesWithHighestAverageEmissions() {
        List<String> topActivities = activityDao.identifyTop3ActivitiesWithHighestAverageEmissions();
        assertNotNull(topActivities);
        assertEquals(3, topActivities.size());
    }

}