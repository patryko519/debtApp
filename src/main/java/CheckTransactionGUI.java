import javax.swing.*;
import java.sql.SQLException;
import java.util.Vector;

public class CheckTransactionGUI extends Frame{

    private final int userId;
    private final JComboBox<String> listOfUsers;
    private final JButton transactionType;
    private JList<Tuple<Double,String>> listOfTransactions;
    private JLabel emptyTransaction;

    public CheckTransactionGUI(String username) throws SQLException {
        userId = DatabaseConnection.getUserIdByName(username);

        frame.setSize(485,275);

        frame.add(panel);
        panel.setLayout(null);

        Vector<String> names = DatabaseConnection.usersNames();
        names.remove(username);

        transactionType = new JButton("Type of transaction");
        transactionType.setBounds(160,10,165,25);
        panel.add(transactionType);
        transactionType.addActionListener(e -> typeOfTransaction());

        listOfUsers = new JComboBox<>(names);
        listOfUsers.setBounds(160,40,165,25);
        panel.add(listOfUsers);
    }

    public void typeOfTransaction(){
        if("Type of transaction".equals(transactionType.getText())){
            transactionType.setText("Incoming");
            incomingTransactions();
        }else if("Incoming".equals(transactionType.getText())){
            panel.remove(listOfTransactions);
            transactionType.setText("Outgoing");
            outgoingTransactions();
        }else{
            panel.remove(listOfTransactions);
            transactionType.setText("Incoming");
            incomingTransactions();
        }
    }

    private void outgoingTransactions() {
        String secondUser = listOfUsers.getItemAt(listOfUsers.getSelectedIndex());
        int secondUserId = DatabaseConnection.getUserIdByName(secondUser);
        Vector<Tuple<Double, String>> transactions = DatabaseConnection.checkTransactions(secondUserId, userId);

        if(!transactions.isEmpty()){
            listOfTransactions = new JList<>((transactions));
            listOfTransactions.setBounds(160, 70, 100, 100);
            panel.add(listOfTransactions);
            panel.repaint();
        }else{
            emptyTransaction = new JLabel("List of transactions is empty");
            emptyTransaction.setBounds(160,70,100,100);
            panel.add(emptyTransaction);
        }


    }

    private void incomingTransactions(){
        String secondUser = listOfUsers.getItemAt(listOfUsers.getSelectedIndex());
        int secondUserId = DatabaseConnection.getUserIdByName(secondUser);
        Vector<Tuple<Double,String>> transactions = DatabaseConnection.checkTransactions(userId,secondUserId);

        if(!transactions.isEmpty()){
            listOfTransactions = new JList<>((transactions));
            listOfTransactions.setBounds(160,70,100,100);
            panel.add(listOfTransactions);
            panel.repaint();
        }else{
            emptyTransaction = new JLabel("List of transactions is empty");
            emptyTransaction.setBounds(160,70,100,100);
            panel.add(emptyTransaction);
        }
    }
}
