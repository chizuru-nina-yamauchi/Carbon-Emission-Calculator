import dao.*;
import model.Activity;
import model.EmissionGoal;
import util.ConnectionFactory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
*  Demo for CRUD Operations:
*  1. CRUD operation
*   Activity CRUD Operations(Pick up one model from the model package to demonstrate CRUD operations):
*
*  2. Advanced Operations:
*   Calculate total emissions for a user
*   Compare emissions between different activities
*   List activities with emissions above a certain threshold
*   Calculate monthly emissions for a user
*   Find users who have exceeded their emission goals
*   Filter results based on a custom enum type
*   Aggregate emissions by activity type and filter by minimum emission
*   Identify top 3 activities with the highest average emissions
* */

public class Main {

    private static final Menu menu = new Menu();
    private static final Scanner input = new Scanner(System.in);
    private static final ActivityDao activityDao = new ActivityDaoImpl();
    private static final UserEmissionDao userEmissionDao = new UserEmissionDaoImpl();
    private static final EmissionGoalDao emissionGoalDao = new EmissionGoalDaoImpl();

    public static void main(String[] args) {
        /* Instantiate the ConnectionFactory class(Singleton pattern)
         * and print the connection info.
         * printConnectionInfo() method is not static,
         * so we need to create an instance of the class to call it
         */
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connectionFactory.printConnectionInfo();
        while (true) {
            menu.displayMainMenu();

            int option = menu.getUserChoice();

            switch (option) {
                case 1:
                    handleCRUDOperation();
                    break;
                case 2:
                    boolean advancedMenuLoop = true; // Variable to control the loop
                    while (advancedMenuLoop) {
                        menu.displayAdvancedMenu();
                        int advancedOption = menu.getUserChoice();

                        switch (advancedOption) {
                            case 1:
                                System.out.println("Enter the user ID:");
                                int userIdToCalculate = input.nextInt();
                                input.nextLine(); // Consume the newline character

                                // Call the calculateTotalEmissionsForUser method from the UserEmissionDaoImpl class to calculate total emissions
                                double totalEmissions = userEmissionDao.calculateTotalEmissionsForUser(userIdToCalculate);

                                // Display the calculated total emissions
                                System.out.println("Total emissions for user with ID " + userIdToCalculate + ": " + totalEmissions);

                                break;
                            case 2:
                                // Call the compareEmissionsBetweenActivities method from the ActivityDaoImpl class
                                Map<String, Double> activityEmissions = activityDao.compareEmissionsBetweenActivities();

                                // Display the comparison results
                                if (!activityEmissions.isEmpty()) {
                                    System.out.println("Comparison of emissions between activities:");
                                    for (Map.Entry<String, Double> entry : activityEmissions.entrySet()) {
                                        System.out.println("Activity: " + entry.getKey() + ", Average Emission: " + entry.getValue());
                                    }
                                } else {
                                    System.out.println("No emissions data available for comparison between activities.");
                                }
                                break;
                            case 3:
                                System.out.println("Enter the threshold value:");
                                double threshold = input.nextDouble();
                                input.nextLine(); // consume the newline character

                                // Call the listActivitiesAboveThreshold method from the ActivityDaoImpl class
                                Map<String, Double> activityEmissionsAboveThreshold = activityDao.listActivitiesAboveThreshold(threshold);

                                // Display the activities with emissions above the threshold
                                if (!activityEmissionsAboveThreshold.isEmpty()) {
                                    System.out.println("Activities with emissions above the threshold:");
                                    for (Map.Entry<String, Double> entry : activityEmissionsAboveThreshold.entrySet()) {
                                        System.out.println("Activity: " + entry.getKey() + ", Emission: " + entry.getValue());
                                    }
                                } else {
                                    System.out.println("No activities found with emissions above the specified threshold.");
                                }
                                break;
                            case 4:
                                System.out.println("Enter the user ID:");
                                int userIdForMonthlyEmissions = input.nextInt();
                                input.nextLine(); // consume the newline character

                                // Call the calculateMonthlyEmissionsForUser method from the UserEmissionDaoImpl class
                                Map<Integer, Double> monthlyEmissionsToCalculate = userEmissionDao.calculateMonthlyEmissionsForUser(userIdForMonthlyEmissions);

                                // Display the monthly emissions for the user
                                if (!monthlyEmissionsToCalculate.isEmpty()) {
                                    System.out.println("Monthly emissions for user with ID " + userIdForMonthlyEmissions + ":");
                                    for (Map.Entry<Integer, Double> entry : monthlyEmissionsToCalculate.entrySet()) {
                                        System.out.println("Month: " + entry.getKey() + ", Emission: " + entry.getValue());
                                    }
                                } else {
                                    System.out.println("No monthly emissions found for the specified user ID.");
                                }
                                break;
                            case 5:
                                Set<Integer> userIds = emissionGoalDao.findUsersExceededEmissionGoals();

                                // Display the user IDs who have exceeded their emission goals
                                if (!userIds.isEmpty()) {
                                    System.out.println("Users who have exceeded their emission goals:");
                                    for (int userId : userIds) {
                                        System.out.println("User ID: " + userId);
                                    }
                                } else {
                                    System.out.println("No users found who have exceeded their emission goals.");
                                }
                                break;
                            case 6:
                                System.out.println("Enter the status to find the emission goals:");
                                String status = input.nextLine();

                                // Call the findEmissionGoalsWithStatus method from the EmissionGoalDaoImpl class
                                Set<EmissionGoal> emissionGoals = emissionGoalDao.findEmissionGoalsWithStatus(status);

                                // Display the emission goals with the specified status
                                if (!emissionGoals.isEmpty()) {
                                    System.out.println("Emission goals with status '" + status + "':");
                                    for (EmissionGoal goal : emissionGoals) {
                                        System.out.println("ID: " + goal.getEmissionGoalId() + ", Status: " + goal.getStatus());
                                    }
                                } else {
                                    System.out.println("No emission goals found with status '" + status + "'.");
                                }
                                break;
                            case 7:
                                System.out.println("Enter the minimum emission value:");
                                double minimumEmission = input.nextDouble();
                                input.nextLine(); // consume the newline character

                                Map<String, Double> activityEmissionsToAggregate = activityDao.aggregateEmissionsByActivityAndFilterByMinimumEmission(minimumEmission);

                                // Display the aggregated emissions filtered by minimum emission
                                if (!activityEmissionsToAggregate.isEmpty()) {
                                    System.out.println("Aggregated emissions by activity filtered by minimum emission (" + minimumEmission + "):");
                                    for (Map.Entry<String, Double> entry : activityEmissionsToAggregate.entrySet()) {
                                        System.out.println("Activity: " + entry.getKey() + ", Total Emission: " + entry.getValue());
                                    }
                                } else {
                                    System.out.println("No activity emissions found exceeding the minimum emission value.");
                                }
                                break;
                            case 8:
                                List<String> topActivities = activityDao.identifyTop3ActivitiesWithHighestAverageEmissions();
                                // Display the top 3 activities with the highest average emissions
                                if (!topActivities.isEmpty()) {
                                    System.out.println("Top 3 activities with highest average emissions:");
                                    for (int i = 0; i < topActivities.size(); i++) {
                                        System.out.println((i + 1) + ". " + topActivities.get(i));
                                    }
                                } else {
                                    System.out.println("No activities found.");
                                }
                                break;

                                case 9:
                                    advancedMenuLoop = false; // Back to main menu
                                    break;
                            default:
                                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                                break;
                        }
                    }
                    break;

                case 3:
                    visualizeEmissionsForUserOverTime();
                    break;

                case 4:
                    System.out.println("You chose to exit the system");
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    break;
            }

        }
    }

