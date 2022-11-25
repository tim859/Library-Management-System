import java.sql.*;

public class BookDBHandler {

    // this address will need to be changed based on where the database is stored locally
    String jdbcUrl = "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db"; // this address will need to be changed based on where the database is stored locally
    Connection connection;

    public Book[] searchDBForBooks() {
        Book[] books = new Book[0];
        return books;
    }

    public boolean addBookToDB(Book book) {

        String title = book.getTitle();
        String author = book.getAuthor();
        int year = book.getYearOfPublication();
        String publisher = book.getPublisher();
        String subject = book.getSubject();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            // apparently using PreparedStatement protects from SQL injection, not really a concern but its good practise to use it
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, year);
            pstmt.setString(4, publisher);
            pstmt.setString(5, subject);

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

    public boolean deleteBookFromDB(Book book) {

        return false;
    }
}
