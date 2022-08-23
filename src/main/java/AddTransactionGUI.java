import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

public class AddTransactionGUI extends Frame {

    private final JComboBox<String> list;
    private final JFormattedTextField amountField;
    private final JButton transactionType;
    private int typeOfTransaction=1;
    private final String username;
    private final JTextField transactionDescription;

    public AddTransactionGUI(int userId, String username) throws SQLException {
        this.username = username;

        frame.setSize(485,275);

        frame.add(panel);
        panel.setLayout(null);

        Vector<String> names = DatabaseConnection.usersNames();
        names.remove(username);

        transactionType = new JButton("Incoming");
        transactionType.setBounds(192,10,100,25);
        panel.add(transactionType);
        transactionType.addActionListener(e -> typeOfTransaction());

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,50,80,25);
        panel.add(userLabel);

        list = new JComboBox<>(names);
        list.setBounds(100,50,165,25);
        panel.add(list);

        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setBounds(10,80,80,25);
        panel.add(amountLabel);

        amountField = new JFormattedTextField(getMaskFormatter("####.##"));
        amountField.setBounds(100,80,80,25);
        panel.add(amountField);

        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(10,110,80,25);
        panel.add(descriptionLabel);

        transactionDescription = new JTextField();
        transactionDescription.setBounds(100,110,165,50);
        panel.add(transactionDescription);

        JButton addTransactionButton = new JButton("Add");
        addTransactionButton.setBounds(5,170,200,25);
        panel.add(addTransactionButton);
        addTransactionButton.addActionListener(e -> {
            try {
                addTransaction();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(5,200,200,25);
        panel.add(backButton);
        backButton.addActionListener(e -> new ManagementSystemGUI(userId,username));
        backButton.addActionListener(e -> frame.dispose());
    }

    public void addTransaction() throws SQLException {
        if(typeOfTransaction == 1){
            DatabaseConnection.addTransaction(username, list.getItemAt(list.getSelectedIndex()), Double.parseDouble(amountField.getText()), transactionDescription.getText());
        } else{
            DatabaseConnection.addTransaction( list.getItemAt(list.getSelectedIndex()),username, Double.parseDouble(amountField.getText()), transactionDescription.getText());
        }

        JOptionPane.showMessageDialog(this,"Transaction has been added");
    }

    public void typeOfTransaction(){
        if("Incoming".equals(transactionType.getText() )){
            transactionType.setText("Outgoing");
            typeOfTransaction = -1;
        }else{
            transactionType.setText("Incoming");
            typeOfTransaction = 1;
        }
    }

    private MaskFormatter getMaskFormatter(String format) {
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter(format);
            mask.setPlaceholderCharacter('0');
        }catch (ParseException ex) {
            ex.printStackTrace();
        }
        return mask;
    }
}
