import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean shouldContinue = true;
        int userChoice;
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("Welcome. Choose your option");

        while(shouldContinue){
            System.out.println("1. Add user to database");
            System.out.println("2. Add new transaction");
            System.out.println("3. Check your transactions");
            System.out.println("4. Exit");

            userChoice = scanner.nextInt();
            switch(userChoice){
                case 1 -> user.addUserToDatabase();
                case 2 -> user.addUserToDatabase();
                case 3 -> user.addUserToDatabase();
                case 4 -> shouldContinue = false;
            }

        }
    }
}
