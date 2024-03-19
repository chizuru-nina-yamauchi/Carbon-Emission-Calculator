import util.ConnectionFactory;

import java.util.Scanner;

/*
*  Demo for CRUD Operations:
*  1. CRUD operation
*   Activity CRUD Operations:
*   Emission Factor CRUD Operations:
*   User Emission CRUD Operations:
*   User CRUD Operations:
*   Emission Goal CRUD Operations:
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
    public static void main(String[] args) {
        /* Instantiate the ConnectionFactory class(Singleton pattern)
         * and print the connection info.
         * printConnectionInfo() method is not static,
         * so we need to create an instance of the class to call it
         */
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        connectionFactory.printConnectionInfo();

        Scanner input = new Scanner(System.in);

        while(true) {

            System.out.println("---------------------------------");
            System.out.println("Welcome to the Emission Tracker System!");

            System.out.println("Main menu: choose an option");
            System.out.println("1. CRUD operations");
            System.out.println("2. Other advanced operations");
            System.out.println("3. Exit the system");

            System.out.println("Enter the number of the option you want to choose: ");

            System.out.println("---------------------------------");

            try {
                String optionInput = input.nextLine();
                int option = Integer.parseInt(optionInput);

                switch (option){
                    case 1:
                        System.out.println("You chose CRUD operations");
                        System.out.println("Choose an option");
                        System.out.println("1. Activity CRUD operations");
                        System.out.println("2. Emission Factor CRUD operations");
                        System.out.println("3. User Emission CRUD operations");
                        System.out.println("4. User CRUD operations");
                        System.out.println("5. Emission Goal operations");
                        System.out.println("6. Back to main menu");

                        System.out.println("Enter the number of the option you want to choose: ");
                        String crudOptionInput = input.nextLine();
                        int crudOption = Integer.parseInt(crudOptionInput);

                        switch (crudOption){
                            case 1:
                                System.out.println("You chose Activity operations");
                                System.out.println("Choose an option");
                                System.out.println("1. Create an activity");
                                System.out.println("2. Read an activity by ID");
                                System.out.println("3. Read all activities");
                                System.out.println("4. Update an activity");
                                System.out.println("5. Delete an activity");
                                System.out.println("6. Back to previous menu");

                                System.out.println("Enter the number of the option you want to choose: ");

                                boolean activityOptionValid = true;

                                while(activityOptionValid) {

                                    try {
                                        String activityOptionInput = input.nextLine();
                                        int activityOption = Integer.parseInt(activityOptionInput);
                                        switch (activityOption) {
                                            case 1:
                                                System.out.println("You chose to create an activity");
                                                // Call the createActivity method
                                                break;
                                            case 2:
                                                System.out.println("You chose to read an activity by ID");
                                                // Call the readActivityById method
                                                break;
                                            case 3:
                                                System.out.println("You chose to read all activities");
                                                // Call the readAllActivities method
                                                break;
                                            case 4:
                                                System.out.println("You chose to update an activity");
                                                // Call the updateActivity method
                                                break;
                                            case 5:
                                                System.out.println("You chose to delete an activity");
                                                // Call the deleteActivity method
                                                break;
                                            case 6:
                                                System.out.println("You chose to go back to the main menu");
                                                break;
                                            default:
                                                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                                        }
                                        activityOptionValid = false;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input. Please enter a number.");
                                    }
                                }

                            case 2:
                                System.out.println("You chose User Emission operations");
                                System.out.println("Choose an option");
                                System.out.println("1. Create a user emission");
                                System.out.println("2. Read a user emission by ID");
                                System.out.println("3. Read all user emissions");
                                break;
                        }

                    case 2:
                        System.out.println("You chose Other advanced operations");
                        break;
                    case 3:
                        System.out.println("You chose to exit the system");
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 3.");
                        break;

                }

                input.close();

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

        }

    }
}
