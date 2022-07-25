import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private HashMap<String, Integer> debts;



    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addUserToDatabase(){

        try {
            DatabaseConnection.addUser(username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("User " + username + " has been added to database");
    }

    public void addTransactionToDatabase(){

    }

}