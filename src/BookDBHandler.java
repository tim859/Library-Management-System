import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDBHandler {

    // this address will need to be changed based on where the database is stored locally
    String jdbcUrl = "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db";
    Connection connection;

    public List<Book> searchDBForBook(String bookTitle, String bookAuthor, int bookYear, String bookPublisher, String bookSubject) {

        List<Book> searchedBookList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet result = statement.executeQuery("SELECT ROWID, * FROM books");

            while (result.next()) {

                int id = result.getInt("rowid");
                String title = result.getString("bookTitle");
                String author = result.getString("bookAuthor");
                int year = result.getInt("bookYearOfPublication");
                String publisher = result.getString("bookPublisher");
                String subject = result.getString("bookSubject");

                if ((title.equals(bookTitle) || author.equals(bookAuthor) || (year == bookYear) || publisher.equals(bookPublisher) || subject.equals(bookSubject))) {
                    searchedBookList.add(new Book(id, title, author, year, publisher, subject));
                }
            }

            return searchedBookList;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return searchedBookList;
        }
    }

    public boolean addBookToDB(String bookTitle, String bookAuthor, int bookYear, String bookPublisher, String bookSubject) {

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            // apparently using PreparedStatement protects from SQL injection, not really a concern but its good practise to use it
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, bookTitle);
            pstmt.setString(2, bookAuthor);
            pstmt.setInt(3, bookYear);
            pstmt.setString(4, bookPublisher);
            pstmt.setString(5, bookSubject);

            System.out.println("sql query " + pstmt.toString());

            pstmt.executeUpdate();

            System.out.println("Insert successful");

            return true;

        } catch (SQLException e) {

            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBookFromDB(Book book) { // untested

        int id = book.getBookID();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM books WHERE ROWID = ?");
            pstmt.setInt(1, id);

            System.out.println("sql query " + pstmt.toString());

            pstmt.executeUpdate();

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

            PreparedStatement pstmt = connection.prepareStatement("UPDATE books SET bookTitle = ?, bookAuthor = ?, bookYearOfPublication = ?, bookPublisher = ?, bookSubject = ? WHERE ROWID = ?");
            pstmt.setString(1, bookTitle);
            pstmt.setString(2, bookAuthor);
            pstmt.setInt(3, bookYear);
            pstmt.setString(4, bookPublisher);
            pstmt.setString(5, bookSubject);
            pstmt.setInt(6, bookID);

            System.out.println("sql query " + pstmt.toString());

            pstmt.executeUpdate();

            System.out.println("Update successful");

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }
}
