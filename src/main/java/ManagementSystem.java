import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class ManagementSystem {
    private static int userId;
    private static String username;
    private static HashMap transactions;

    public static void enterSystem(int idOfUser, String userName) throws SQLException {
        userId = idOfUser;
        username = userName;
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
                case 2 -> checkTransactions();
                case 3 -> shouldContinue = false;
            }
        }
    }

    private static void addNewTransaction() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of user who owe you money");

        String nameOfDebtor = scanner.nextLine();
        System.out.println();

        while(!DatabaseConnection.existsUserByName(nameOfDebtor)) {
            nameOfDebtor = scanner.nextLine();
        }

        System.out.println("Enter amount of debt");
        String amount = scanner.nextLine();
        System.out.println();
        while(!RegexCheck.isNumeric(amount)){
            System.out.println("Enter integer");
            amount = scanner.nextLine();
        }

        System.out.println("Enter description");
        String description = scanner.nextLine();
        System.out.println();

        DatabaseConnection.addTransaction(username, nameOfDebtor, Integer.valueOf(amount), description);
    }

    public static void checkTransactions() throws SQLException {
        transactions = DatabaseConnection.checkTransactions(userId);
        System.out.println(transactions);
    }
}
