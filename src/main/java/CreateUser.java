import java.util.Scanner;

public class CreateUser {

    public static void createNewUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name of new user");
        String username = scanner.nextLine();
        while (!isUsernameValid(username)){
            System.out.println("Pleas enter correct username");
            username = scanner.nextLine();
        }

        System.out.println("Enter your password");
        String password = scanner.nextLine();
        while (!isPasswordValid(password)){
            System.out.println("Pleas enter correct password");
            password = scanner.nextLine();
        }

        User user = new User(username, password);
        user.addUserToDatabase();

    }

    public static boolean isUsernameValid(String s) {
        return s != null && s.matches("[a-zA-Z]+");
    }

    public static boolean isPasswordValid(String s) {
        //at least one lower case alphabet
        return s != null && s.matches(".+");
    }

}
