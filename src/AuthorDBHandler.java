import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDBHandler {

    // the database address will need to be changed based on where the database is stored locally

    String jdbcUrl = "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db"; // tim home pc address
    // String jdbcUrl = "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db"; // tim laptop address
    // atif home pc address:
    // atif laptop address:

    Connection connection;

    public List<Author> getAllAuthorsInDB() {
        List<Author> authorList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet authorResult = statement.executeQuery("SELECT * FROM authors");

            while (authorResult.next()) {

                int pk = authorResult.getInt("authorPK");
                String firstName = authorResult.getString("authorFirstName");
                String surname = authorResult.getString("authorSurname");

                authorList.add(new Author(pk, firstName, surname));
            }
        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
        }
        return authorList;
    }

    public List<Author> searchDBForAuthorByName(String authorFirstName, String authorSurname) {

        List<Author> searchedAuthorList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet authorResult = statement.executeQuery("SELECT * FROM authors");


            while (authorResult.next()) {

                int pk = authorResult.getInt("authorPK");
                String firstName = authorResult.getString("authorFirstName");
                String surname = authorResult.getString("authorSurname");

                // search function
                if (firstName.equals(authorFirstName) || surname.equals(authorSurname)) {
                    searchedAuthorList.add(new Author(pk, firstName, surname));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
        }
        return searchedAuthorList;
    }

    public boolean addAuthorToDB(String authorFirstName, String authorSurname) {

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO authors VALUES (?, ?, ?)");
            preparedStatement.setString(2, authorFirstName);
            preparedStatement.setString(3, authorSurname);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAuthorFromDB(Author author) {

        int pk = author.getAuthorPK();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM authors WHERE authorPK = ?");
            preparedStatement.setInt(1, pk);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editAuthorInDB(Author author) {

        int authorPK = author.getAuthorPK();
        String authorFirstName = author.getFirstName();
        String authorSurname = author.getSurname();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE authors SET authorFirstName = ?, authorSurname = ? WHERE authorPK = ?");
            preparedStatement.setString(1, authorFirstName);
            preparedStatement.setString(2, authorSurname);
            preparedStatement.setInt(3, authorPK);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }
}
