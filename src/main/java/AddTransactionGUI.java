import javax.swing.*;
import java.sql.SQLException;
import java.util.Vector;

public class AddTransactionGUI extends MainFrame{
    public AddTransactionGUI(int userId, String username) throws SQLException {

        frame.add(panel);
        panel.setLayout(null);

        Vector<String> names = DatabaseConnection.usersNames();

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JComboBox<String> list = new JComboBox<>(names);
        list.setBounds(100,20,165,25);
        panel.add(list);

//        userText = new JTextField(20);
//        userText.setBounds(100,20,165,25);
//        panel.add(userText);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setBounds(10,50,80,25);
        panel.add(amountLabel);

//        passwordText = new JPasswordField();
//        passwordText.setBounds(100, 50, 165, 25);
//        panel.add(passwordText);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(10,80,80,25);
        panel.add(descriptionLabel);
    }
}
