import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JPanel mainPanel;
    private JButton mainMenuManageBooksButton;
    private JButton mainMenuManageAuthorsButton;
    private JButton mainMenuManagePublishersButton;
    private JButton mainMenuQuitButton;
    private JPanel mainMenuPanel;
    private JLabel mainMenuLabel;
    private JPanel parentPanel;
    private JPanel manageBooksPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JList list1;
    private JPanel manageAuthorsPanel;
    private JPanel managePublishersPanel;

    Font titleFont = new Font(Font.SERIF, Font.BOLD, 50);
    Font subtitleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);

    public static void main(String[] args) {
        JFrame mainMenuFrame = new JFrame("MainGUI");
        mainMenuFrame.setContentPane(new MainGUI().mainPanel);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);
    }

    public MainGUI() {
        mainMenuLabel.setFont(titleFont);
        mainMenuManageBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(manageBooksPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        mainMenuManageAuthorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(manageAuthorsPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        mainMenuManagePublishersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(managePublishersPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
    }
}
