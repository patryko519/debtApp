import javax.swing.*;
import java.sql.SQLException;
import java.util.Vector;

public class AddTransactionGUI extends MainFrame{

    private JComboBox<String> list;
    private SpinnerModel model;
    private JSpinner spinner;
    public AddTransactionGUI(int userId, String username) throws SQLException {

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


        //need to change height
        JTextField transactionDescription = new JTextField();
        transactionDescription.setBounds(100,80,165,50);
        panel.add(transactionDescription);

        JButton addTransactionButton = new JButton("Add");
        addTransactionButton.setBounds(5,110,200,25);
        panel.add(addTransactionButton);
        addTransactionButton.addActionListener(e -> addTransaction());

        JButton backButton = new JButton("Log out");
        backButton.setBounds(5,120,200,25);
        panel.add(backButton);
        backButton.addActionListener(e -> new ManagementSystemGUI(userId,username));
        backButton.addActionListener(e -> frame.dispose());
    }

    public void addTransaction(){
        //String selectedFruit = "You selected " + list.getItemAt(list.getSelectedIndex());
        System.out.println(list.getItemAt(list.getSelectedIndex()));
    }
}
