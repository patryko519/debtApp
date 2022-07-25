import java.util.Scanner;

public class LogInSystem {

    public static void logIntoSystem(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username");
        String username = scanner.nextLine();

        System.out.println("Enter password");
        String password = scanner.nextLine();

        int idOfAccount = DatabaseConnection.getUserId2(username,password);

        if(idOfAccount != -1){
            ManagementSystem.enterSystem(idOfAccount);
        } else{
            System.out.println("Incorrect username or login");
        }
    }
}