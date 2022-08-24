import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class DatabaseConnection {
    private static Connection connection;

    public static void connectionToDatabase(){
        String url = "jdbc:mysql://localhost:3306/debtapp";
        String user="root";
        String password="patryk#123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Couldn't connect to database");
        }
    }

    public static void addUser(String username, String password) throws SQLException {
        connectionToDatabase();

        String queryToAddUser = "INSERT INTO users(username, password) VALUES(?,?)";
        PreparedStatement statement = connection.prepareStatement(queryToAddUser);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void addTransaction(String whoBought, String whoOwe, double howMuch, String transactionDescription) throws SQLException {
        connectionToDatabase();

        int whoBoughtId = getUserIdByName(whoBought);
        int whoOweId = getUserIdByName(whoOwe);

        String queryToAddTransaction = "INSERT INTO transactions(buyer_id, debtor_id, amount, transaction_description) VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(queryToAddTransaction);
        statement.setInt(1, whoBoughtId);
        statement.setInt(2, whoOweId);
        statement.setDouble(3, howMuch);
        statement.setString(4, transactionDescription);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static int getUserIdByName(String username){
        connectionToDatabase();
        try {
            String query = "SELECT id FROM users WHERE username=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int userId = resultSet.getInt("id");
            statement.close();

            return userId;
        } catch (SQLException e) {
            System.out.println("There is no such user");
        }
        return -1;
    }

    public static int getUserIdIfExists(String username, String password){
        connectionToDatabase();
        try {
            String query = "SELECT id FROM users WHERE username=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int userId = resultSet.getInt("id");
            statement.close();
            return userId;
        } catch (SQLException e) {
            System.out.println("There is no such user");
        }

        return -1;
    }

    public static Vector<Tuple<Double,String>> checkTransactions(int userId, int secondUserId){
        Vector<Tuple<Double,String>> transaction = new Vector<>();
        double amount;
        String descriptionOfTransaction;
        connectionToDatabase();

        try {
            String query = "SELECT amount, transaction_description FROM transactions WHERE buyer_id=? AND debtor_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, secondUserId);
            ResultSet buyer = statement.executeQuery();

            while (!buyer.isLast()) {
                buyer.next();

                amount = buyer.getDouble("amount");
                descriptionOfTransaction = buyer.getString("transaction_description");

                transaction.add(new Tuple(amount,descriptionOfTransaction));
            }
            statement.close();
        }catch (SQLException e) {
            System.out.println("Nobody Owes You Anything");
        }

//        try {
//            String query = "SELECT buyer_id, amount, transaction_description FROM transactions WHERE debtor_id=?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, userId);
//            ResultSet debtor = statement.executeQuery();
//            String buyerName;
//
//            while (!debtor.isLast()) {
//                debtor.next();
//
//                buyerName = getUsernameById(debtor.getInt("buyer_id"));
//                amount = debtor.getInt("amount");
//                descriptionOfTransaction = debtor.getString("transaction_description");
//
//                transaction.put(indexOfTransaction, "You owe " + buyerName + " " + amount + " for " + descriptionOfTransaction);
//                indexOfTransaction++;
//            }
//            statement.close();
//        } catch (SQLException e) {
//            System.out.println("You don't owe anyone anything");
//        }

        return transaction;
    }

    public static String getUsernameById(int userId) throws SQLException {
        connectionToDatabase();
        try {
            String query = "SELECT username FROM users WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            String username = resultSet.getString("username");
            statement.close();
            return username;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public static Vector<String> usersNames() throws SQLException {
        Vector<String> names = new Vector<>();
        connectionToDatabase();

        try {
            String query = "SELECT username FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (!resultSet.isLast()) {
                resultSet.next();

                names.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            throw new SQLException();
        }

        return names;
    }

    public static void main(String[] args) throws SQLException {
        Vector<String> test = usersNames();
        for(String names : test){
            System.out.println(names);
        }
    }
}