    private static void visualizeEmissionsForUserOverTime(){
        System.out.println("Enter the user ID:");
        int userId = input.nextInt();
        input.nextLine(); // Consume newline

        Map<Integer, Double> monthlyEmissions = userEmissionDao.calculateMonthlyEmissionsForUser(userId);

        if (!monthlyEmissions.isEmpty()) {
            System.out.println("Monthly emissions for user with ID " + userId + ":");

            for (Map.Entry<Integer, Double> entry : monthlyEmissions.entrySet()) {
                int month = entry.getKey();
                double emission = entry.getValue();

                System.out.print("Month " + month + ": ");
                printEmissionBar(emission);
                System.out.println(" " + emission);
            }
        } else {
            System.out.println("No monthly emissions found for the specified user ID.");
        }
    }

    private static void printEmissionBar(double emission) {
        int numberOfAsterisks = (int) Math.round(emission); // Round to the nearest integer
        for (int i = 0; i < numberOfAsterisks; i++) {
            System.out.print("*");
        }
    }

    private static void handleCRUDOperation() {
        while (true) {
            menu.displayCRUDMenuForActivity();
            int crudOption = menu.getUserChoice();

            switch (crudOption) {
                case 1:
                    handleActivityCRUD();
                    break;

                case 2:
                    // Back to main menu
                    return;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    break;
            }
        }
    }

