import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean shouldContinue = true;
        int userChoice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome. Choose your option");

        while(shouldContinue){
            System.out.println("1. Create new account");
            System.out.println("2. Log in");
            //System.out.println("2. Add new transaction");
            //System.out.println("3. Check your transactions");
            System.out.println("3. Exit");

            userChoice = scanner.nextInt();
            switch(userChoice){
                case 1 -> CreateUser.createNewUser();
                case 2 -> LogInSystem.logIntoSystem();
                case 3 -> shouldContinue = false;
            }

        }
    }
}
