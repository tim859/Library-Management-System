import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDBHandler {

    // the database address will need to be changed based on where the database is stored locally

    String jdbcUrl = "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db"; // tim home pc address
    // String jdbcUrl = "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db"; // tim laptop address
    // atif home pc address:
    // atif laptop address:
    Connection connection;

    public List<Book> getAllBooksInDB() {
        List<Book> bookList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet bookResult = statement.executeQuery("SELECT ROWID, * FROM books");

            while (bookResult.next()) {

                int id = bookResult.getInt("rowid");
                String title = bookResult.getString("bookTitle");
                int author = bookResult.getInt("bookAuthor"); // author foreign key
                int year = bookResult.getInt("bookYearOfPublication");
                int publisher = bookResult.getInt("bookPublisher"); // publisher foreign key
                String subject = bookResult.getString("bookSubject");

                bookList.add(new Book(id, title, author, year, publisher, subject));
            }
        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
        }

        return bookList;
    }

    public List<Book> searchDBForBook(String bookTitle, int bookAuthor, int bookYear, int bookPublisher, String bookSubject) {

        List<Book> searchedBookList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet bookResult = statement.executeQuery("SELECT ROWID, * FROM books");


            while (bookResult.next()) {

                int id = bookResult.getInt("rowid");
                String title = bookResult.getString("bookTitle");
                int author = bookResult.getInt("bookAuthor"); // author foreign key
                int year = bookResult.getInt("bookYearOfPublication");
                int publisher = bookResult.getInt("bookPublisher"); // publisher foreign key
                String subject = bookResult.getString("bookSubject");

                // search function
                if ((title.equals(bookTitle) || (author == bookAuthor) || (year == bookYear) || publisher == bookPublisher || subject.equals(bookSubject))) {
                    searchedBookList.add(new Book(id, title, author, year, publisher, subject));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
        }
        return searchedBookList;
    }

//    public List<Book> searchDBForBooksByAuthor() {
//
//    }
//
//    public List<Book> searchDBForBooksByPublisher() {
//
//    }

    public boolean addBookToDB(String bookTitle, int bookAuthor, int bookYear, int bookPublisher, String bookSubject) {

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            // apparently using PreparedStatement protects from SQL injection, not really a concern but its good practise to use it
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setInt(2, bookAuthor);
            preparedStatement.setInt(3, bookYear);
            preparedStatement.setInt(4, bookPublisher);
            preparedStatement.setString(5, bookSubject);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBookFromDB(Book book) {

        int id = book.getBookID();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE ROWID = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editBookInDB(Book book) {

        int bookID = book.getBookID();
        String bookTitle = book.getTitle();
        int bookAuthor = book.getAuthor();
        int bookYear = book.getYearOfPublication();
        int bookPublisher = book.getPublisher();
        String bookSubject = book.getSubject();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books SET bookTitle = ?, bookAuthor = ?, bookYearOfPublication = ?, bookPublisher = ?, bookSubject = ? WHERE ROWID = ?");
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setInt(2, bookAuthor);
            preparedStatement.setInt(3, bookYear);
            preparedStatement.setInt(4, bookPublisher);
            preparedStatement.setString(5, bookSubject);
            preparedStatement.setInt(6, bookID);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }
}
