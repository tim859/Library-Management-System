import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;


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
    private JList<String> manageBooksListOfBooks;
    private JPanel manageAuthorsPanel;
    private JPanel managePublishersPanel;
    private JTextField manageAuthorsAuthorFirstNameTextField;
    private JTextField manageAuthorsAuthorSurnameTextField;
    private DefaultListModel<Author> authorListModel;
    private JList manageAuthorsListOfAuthorsList;
    private DefaultListModel<Book> booksByAuthorListModel;
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

    List<Book> listOfBooks;

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
                changePanel(manageBooksPanel);
                listOfBooks = bookHandler.getAllBooks();
                refreshBookList();
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

                //List<Book> searchedBookList = new ArrayList<>();
                if (Objects.equals(manageBooksBookYearTextField.getText(), "")) {
                    // year field empty (trying to parse an int when its an empty string throws an error so needs to be handled separately
                    listOfBooks = bookHandler.searchForBook(manageBooksBookTitleTextField.getText(), manageBooksBookAuthorTextField.getText(), 1, manageBooksBookPublisherTextField.getText(), manageBooksBookSubjectTextField.getText());
                }
                else {
                    // year field not empty
                    listOfBooks = bookHandler.searchForBook(manageBooksBookTitleTextField.getText(), manageBooksBookAuthorTextField.getText(), Integer.parseInt(manageBooksBookYearTextField.getText()), manageBooksBookPublisherTextField.getText(), manageBooksBookSubjectTextField.getText());
                }

                refreshBookList();
                clearBookFields();
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
                        popUpMessage("Failed to add book, all fields must be populated");
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
                // find index of currently selected book in the book jlist
                int index = manageBooksListOfBooks.getSelectedIndex();

                Book deletedBook = listOfBooks.get(index);

                if (bookHandler.deleteBook(deletedBook)) {
                    listOfBooks.remove(index);
                    refreshBookList();
                    popUpMessage("Book deleted successfully");
                }
                else {
                    popUpMessage("Failed to delete book");
                }
            }
        });
        manageBooksEditBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // find index of currently selected book in the book jlist
                int index = manageBooksListOfBooks.getSelectedIndex();
                Book editedBook;

                if (!Objects.equals(manageBooksBookYearTextField.getText(), "")) {
                    editedBook = new Book(listOfBooks.get(index).getBookID(), manageBooksBookTitleTextField.getText(), manageBooksBookAuthorTextField.getText(), Integer.parseInt(manageBooksBookYearTextField.getText()), manageBooksBookPublisherTextField.getText(), manageBooksBookSubjectTextField.getText());
                }
                else {
                    editedBook = new Book(listOfBooks.get(index).getBookID(), manageBooksBookTitleTextField.getText(), manageBooksBookAuthorTextField.getText(), 0, manageBooksBookPublisherTextField.getText(), manageBooksBookSubjectTextField.getText());
                }

                if (bookHandler.editBook(editedBook)) {
                    listOfBooks.remove(index);
                    listOfBooks.add(index, editedBook);
                    refreshBookList();
                    popUpMessage("Book edited successfully");
                }
                else {
                    popUpMessage("Failed to edit book");
                }
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
        manageBooksClearFieldsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBookFields();
            }
        });
        manageAuthorsClearFieldsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageAuthorsAuthorFirstNameTextField.setText("");
                manageAuthorsAuthorSurnameTextField.setText("");
            }
        });
        managePublishersClearFieldsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managePublishersPublisherNameTextField.setText("");
            }
        });
        manageBooksListOfBooks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // find index of currently selected book in the book jlist
                int index = manageBooksListOfBooks.getSelectedIndex();

                updateBookFields(listOfBooks.get(index).getTitle(), listOfBooks.get(index).getAuthor(), listOfBooks.get(index).getYearOfPublication(), listOfBooks.get(index).getPublisher(), listOfBooks.get(index).getSubject());
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

    void refreshBookList() {
        List<String> editedBookList = new ArrayList<>();
        DefaultListModel<String> bookListModel = new DefaultListModel<>(); // initialise list model

        // iterating through the list of books
        for (Book book : listOfBooks) {
            String editedBook = "Title: " + book.getTitle() + " | Author: " + book.getAuthor() + " | Year: " + book.getYearOfPublication() + " | Publisher: " + book.getPublisher() + " | Subject: " + book.getSubject();
            editedBookList.add(editedBook);
        }

        for (String editedBook : editedBookList) {
            // adding each book to the list model
            bookListModel.addElement(editedBook);
        }

        // add model to jlist
        manageBooksListOfBooks.setModel(bookListModel);
    }

    void clearBookFields() {
        manageBooksBookTitleTextField.setText("");
        manageBooksBookAuthorTextField.setText("");
        manageBooksBookYearTextField.setText("");
        manageBooksBookPublisherTextField.setText("");
        manageBooksBookSubjectTextField.setText("");
    }

    void updateBookFields(String title, String author, int year, String publisher, String subject) {
        manageBooksBookTitleTextField.setText(title);
        manageBooksBookAuthorTextField.setText(author);
        manageBooksBookYearTextField.setText(Integer.toString(year));
        manageBooksBookPublisherTextField.setText(publisher);
        manageBooksBookSubjectTextField.setText(subject);
    }
}
