import java.sql.*;


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

    public static void addTransaction(String whoBought, String whoOwe, int howMuch, String transactionDescription) throws SQLException {
        connectionToDatabase();
        int whoBoughtId = getUserIdByName(whoBought);
        int whoOweId = getUserIdByName(whoOwe);

        /* String queryToAddTransaction = "INSERT INTO transactions(buyer_id, debtor_id, amount, transaction_description) " +
                                       "VALUES('" + whoBoughtId + "','" + whoOweId + "'," + howMuch + ",'" + transactionDescription + "')";
        */
        String queryToAddTransaction = "INSERT INTO transactions(buyer_id, debtor_id, amount, transaction_description) VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(queryToAddTransaction);
        statement.setInt(1,whoBoughtId);
        statement.setInt(2,whoOweId);
        statement.setInt(3,howMuch);
        statement.setString(4,transactionDescription);
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

    public static int getUserId2(String username, String password){
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



    public static void main(String[] args){
        System.out.println(getUserId2("aaa","aan"));
        //addTransaction("mich", "adam", 8820, "a3ca dla");
    }
}
