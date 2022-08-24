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

        emptyTransaction = new JLabel("");

        JButton backButton = new JButton("Back");
        backButton.setBounds(160,200,165,25);
        panel.add(backButton);
        backButton.addActionListener(e -> new ManagementSystemGUI(username));
        backButton.addActionListener(e -> frame.dispose());
    }

    public void typeOfTransaction(){
        if("Type of transaction".equals(transactionType.getText())){
            transactionType.setText("Incoming");
            incomingTransactions();
        }else if("Incoming".equals(transactionType.getText())){
            if(listOfTransactions.isEnabled()){
                panel.remove(listOfTransactions);
            }
            if(emptyTransaction.isEnabled()){
                panel.remove(emptyTransaction);
            }
            transactionType.setText("Outgoing");
            outgoingTransactions();
        }else{
            if(listOfTransactions.isEnabled()){
                panel.remove(listOfTransactions);
            }
            if(emptyTransaction.isEnabled()){
                panel.remove(emptyTransaction);
            }
            transactionType.setText("Incoming");
            incomingTransactions();
        }
    }

    private void outgoingTransactions() {
        String secondUser = listOfUsers.getItemAt(listOfUsers.getSelectedIndex());
        int secondUserId = DatabaseConnection.getUserIdByName(secondUser);
        Vector<Tuple<Double, String>> transactions = DatabaseConnection.checkTransactions(secondUserId, userId);
        listOfTransactions = new JList<>((transactions));

        if(!transactions.isEmpty()){
            listOfTransactions = new JList<>((transactions));
            listOfTransactions.setBounds(190, 70, 105, 100);
            panel.add(listOfTransactions);
            panel.repaint();
        }else{
            System.out.println("hello");
            emptyTransaction.setText("Transaction list is empty");
            emptyTransaction.setBounds(140,70,205,100);
            panel.add(emptyTransaction);
            panel.repaint();
        }


    }

    private void incomingTransactions(){
        String secondUser = listOfUsers.getItemAt(listOfUsers.getSelectedIndex());
        int secondUserId = DatabaseConnection.getUserIdByName(secondUser);
        Vector<Tuple<Double,String>> transactions = DatabaseConnection.checkTransactions(userId,secondUserId);
        listOfTransactions = new JList<>((transactions));

        if(!transactions.isEmpty()){
            listOfTransactions.setBounds(190,70,105,100);
            panel.add(listOfTransactions);
            panel.repaint();
        }else{
            emptyTransaction.setText("Transaction list is empty");
            emptyTransaction.setBounds(140,70,205,100);
            panel.add(emptyTransaction);
            panel.repaint();
        }
    }
}
