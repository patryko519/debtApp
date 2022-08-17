import javax.swing.*;

public class MainFrame extends JFrame{

    protected JFrame frame;
    protected JPanel panel;

    public MainFrame(){
        frame = new JFrame();
        frame.setSize(485,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel = new JPanel();
    }

    public void exitPanel(){
        frame.dispose();
        new MainGUI();
    }
}
