import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDBHandler {

    // this address will need to be changed based on where the database is stored locally
    // tim home pc address: "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db"
    // tim laptop address: "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db"
    // atif home pc address:
    // atif laptop address:
    String jdbcUrl = "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db";
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
                String author = bookResult.getString("bookAuthor");
                int year = bookResult.getInt("bookYearOfPublication");
                String publisher = bookResult.getString("bookPublisher");
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

    public List<Book> searchDBForBook(String bookTitle, String bookAuthor, int bookYear, String bookPublisher, String bookSubject) {

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
                String author = bookResult.getString("bookAuthor");
                int year = bookResult.getInt("bookYearOfPublication");
                String publisher = bookResult.getString("bookPublisher");
                String subject = bookResult.getString("bookSubject");

                // search function
                if ((title.equals(bookTitle) || (author.equals(bookAuthor)) || (year == bookYear) || publisher.equals(bookPublisher) || subject.equals(bookSubject))) {
                    searchedBookList.add(new Book(id, title, author, year, publisher, subject));
                }

                // add record to array of books

            }

            // implement searches here

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
        }

        return searchedBookList;
    }

    public boolean addBookToDB(String bookTitle, String bookAuthor, int bookYear, String bookPublisher, String bookSubject) {

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            // apparently using PreparedStatement protects from SQL injection, not really a concern but its good practise to use it
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setString(2, bookAuthor);
            preparedStatement.setInt(3, bookYear);
            preparedStatement.setString(4, bookPublisher);
            preparedStatement.setString(5, bookSubject);

            System.out.println("sql query " + preparedStatement.toString());

            preparedStatement.executeUpdate();

            System.out.println("Insert successful");

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

            System.out.println("sql query " + preparedStatement.toString());

            preparedStatement.executeUpdate();

            System.out.println("Deletion successful");

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editBookInDB(Book book) { // untested

        int bookID = book.getBookID();
        String bookTitle = book.getTitle();
        String bookAuthor = book.getAuthor();
        int bookYear = book.getYearOfPublication();
        String bookPublisher = book.getPublisher();
        String bookSubject = book.getSubject();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books SET bookTitle = ?, bookAuthor = ?, bookYearOfPublication = ?, bookPublisher = ?, bookSubject = ? WHERE ROWID = ?");
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setString(2, bookAuthor);
            preparedStatement.setInt(3, bookYear);
            preparedStatement.setString(4, bookPublisher);
            preparedStatement.setString(5, bookSubject);
            preparedStatement.setInt(6, bookID);

            System.out.println("sql query " + preparedStatement.toString());

            preparedStatement.executeUpdate();

            System.out.println("Update successful");

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }
}
