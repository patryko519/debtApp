import java.sql.SQLException;
import java.util.Scanner;

public class CreateUser {

    public static void createNewUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name of new user");
        String username = scanner.nextLine();
        while (!RegexCheck.isUsernameValid(username)){
            System.out.println("Pleas enter correct username");
            username = scanner.nextLine();
        }

        System.out.println("Enter your password");
        String password = scanner.nextLine();
        while (!RegexCheck.isPasswordValid(password)){
            System.out.println("Pleas enter correct password");
            password = scanner.nextLine();
        }

        CreateUser.addUserToDatabase(username, password);
    }

    public static void addUserToDatabase(String username, String password){

        try {
            DatabaseConnection.addUser(username,password);
            System.out.println("User " + username + " has been added to database");
        } catch (SQLException e) {
            System.out.println("Couldn't create account. There is another user with such name");
        }

    }
}
