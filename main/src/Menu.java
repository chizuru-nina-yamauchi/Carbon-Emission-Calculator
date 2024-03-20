import java.util.Scanner;

public class Menu {
    private final Scanner input;

    public Menu() {
        this.input = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("---------------------------------");
        System.out.println("Welcome to the Emission Tracker System!");
        System.out.println("Main menu: choose an option");
        System.out.println("1. CRUD operations Demo with Activity");
        System.out.println("2. Other advanced operations");
        System.out.println("3. Visualize carbon emissions for a user over time");
        System.out.println("4. Exit the system");
        System.out.println("Enter the number of the option you want to choose: ");
        System.out.println("---------------------------------");
    }

    public void displayCRUDMenuForActivity() {
        System.out.println("---------------------------------");
        System.out.println("You chose CRUD operations Demo with Activity");
        System.out.println("Choose an option");
        System.out.println("1. Activity CRUD operations");
        System.out.println("2. Back to main menu");
        System.out.println("Enter the number of the option you want to choose: ");
        System.out.println("---------------------------------");
    }

    public void displayActivityCRUDMenu(){
        System.out.println("---------------------------------");
        System.out.println("Activity CRUD Operations");
        System.out.println("1. Create Activity");
        System.out.println("2. Read Activity by ID");
        System.out.println("3. Read All Activities");
        System.out.println("4. Update Activity");
        System.out.println("5. Delete Activity");
        System.out.println("6. Back to previous menu");
        System.out.println("Enter the number of the operation you want to perform:");
        System.out.println("---------------------------------");
    }

    public void displayAdvancedMenu() {
            System.out.println("---------------------------------");
            System.out.println("You chose Other advanced operations");
            System.out.println("Choose an option");
            System.out.println("1. Calculate total emissions for a user");
            System.out.println("2. Compare emissions between different activities");
            System.out.println("3. List activities with emissions above a certain threshold");
            System.out.println("4. Calculate monthly emissions for a user");
            System.out.println("5. Find users who have exceeded their emission goals");
            System.out.println("6. Filter results based on a custom enum type");
            System.out.println("7. Aggregate emissions by activity type and filter by minimum emission");
            System.out.println("8. Identify top 3 activities with the highest average emissions");
            System.out.println("9. Back to main menu");
            System.out.println("---------------------------------");
    }

    public int getUserChoice() {
       while(true){
           try{
               String optionInput = input.nextLine();
               return Integer.parseInt(optionInput);
           }catch (NumberFormatException e){
               System.out.println("Invalid input. Please enter a number.");
           }
       }
    }



}
