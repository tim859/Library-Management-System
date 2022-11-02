import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JPanel mainPanel;
    private JPanel tabsPanel;
    private JPanel parentPanel;
    private JButton viewBooksTabButton;
    private JButton addBookTabButton;
    private JPanel viewBooksPanel;
    private JPanel addBookPanel;
    private JButton addAuthorButton;
    private JButton addBookButton;
    private JLabel bookLabel;
    private JLabel titleLabel;
    private JLabel yearLabel;
    private JLabel subjectLabel;
    private JLabel authorLabel;
    private JLabel surnameLabel;
    private JLabel firstNameLabel;
    private JLabel publisherLabel;
    private JLabel publisherNameLabel;

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

        viewBooksTabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(viewBooksPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
        addBookTabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(addBookPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });
    }
}
