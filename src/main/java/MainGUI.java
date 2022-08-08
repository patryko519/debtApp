import javax.swing.*;
import java.sql.SQLException;

public class MainGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    public MainGUI(){
        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(485,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);

        panel.setLayout(null);

        label = new JLabel("Welcome to debt manager. Choose your option");
        label.setBounds(10,0,450,20);
        panel.add(label);

        JButton newAccountButton = new JButton("Create new account");
        newAccountButton.setBounds(5,80,150,25);
        panel.add(newAccountButton);
        newAccountButton.addActionListener(e -> new CreateUserGUI());

        JButton logInButton = new JButton("Log in");
        logInButton.setBounds(160,80,150,25);
        panel.add(logInButton);
        logInButton.addActionListener(e -> {
            try {
                LogInSystem.logIntoSystem();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(315,80,150,25);
        panel.add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
    }
}