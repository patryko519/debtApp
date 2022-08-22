import javax.swing.*;
import java.sql.SQLException;
import java.util.Vector;

public class CheckTransactionGUI extends Frame{

    private final String username;
    private final JComboBox<String> list;
    public CheckTransactionGUI(String username) throws SQLException {
        this.username = username;

        frame.setSize(485,275);

        frame.add(panel);
        panel.setLayout(null);

        Vector<String> names = DatabaseConnection.usersNames();
        names.remove(username);

        list = new JComboBox<>(names);
        list.setBounds(160,20,165,25);
        panel.add(list);
    }
}
