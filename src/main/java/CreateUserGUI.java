import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateUserGUI extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userText;
    private JPasswordField passwordText;

    private JButton createButton;

    public CreateUserGUI() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(485,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        createButton = new JButton("Create");
        createButton.setBounds(10, 80, 80, 25);
        createButton.addActionListener(this);
        panel.add(createButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = userText.getText();
        String password = passwordText.getText();
        if(!RegexCheck.isUsernameValid(username) || !RegexCheck.isPasswordValid(password)){
            JOptionPane.showMessageDialog(this,"Enter correct username or password");
        } else{
            addUserToDatabase(username, password);
            frame.setVisible(false);
        }

    }

    public void addUserToDatabase(String username, String password){

        try {
            DatabaseConnection.addUser(username,password);
            JOptionPane.showMessageDialog(this,"Account has been created");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"Couldn't create account. There is another user with such name");
        }

    }
}
