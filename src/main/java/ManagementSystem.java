import java.util.Scanner;

public class ManagementSystem {
    private static int userId;

    public static void enterSystem(int idOfUser){
        userId = idOfUser;
        boolean shouldContinue = true;
        Scanner scanner = new Scanner(System.in);

        while(shouldContinue){
            System.out.println("1. Add new transaction");
            System.out.println("2. Check your transactions");
            System.out.println("3. Log out");

            String userChoice = scanner.nextLine();
            while(!RegexCheck.isNumeric(userChoice)){
                System.out.println("Enter integer");
                userChoice = scanner.nextLine();
            }

            switch(Integer.valueOf(userChoice)){
                case 1 -> addNewTransaction();
                case 2 -> LogInSystem.logIntoSystem();
                case 3 -> shouldContinue = false;
            }
        }
    }

    private static void addNewTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of user who owe you money");

        String nameOfDebtor = scanner.nextLine();
        int idOfDebtor = DatabaseConnection.getUserIdByName(nameOfDebtor);



    }
}
