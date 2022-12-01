// this class is completely useless until proven otherwise

//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public final class BookListHandler {
//    // the purpose of this class is to hold lists of data. when the program is started, this data is gathered from the
//    // database and is put into array lists where it can be mutated (added to, deleted from and edited) by the user
//    // changes will be made to these arrays as well as to the database concurrently
//
//    static List<Book> bookList = new ArrayList<>();
//
//    public static void main() {
//
//        // this address will need to be changed based on where the database is stored locally
//        String jdbcUrl = "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db"; // this address will need to be changed based on where the database is stored locally
//        // creating a connection object
//        Connection connection;
//
//        try {
//            // getting the connection using the sql url specified above
//            connection = DriverManager.getConnection(jdbcUrl);
//
//            // creating a statement object that will be used to run sql queries
//            Statement statement = connection.createStatement();
//
//            // running a sql query that gets all information in the book table along with the hidden row id
//            ResultSet result = statement.executeQuery("SELECT ROWID, * FROM books");
//
//            while (result.next()) {
//
//                int id = result.getInt("rowid");
//                String title = result.getString("bookTitle");
//                String author = result.getString("bookAuthor");
//                String year = result.getString("bookYearOfPublication");
//                String publisher = result.getString("bookPublisher");
//                String subject = result.getString("bookSubject");
//
//                bookList.add(new Book(id, title, author, Integer.parseInt(year), publisher, subject));
//            }
//        }
//        catch (SQLException e) {
//            System.out.println("Error connecting to SQL database");
//            e.printStackTrace();
//        }
//    }
//
//    public static List<Book> searchBookList(String bookTitle, String bookAuthor, int bookYear, String bookPublisher, String bookSubject) {
//
//        List<Book> searchedBookList = new ArrayList<>();
//
//        for (Book book: bookList) {
//            if (book.getTitle().equals(bookTitle) || book.getAuthor().equals(bookAuthor) || book.getYearOfPublication() == bookYear || book.getPublisher().equals(bookPublisher) || book.getSubject().equals(bookSubject)) {
//                searchedBookList.add(book);
//            }
//        }
//        return searchedBookList;
//    }
//
//    // this class will return the book id to BookHandler which will then use that id to call the addBookToDB method in BookDBHandler
//    public static int addBookToList(String title, String author, int year, String publisher, String subject) {
//        bookList.add(new Book(bookList.size() + 1, title, author, year, publisher, subject));
//        return bookList.size() + 1;
//    }
//}