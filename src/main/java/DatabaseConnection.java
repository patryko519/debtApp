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
            System.out.println("Connected successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(String userName) throws SQLException {
        connectionToDatabase();
        String queryToAddUser = "INSERT INTO users(user_name) VALUES('" + userName + "')";
        PreparedStatement statement = connection.prepareStatement(queryToAddUser);
        statement.executeQuery();
        statement.close();
        connection.close();
    }

    public static void addTransaction(String whoBought, String whoOwe, int howMuch) throws SQLException {
        connectionToDatabase();
        int whoBoughtId = getUserId(whoBought);
        int whoOweId = getUserId(whoOwe);
        String queryToAddTransaction = "INSERT INTO transactions(buyer_id, debtor_id, amount) " +
                                        "VALUES('" + whoBoughtId + "','" + whoOweId + "'," + howMuch +");";
        PreparedStatement statement = connection.prepareStatement(queryToAddTransaction);
        statement.executeQuery();
        statement.close();
        connection.close();
    }

    public static int getUserId(String user_name) throws SQLException {
        connectionToDatabase();
        String query = "SELECT id FROM users WHERE user_name='" + user_name + "'";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        statement.close();
        resultSet.next();

        int userId = resultSet.getInt("id");
        resultSet.close();
        connection.close();
        return userId;
    }

    public static void main(String[] args) throws SQLException {
        addTransaction("mich", "adam", 20);
    }
}
