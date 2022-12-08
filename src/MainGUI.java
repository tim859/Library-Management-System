import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JButton manageBooksAddBookButton;
    private JButton manageBooksDeleteBookButton;
    private JButton manageBooksEditBookButton;
    private JButton manageBooksClearFieldsButton;
    private JButton manageBooksMainMenuButton;
    private JButton manageBooksSearchBooksButton;
    private JList<String> manageBooksListOfBooksList;
    private JPanel manageAuthorsPanel;
    private JPanel managePublishersPanel;
    private JTextField manageAuthorsAuthorFirstNameTextField;
    private JTextField manageAuthorsAuthorSurnameTextField;
    private JList<String> manageAuthorsListOfAuthorsList;
    private JList<String> manageAuthorsListOfBooksBySelectedAuthorList;
    private JLabel manageBooksManageBooksLabel;
    private JLabel manageBooksListOfBooksLabel;
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
    private JList<String> managePublishersListOfPublishersList;
    private JTextField managePublishersPublisherNameTextField;
    private JList<String> managePublishersListOfBooksBySelectedPublisherList;
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
    private JComboBox<String> manageBooksBooksPublisherComboBox;
    private JComboBox<String> manageBooksBookAuthorComboBox;
    private JRadioButton mainMenuBubbleSortRadioButton;
    private JRadioButton mainMenuMergeSortRadioButton;
    private JRadioButton mainMenuRadixSortRadioButton;
    private JLabel mainMenuChooseSortAlgoLabel;

    BookHandler bookHandler = new BookHandler();
    AuthorHandler authorHandler = new AuthorHandler();
    PublisherHandler publisherHandler = new PublisherHandler();

    List<Book> listOfBooks;
    List<Author> listOfAuthors;
    List<Book> listOfBooksByAuthor;
    List<Publisher> listOfPublishers;
    List<Book> listOfBooksByPublisher;

    Font titleFont = new Font(Font.SERIF, Font.BOLD, 50);
    Font mediumTitleFont = new Font(Font.SERIF, Font.PLAIN, 40);
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
        manageBooksManageBooksLabel.setFont(subtitleFont);
        manageBooksListOfBooksLabel.setFont(subtitleFont);
        manageAuthorsManageAuthorsLabel.setFont(subtitleFont);
        manageAuthorsListOfAuthorsLabel.setFont(subtitleFont);
        managePublishersManagePublishersLabel.setFont(subtitleFont);
        managePublishersListOfPublishersLabel.setFont(subtitleFont);
        mainMenuManageBooksButton.addActionListener(e -> {
            changePanel(manageBooksPanel);
            listOfBooks = bookHandler.getAllBooks();
            listOfAuthors = authorHandler.getAllAuthors();
            listOfPublishers = publisherHandler.getAllPublishers();
            refreshBookList();

            // update author combo box with all current authors
            manageBooksBookAuthorComboBox.addItem("N/A");
            for (Author author : listOfAuthors) {
                String authorString = author.getFirstName() + " " + author.getSurname();
                manageBooksBookAuthorComboBox.addItem(authorString);
            }

            // update publisher combo box with all current publishers
            manageBooksBooksPublisherComboBox.addItem("N/A");
            for (Publisher publisher : listOfPublishers) {
                String publisherString = publisher.getName();
                manageBooksBooksPublisherComboBox.addItem(publisherString);
            }
        });

        mainMenuManageAuthorsButton.addActionListener(e -> {

            changePanel(manageAuthorsPanel);
            listOfBooks = bookHandler.getAllBooks();
            listOfAuthors = authorHandler.getAllAuthors();
            listOfPublishers = publisherHandler.getAllPublishers();
            refreshAuthorList();
        });

        mainMenuManagePublishersButton.addActionListener(e -> {

            changePanel(managePublishersPanel);
            listOfBooks = bookHandler.getAllBooks();
            listOfAuthors = authorHandler.getAllAuthors();
            listOfPublishers = publisherHandler.getAllPublishers();
            refreshPublisherList();
        });

        manageBooksMainMenuButton.addActionListener(e -> changePanel(mainMenuPanel));

        manageAuthorsMainMenuButton.addActionListener(e -> changePanel(mainMenuPanel));

        managePublishersMainMenuButton.addActionListener(e -> changePanel(mainMenuPanel));

        mainMenuQuitButton.addActionListener(e -> System.exit(0));

        manageBooksSearchBooksButton.addActionListener(e -> {

            try {
                // will only not throw an exception if year field is filled with a valid integer
                listOfBooks = bookHandler.searchForBook(manageBooksBookTitleTextField.getText(), findAuthorPKFromComboBox(), Integer.parseInt(manageBooksBookYearTextField.getText()), findPublisherPKFromComboBox(), manageBooksBookSubjectTextField.getText());
            }
            catch (NumberFormatException ignored) {
                // in any other case, will just pass in 1 as the year instead. could only return a false positive if a book year is 1 which is vanishingly unlikely in a real world scenario
                listOfBooks = bookHandler.searchForBook(manageBooksBookTitleTextField.getText(), findAuthorPKFromComboBox(), 1, findPublisherPKFromComboBox(), manageBooksBookSubjectTextField.getText());
            }
            catch (NullPointerException ignored) {
                listOfBooks = bookHandler.searchForBook(manageBooksBookTitleTextField.getText(), findAuthorPKFromComboBox(), 1, findPublisherPKFromComboBox(), manageBooksBookSubjectTextField.getText());
            }
            refreshBookList();
            clearBookFields();
        });

        manageBooksAddBookButton.addActionListener(e -> {
            try {
                if (bookHandler.addBook(manageBooksBookTitleTextField.getText(), findAuthorPKFromComboBox(), Integer.parseInt(manageBooksBookYearTextField.getText()), findPublisherPKFromComboBox(), manageBooksBookSubjectTextField.getText())) {
                    listOfBooks = bookHandler.getAllBooks();
                    refreshBookList();
                    popUpMessage("Book added successfully");
                }
                else {
                    popUpMessage("Failed to add book");
                }
            }
            catch (NumberFormatException ignored) {
                popUpMessage("Failed to add book - year must be a valid year after 0 AD");
            }
            catch (NullPointerException ignored) {
                popUpMessage("Failed to add book");
            }
        });

        manageBooksDeleteBookButton.addActionListener(e -> {

            // find index of currently selected book in the book jlist
            int index = manageBooksListOfBooksList.getSelectedIndex();

            Book deletedBook = listOfBooks.get(index);

            if (bookHandler.deleteBook(deletedBook)) {
                listOfBooks.remove(index);
                refreshBookList();
                popUpMessage("Book deleted successfully");
            }
            else {
                popUpMessage("Failed to delete book");
            }
        });

        manageBooksEditBookButton.addActionListener(e -> {

            // find index of currently selected book in the book jlist
            int index = manageBooksListOfBooksList.getSelectedIndex();

            try {
                Book editedBook = new Book(listOfBooks.get(index).getBookID(), manageBooksBookTitleTextField.getText(), findAuthorPKFromComboBox(), Integer.parseInt(manageBooksBookYearTextField.getText()), findPublisherPKFromComboBox(), manageBooksBookSubjectTextField.getText());

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
            catch (NumberFormatException ignored) {
                popUpMessage("Failed to add book - year must be a valid year after 0 AD");
            }
            catch (NullPointerException ignored) {
                popUpMessage("Failed to edit book");
            }
        });

        manageAuthorsSearchAuthorsButton.addActionListener(e -> {

            listOfAuthors = authorHandler.searchForAuthor(manageAuthorsAuthorFirstNameTextField.getText(), manageAuthorsAuthorSurnameTextField.getText());

            refreshAuthorList();
            clearAuthorFields();
        });

        manageAuthorsAddAuthorButton.addActionListener(e -> {

            if (authorHandler.addAuthor(manageAuthorsAuthorFirstNameTextField.getText(), manageAuthorsAuthorSurnameTextField.getText())) {
                listOfAuthors = authorHandler.getAllAuthors();
                refreshAuthorList();
                popUpMessage("Author added successfully");
            }
            else {
                popUpMessage("Failed to add author");
            }
        });

        manageAuthorsDeleteAuthorButton.addActionListener(e -> {

            // find index of currently selected author in the author jlist
            int index = manageAuthorsListOfAuthorsList.getSelectedIndex();

            Author deletedAuthor = listOfAuthors.get(index);

            if (authorHandler.deleteAuthor(deletedAuthor)) {
                listOfAuthors.remove(index);
                refreshAuthorList();
                popUpMessage("Author deleted successfully");
            }
            else {
                popUpMessage("Failed to delete author");
            }
        });

        manageAuthorsEditAuthorButton.addActionListener(e -> {

            int index = manageAuthorsListOfAuthorsList.getSelectedIndex();

            Author editedAuthor = new Author(listOfAuthors.get(index).getAuthorPK(), manageAuthorsAuthorFirstNameTextField.getText(), manageAuthorsAuthorSurnameTextField.getText());

            if (authorHandler.editAuthor(editedAuthor)) {
                listOfAuthors.remove(index);
                listOfAuthors.add(index, editedAuthor);
                refreshAuthorList();
                popUpMessage("Author edited successfully");
            }
            else {
                popUpMessage("Failed to edit author");
            }
        });

        managePublishersSearchPublishersButton.addActionListener(e -> {

            listOfPublishers = publisherHandler.searchForPublisher(managePublishersPublisherNameTextField.getText());

            refreshPublisherList();
            clearPublisherFields();
        });

        managePublishersAddPublisherButton.addActionListener(e -> {

            // add publisher
            if (publisherHandler.addPublisher(managePublishersPublisherNameTextField.getText())) {
                listOfPublishers = publisherHandler.getAllPublishers();
                refreshPublisherList();
                popUpMessage("Publisher added successfully");
            }
            // couldn't add publisher for whatever reason
            else {
                popUpMessage("Failed to add publisher");
            }
        });

        managePublishersDeletePublisherButton.addActionListener(e -> {

            // find index of currently selected book in the book jlist
            int index = managePublishersListOfPublishersList.getSelectedIndex();

            Publisher deletedPublisher = listOfPublishers.get(index);

            if (publisherHandler.deletePublisher(deletedPublisher)) {
                listOfPublishers.remove(index);
                refreshPublisherList();
                popUpMessage("Publisher deleted successfully");
            }
            else {
                popUpMessage("Failed to delete publisher");
            }
        });

        managePublishersEditPublisherButton.addActionListener(e -> {

            int index = managePublishersListOfPublishersList.getSelectedIndex();

            Publisher editedPublisher = new Publisher(listOfPublishers.get(index).getPublisherPK(), managePublishersPublisherNameTextField.getText());

            if (publisherHandler.editPublisher(editedPublisher)) {
                listOfPublishers.remove(index);
                listOfPublishers.add(index, editedPublisher);
                refreshPublisherList();
                popUpMessage("Publisher edited successfully");
            }
            else {
                popUpMessage("Failed to edit publisher");
            }
        });

        manageBooksClearFieldsButton.addActionListener(e -> clearBookFields());

        manageAuthorsClearFieldsButton.addActionListener(e -> clearAuthorFields());

        managePublishersClearFieldsButton.addActionListener(e -> clearPublisherFields());

        manageBooksListOfBooksList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = manageBooksListOfBooksList.getSelectedIndex();
                updateBookFields(listOfBooks.get(index).getTitle(), listOfBooks.get(index).getAuthor(), listOfBooks.get(index).getYearOfPublication(), listOfBooks.get(index).getPublisher(), listOfBooks.get(index).getSubject());
            }
        });

        manageAuthorsListOfAuthorsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = manageAuthorsListOfAuthorsList.getSelectedIndex();
                updateAuthorFields(listOfAuthors.get(index).getFirstName(), listOfAuthors.get(index).getSurname());
                refreshBooksByAuthorList(index);
            }
        });

        managePublishersListOfPublishersList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = managePublishersListOfPublishersList.getSelectedIndex();
                updatePublisherField(listOfPublishers.get(index).getName());
                refreshBooksByPublisherList(index);
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

            String authorName = null;
            String publisherName = null;

            // find author name by stored author pk
            for (Author author : listOfAuthors) {
                if (author.getAuthorPK() == book.getAuthor()) {
                    authorName = author.getFirstName() + " " + author.getSurname();
                }
            }

            // find publisher name by stored publisher pk
            for (Publisher publisher : listOfPublishers) {
                if (publisher.getPublisherPK() == book.getPublisher()) {
                    publisherName = publisher.getName();
                }
            }

            String editedBook = "Title: " + book.getTitle() + " | Author: " + authorName + " | Year: " + book.getYearOfPublication() + " | Publisher: " + publisherName + " | Subject: " + book.getSubject();
            editedBookList.add(editedBook);
        }

        for (String editedBook : editedBookList) {
            // adding each book to the list model
            bookListModel.addElement(editedBook);
        }

        // add model to jlist
        manageBooksListOfBooksList.setModel(bookListModel);
    }

    void refreshAuthorList() {

        List<String> editedAuthorList = new ArrayList<>();
        DefaultListModel<String> authorListModel = new DefaultListModel<>();

        for (Author author : listOfAuthors) {
            String editedAuthor = author.getFirstName() + " " + author.getSurname();
            editedAuthorList.add(editedAuthor);
        }

        for (String editedAuthor : editedAuthorList) {
            authorListModel.addElement(editedAuthor);
        }

        manageAuthorsListOfAuthorsList.setModel(authorListModel);
    }

    void refreshPublisherList() {

        List<String> editedPublisherList = new ArrayList<>();
        DefaultListModel<String> publisherListModel = new DefaultListModel<>();

        for (Publisher publisher : listOfPublishers) {
            String editedPublisher = publisher.getName();
            editedPublisherList.add(editedPublisher);
        }

        for (String editedPublisher : editedPublisherList) {
            publisherListModel.addElement(editedPublisher);
        }

        managePublishersListOfPublishersList.setModel(publisherListModel);
    }

    void refreshBooksByAuthorList(int authorIndex) { // the authorIndex is the index of the selected item in the booksByAuthor jList

        int authorPK = listOfAuthors.get(authorIndex).getAuthorPK();
        List<String> booksByAuthorList = new ArrayList<>();
        DefaultListModel<String> authorBooksListModel = new DefaultListModel<>();

        for (Book book : listOfBooks) {
            
            String publisherName = "N/A";
            for (Publisher publisher : listOfPublishers) {
                if (publisher.getPublisherPK() == book.getPublisher()) {
                    publisherName = publisher.getName();
                }
            }
            
            if (book.getAuthor() == authorPK) {
                booksByAuthorList.add("Title: " + book.getTitle() + " | Author: " + listOfAuthors.get(authorIndex).getFirstName() + " " + listOfAuthors.get(authorIndex).getSurname() + " | Year: " + book.getYearOfPublication() + " | Publisher: " + publisherName + " | Subject: " + book.getSubject());
            }
        }

        for (String editedBook : booksByAuthorList) {
            authorBooksListModel.addElement(editedBook);
        }

        manageAuthorsListOfBooksBySelectedAuthorList.setModel(authorBooksListModel);
    }

    void refreshBooksByPublisherList(int publisherIndex) {

        int publisherPK = listOfPublishers.get(publisherIndex).getPublisherPK();
        List<String> booksByPublisherList = new ArrayList<>();
        DefaultListModel<String> publisherBooksListModel = new DefaultListModel<>();

        for (Book book : listOfBooks) {

            String authorName = "N/A";
            for (Author author : listOfAuthors) {
                if (author.getAuthorPK() == book.getAuthor()) {
                    authorName = author.getFirstName() + " " + author.getSurname();
                }
            }

            if (book.getPublisher() == publisherPK) {
                booksByPublisherList.add("Title: " + book.getTitle() + " | Author: " + authorName + " | Year: " + book.getYearOfPublication() + " | Publisher: " + listOfPublishers.get(publisherIndex).getName() + " | Subject: " + book.getSubject());
            }
        }

        for (String editedBook : booksByPublisherList) {
            publisherBooksListModel.addElement(editedBook);
        }

        managePublishersListOfBooksBySelectedPublisherList.setModel(publisherBooksListModel);
    }

    void clearBookFields() {
        manageBooksBookTitleTextField.setText("");
        manageBooksBookAuthorComboBox.setSelectedIndex(0);
        manageBooksBookYearTextField.setText("");
        manageBooksBooksPublisherComboBox.setSelectedIndex(0);
        manageBooksBookSubjectTextField.setText("");
    }

    void clearAuthorFields() {
        manageAuthorsAuthorFirstNameTextField.setText("");
        manageAuthorsAuthorSurnameTextField.setText("");
    }

    void clearPublisherFields() {
        managePublishersPublisherNameTextField.setText("");
    }

    void updateBookFields(String title, int author, int year, int publisher, String subject) {
        manageBooksBookTitleTextField.setText(title);
        manageBooksBookYearTextField.setText(Integer.toString(year));
        manageBooksBookSubjectTextField.setText(subject);

        for (int i = 0; i < listOfAuthors.size(); i++) {
            if (author == listOfAuthors.get(i).getAuthorPK()) {
                manageBooksBookAuthorComboBox.setSelectedIndex(i + 1); // i + 1 to account for the black space at the beginning
            }
        }

        for (int i = 0; i < listOfPublishers.size(); i++) {
            if (publisher == listOfPublishers.get(i).getPublisherPK()) {
                manageBooksBooksPublisherComboBox.setSelectedIndex(i + 1);
            }
        }
    }

    void updateAuthorFields(String firstName, String surname) {
        manageAuthorsAuthorFirstNameTextField.setText(firstName);
        manageAuthorsAuthorSurnameTextField.setText(surname);
    }

    void updatePublisherField(String publisherName) {
        managePublishersPublisherNameTextField.setText(publisherName);
    }

    int findAuthorPKFromComboBox() {
        // find author pk by name in combo box
        for (Author author : listOfAuthors) {
            if ((author.getFirstName() + " " + author.getSurname()).equals(Objects.requireNonNull(manageBooksBookAuthorComboBox.getSelectedItem()).toString())) {
                return author.getAuthorPK();
            }
        }
        return 0;
    }

    int findPublisherPKFromComboBox() {
        // find publisher pk by name in combo box
        for (Publisher publisher : listOfPublishers) {
            if (publisher.getName().equals(Objects.requireNonNull(manageBooksBooksPublisherComboBox.getSelectedItem()).toString())) {
                return publisher.getPublisherPK();
            }
        }
        return 0;
    }
}
