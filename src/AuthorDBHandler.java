import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDBHandler {
    // this address will need to be changed based on where the database is stored locally
    // tim home pc address: "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db"
    // tim laptop address: "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db"
    // atif home pc address:
    // atif laptop address:
    String jdbcUrl = "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db";
    Connection connection;

    public List<Author> getAllAuthorsInDB() {
        List<Author> authorList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet authorResult = statement.executeQuery("SELECT ROWID, * FROM authors");

            while (authorResult.next()) {

                int id = authorResult.getInt("rowid");
                String firstName = authorResult.getString("authorFirstName");
                String surname = authorResult.getString("authorSurname");

                authorList.add(new Author(id, surname, firstName));
            }
        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
        }

        return authorList;
    }

    public List<Author> searchDBForAuthor(String firstName, String surname) {
        List<Author> searchedAuthorList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet authorResult = statement.executeQuery("SELECT ROWID, * FROM authors");


            while (authorResult.next()) {

                int id = authorResult.getInt("rowid");
                String firstName = authorResult.getString("authorFirstName");
                String surname = authorResult.getString("authorSurname");

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
}
