import javax.swing.*;
import java.sql.SQLException;

public class ManagementSystemGUI extends Frame {

    public ManagementSystemGUI(String username){

        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Welcome " + username + " to debt manager.");
        label.setBounds(10,0,450,20);
        panel.add(label);

        JButton newTransactionButton = new JButton("Add new transaction");
        newTransactionButton.setBounds(5,40,200,25);
        panel.add(newTransactionButton);
        newTransactionButton.addActionListener(e -> {
            try {
                new AddTransactionGUI(username);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        newTransactionButton.addActionListener(e -> frame.dispose());

        JButton checkTransactionsButton = new JButton("Check your transactions");
        checkTransactionsButton.setBounds(5,80,200,25);
        panel.add(checkTransactionsButton);
        checkTransactionsButton.addActionListener(e -> {
            try {
                new CheckTransactionGUI(username);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        checkTransactionsButton.addActionListener(e -> frame.dispose());

        JButton logOutButton = new JButton("Log out");
        logOutButton.setBounds(5,120,200,25);
        panel.add(logOutButton);
        logOutButton.addActionListener(e -> exitPanel());
    }
}
