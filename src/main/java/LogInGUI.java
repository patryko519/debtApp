import javax.swing.*;

public class LogInGUI extends Frame {

    private final JTextField userText;
    private final JPasswordField passwordText;

    public LogInGUI() {
        frame.add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton logInButton = new JButton("Log In");
        logInButton.setBounds(10, 80, 80, 25);
        logInButton.addActionListener(e -> logInButton());
        panel.add(logInButton);

        JButton goBackButton = new JButton("Back");
        goBackButton.setBounds(10, 110, 80, 25);
        panel.add(goBackButton);
        goBackButton.addActionListener(a -> exitPanel());
    }

    public void logInButton(){
        String username = userText.getText();
        String password = passwordText.getText();

        int idOfAccount = DatabaseConnection.getUserIdIfExists(username,password);

        if(idOfAccount != -1){
            frame.dispose();
            new ManagementSystemGUI(idOfAccount, username);
        } else{
            JOptionPane.showMessageDialog(this,"Incorrect username or login");
        }
    }

}