    private static void handleActivityCRUD() {
        while(true) {
            menu.displayActivityCRUDMenu();

            int choice = input.nextInt();
            input.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the activity:");
                    String name = input.nextLine();

                    System.out.println("Enter the description of the activity:");
                    String description = input.nextLine();
                    // Create an Activity object with the provided details
                    Activity activityToCreate = new Activity(name, description);
                    // Call the createActivity method from the ActivityDaoImpl class to persist the activity
                    Activity createdActivity = activityDao.createActivity(activityToCreate);

                    if (createdActivity != null) {
                        System.out.println("Activity created successfully.");
                        // Optionally, you can display details of the created activity
                        System.out.println("Created Activity Details:");
                        System.out.println("Name: " + createdActivity.getActivityName());
                        System.out.println("Description: " + createdActivity.getActivityDescription());
                    } else {
                        System.out.println("Failed to create activity.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the ID of the activity:");
                    int id = input.nextInt();
                    input.nextLine(); // consume the newline character

                    // Call the readActivityById method from the ActivityDaoImpl class to retrieve
                    Activity activityToGet = activityDao.readActivityById(id);
                    if (activityToGet != null) {
                        System.out.println("Activity found:");
                        System.out.println("ID: " + activityToGet.getActivityId());
                        System.out.println("Name: " + activityToGet.getActivityName());
                        System.out.println("Description: " + activityToGet.getActivityDescription());
                    } else {
                        System.out.println("Activity not found with ID: " + id);
                    }
                    break;
                case 3:
                    // Call the readAllActivities method from the ActivityDaoImpl class to retrieve all activities
                    Set<Activity> activities = activityDao.readAllActivities();
                    if (!activities.isEmpty()) {
                        System.out.println("All activities:");
                        for (Activity eachActivity : activities) {
                            System.out.println("ID: " + eachActivity.getActivityId());
                            System.out.println("Name: " + eachActivity.getActivityName());
                            System.out.println("Description: " + eachActivity.getActivityDescription());
                            System.out.println("-----------------------------");
                        }
                    } else {
                        System.out.println("No activities found.");
                    }

                    break;
                case 4:
                    System.out.println("Enter the ID of the activity you want to update:");
                    int idToUpdate = input.nextInt();
                    input.nextLine(); // Consume newline character

                    // Retrieve the existing activity by ID
                    Activity existingActivity = activityDao.readActivityById(idToUpdate);
                    if (existingActivity != null) {
                        // Prompt the user to enter updated details for the activity
                        System.out.println("Enter the new name for the activity:");
                        String newName = input.nextLine();
                        System.out.println("Enter the new description for the activity:");
                        String newDescription = input.nextLine();

                        // Update the existing activity object with the new details
                        existingActivity.setActivityName(newName);
                        existingActivity.setActivityDescription(newDescription);

                        // Update the activity in the database
                        boolean success = activityDao.updateActivity(existingActivity);
                        if (success) {
                            System.out.println("Activity updated successfully.");
                        } else {
                            System.out.println("Failed to update activity.");
                        }
                    } else {
                        System.out.println("No activity found with ID: " + idToUpdate);
                    }
                    break;
                case 5:
                    System.out.println("Enter the ID of the activity to delete:");
                    int idToDelete = input.nextInt();
                    boolean success = activityDao.deleteActivity(idToDelete);
                    if (success) {
                        System.out.println("Activity deleted successfully.");
                    } else {
                        System.out.println("Failed to delete activity.");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    break;
            }
        }

    }
}
