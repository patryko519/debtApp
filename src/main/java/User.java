import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private String userName;

    public static boolean isAlpha(String s) {
        return s != null && s.matches("^[a-zA-Z]*$");
    }

    public User(){
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addUserToDatabase(){
        System.out.println("Enter name of new user");
        Scanner scanner = new Scanner(System.in);

        String user_name = scanner.nextLine();
        while (!isAlpha(user_name)){
            user_name = scanner.nextLine();
        }

        setUserName(user_name);
        try {
            DatabaseConnection.addUser(user_name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("User " + user_name + " has been added to database");
    }

}