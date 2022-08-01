import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        boolean shouldContinue = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome. Choose your option");


        while(shouldContinue){
            System.out.println("1. Create new account");
            System.out.println("2. Log in");
            System.out.println("3. Exit");

//            try{
//                userChoice = scanner.nextInt();
//            } catch (InputMismatchException e) {
//                System.out.println("Enter proper integer");
//                scanner.nextLine();
//            }
            String userChoice = scanner.nextLine();
            while(!RegexCheck.isNumeric(userChoice)){
                System.out.println("Enter integer");
                userChoice = scanner.nextLine();
            }

            switch(Integer.valueOf(userChoice)){
                case 1 -> CreateUser.createNewUser();
                case 2 -> LogInSystem.logIntoSystem();
                case 3 -> shouldContinue = false;
                default -> System.out.println("Enter proper integer");
            }

        }
    }
}
