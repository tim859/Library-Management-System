import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;


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
    private JTextField manageBooksBookTitleTextField;
    private JTextField manageBooksBookYearTextField;
    private JTextField manageBooksBookSubjectTextField;
    private JTextField manageBooksBookAuthorTextField;
    private JTextField manageBooksBookPublisherTextField;
    private JButton manageBooksAddBookButton;
    private JButton manageBooksDeleteBookButton;
    private JButton manageBooksEditBookButton;
    private JButton manageBooksClearFieldsButton;
    private JButton manageBooksMainMenuButton;
    private JButton manageBooksSearchBooksButton;

    private DefaultListModel<Book> bookListModel;
    private JList<Book> manageBooksListOfBooks;
    private JPanel manageAuthorsPanel;
    private JPanel managePublishersPanel;
    private JTextField manageAuthorsAuthorFirstNameTextField;
    private JTextField manageAuthorsAuthorSurnameTextField;
    private JList manageAuthorsListOfAuthorsList;
    private JList manageAuthorsListOfBooksBySelectedAuthorList;
    private JLabel manageBooksManageBooksLabel;
    private JLabel manageBooksListOfBooksList;
    private JLabel manageBooksBookTitleLabel;
    private JLabel manageBooksBookYearLabel;
    private JLabel manageBooksBookSubjectLabel;
    private JLabel manageBooksBookAuthorLabel;
    private JLabel manageBooksBookPublisherLabel;
    private JButton manageAuthorsAddAuthorButton;
    private JButton manageAuthorsDeleteAuthorButton;
    private JButton manageAuthorsEditAuthorButton;
    private JButton manageAuthorsClearFieldsButton;
    private JButton manageAuthorsMainMenuButton;
    private JButton manageAuthorsSearchAuthorsButton;
    private JLabel manageAuthorsManageAuthorsLabel;
    private JLabel manageAuthorsAuthorFirstNameLabel;
    private JLabel manageAuthorsAuthorSurnameLabel;
    private JLabel manageAuthorsListOfAuthorsLabel;
    private JLabel manageAuthorsListOfBooksBySelectedAuthorLabel;
    private JList managePublishersListOfPublishersList;
    private JTextField managePublishersPublisherNameTextField;
    private JList managePublishersListOfBooksBySelectedPublisherList;
    private JButton managePublishersSearchPublishersButton;
    private JButton managePublishersAddPublisherButton;
    private JButton managePublishersDeletePublisherButton;
    private JButton managePublishersEditPublisherButton;
    private JButton managePublishersClearFieldsButton;
    private JButton managePublishersMainMenuButton;
    private JLabel managePublishersManagePublishersLabel;
    private JLabel managePublishersListOfPublishersLabel;
    private JLabel managePublishersPublisherNameLabel;
    private JLabel managePublishersListOfBooksBySelectedPublisherLabel;

    BookHandler bookHandler = new BookHandler();
    AuthorHandler authorHandler = new AuthorHandler();
    PublisherHandler publisherHandler = new PublisherHandler();

    Font titleFont = new Font(Font.SERIF, Font.BOLD, 50);
    Font subtitleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 30);

    public static void main(String[] args) {
        JFrame mainMenuFrame = new JFrame("MainGUI");
        mainMenuFrame.setContentPane(new MainGUI().mainPanel);
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.pack();
        mainMenuFrame.setVisible(true);

        BookListHandler.main();
    }

    public MainGUI() {
        bookListModel = new DefaultListModel<>();

        manageBooksListOfBooks = new JList<>(bookListModel);

        mainMenuLabel.setFont(titleFont);
        mainMenuManageBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(manageBooksPanel);
            }
        });
        mainMenuManageAuthorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(manageAuthorsPanel);
            }
        });
        mainMenuManagePublishersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(managePublishersPanel);
            }
        });
        manageBooksMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(mainMenuPanel);
            }
        });
        manageAuthorsMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(mainMenuPanel);
            }
        });
        managePublishersMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(mainMenuPanel);
            }
        });
        mainMenuQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        manageBooksSearchBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Book> searchedBookList = new ArrayList<>();
                if (Objects.equals(manageBooksBookYearTextField.getText(), "")) {
                    // year field empty (trying to parse an int when its an empty string throws an error so needs to be handled separately
                    searchedBookList = bookHandler.searchForBook(manageBooksBookTitleTextField.getText(), manageBooksBookAuthorTextField.getText(), 1, manageBooksBookPublisherTextField.getText(), manageBooksBookSubjectTextField.getText());
                }
                else {
                    // year field not empty
                    searchedBookList = bookHandler.searchForBook(manageBooksBookTitleTextField.getText(), manageBooksBookAuthorTextField.getText(), Integer.parseInt(manageBooksBookYearTextField.getText()), manageBooksBookPublisherTextField.getText(), manageBooksBookSubjectTextField.getText());
                }
                System.out.println(searchedBookList);

                // manageBooksListOfBooks = new JList<>(bookListModel);
            }
        });
        manageBooksAddBookButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(manageBooksBookYearTextField.getText(), "")) {
                    if (bookHandler.addBook(manageBooksBookTitleTextField.getText(), manageBooksBookAuthorTextField.getText(), Integer.parseInt(manageBooksBookYearTextField.getText()), manageBooksBookPublisherTextField.getText(), manageBooksBookSubjectTextField.getText())) {
                        popUpMessage("Book added successfully");
                    }
                    else {
                        popUpMessage("Failed to add book");
                    }
                }
                else {
                    popUpMessage("Failed to add book");
                }
            }
        });
        manageBooksDeleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //bookHandler.deleteBook();
            }
        });
        manageBooksEditBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookHandler.editBook();
            }
        });
        manageAuthorsSearchAuthorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorHandler.searchAuthor();
            }
        });
        manageAuthorsAddAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorHandler.addAuthor();
            }
        });
        manageAuthorsDeleteAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorHandler.deleteAuthor();
            }
        });
        manageAuthorsEditAuthorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorHandler.editAuthor();
            }
        });
        managePublishersSearchPublishersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publisherHandler.searchPublisher();
            }
        });
        managePublishersAddPublisherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publisherHandler.addPublisher();
            }
        });
        managePublishersDeletePublisherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publisherHandler.deletePublisher();
            }
        });
        managePublishersEditPublisherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publisherHandler.editPublisher();
            }
        });
    }

    private void changePanel(JPanel newPanel) {
        parentPanel.removeAll();
        parentPanel.add(newPanel);
        parentPanel.repaint();
        parentPanel.revalidate();
    }

    void popUpMessage(String message) {
        JFrame parent = new JFrame();

        JOptionPane.showMessageDialog(parent, message);
    }
}
