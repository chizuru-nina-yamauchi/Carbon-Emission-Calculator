package dao;

import model.Activity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ActivityDao {
    // CRUD operations
    Activity createActivity(Activity activity);
    Activity readActivityById(int activityId);
    Set<Activity> readAllActivities();
    boolean updateActivity(Activity activity);
    boolean deleteActivity(int activityId);

    // Other operations
    Map<String, Double> compareEmissionsBetweenActivities();
    Map<String, Double> listActivitiesAboveThreshold(double threshold);
    Map<String, Double> aggregateEmissionsByActivityAndFilterByMinimumEmission(double minimumEmission);
    List<String> identifyTop3ActivitiesWithHighestAverageEmissions();
}
