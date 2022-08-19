import javax.swing.*;
import java.sql.SQLException;
import java.util.Vector;

public class AddTransactionGUI extends Frame {

    private JComboBox<String> list;
    private SpinnerModel model;
    private JSpinner spinner;
    private JToggleButton test;
    private int typeOfTransaction;
    private String username;
    private JTextField transactionDescription;

    public AddTransactionGUI(int userId, String username) throws SQLException {
        this.username = username;

        frame.setSize(485,275);

        frame.add(panel);
        panel.setLayout(null);

        Vector<String> names = DatabaseConnection.usersNames();

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        list = new JComboBox<>(names);
        list.setBounds(100,20,165,25);
        panel.add(list);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setBounds(10,50,80,25);
        panel.add(amountLabel);

        //better number input
        model = new SpinnerNumberModel(9.9, 0.00, 10000, 0.1);
        spinner = new JSpinner(model);
        spinner.setBounds(100,50,80,25);
        panel.add(spinner);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(10,80,80,25);
        panel.add(descriptionLabel);

        transactionDescription = new JTextField();
        transactionDescription.setBounds(100,80,165,50);
        panel.add(transactionDescription);

        JButton addTransactionButton = new JButton("Add");
        addTransactionButton.setBounds(5,140,200,25);
        panel.add(addTransactionButton);
        addTransactionButton.addActionListener(e -> {
            try {
                addTransaction();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton backButton = new JButton("Log out");
        backButton.setBounds(5,170,200,25);
        panel.add(backButton);
        backButton.addActionListener(e -> new ManagementSystemGUI(userId,username));
        backButton.addActionListener(e -> frame.dispose());

        test = new JToggleButton("Incoming");
        test.setBounds(5,200,100,25);
        panel.add(test);
        test.addActionListener(e -> typeOfTransaction());
    }

    public void addTransaction() throws SQLException {
        DatabaseConnection.addTransaction(username, list.getItemAt(list.getSelectedIndex()), (double) spinner.getValue(), transactionDescription.getText());
    }

    public void typeOfTransaction(){
        if(test.isSelected()){
            test.setText("Outgoing");
            typeOfTransaction = 1;
        }else{
            test.setText("Incoming");
            typeOfTransaction = -1;
        }
    }
}
