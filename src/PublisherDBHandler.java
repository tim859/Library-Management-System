import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherDBHandler {

    // the database address will need to be changed based on where the database is stored locally

    String jdbcUrl = "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db"; // tim home pc address
    // String jdbcUrl = "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db"; // tim laptop address
    // atif home pc address:
    // atif laptop address:
    Connection connection;

    public List<Publisher> getAllPublishersInDB() {

        List<Publisher> publisherList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet publisherResult = statement.executeQuery("SELECT * FROM publishers");

            while (publisherResult.next()) {

                int pk = publisherResult.getInt("publisherPK");
                String name = publisherResult.getString("publisherName");

                publisherList.add(new Publisher(pk, name));
            }
        }
        catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
        }

        return publisherList;
    }

    public List<Publisher> searchDBForPublisherByName(String publisherName) {

        List<Publisher> searchedPublisherList = new ArrayList<>();

        try {
            // getting the connection using the sql url specified above
            connection = DriverManager.getConnection(jdbcUrl);

            // creating a statement object that will be used to run sql queries
            Statement statement = connection.createStatement();

            // running a sql query that gets all information in the book table along with the hidden row id
            ResultSet publisherResult = statement.executeQuery("SELECT * FROM publishers");


            while (publisherResult.next()) {

                int pk = publisherResult.getInt("publisherPK");
                String name = publisherResult.getString("publisherName");

                // search function
                if (name.equals(publisherName)) {
                    searchedPublisherList.add(new Publisher(pk, name));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
        }
        return searchedPublisherList;
    }

    public boolean addPublisherToDB(String publisherName) {

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO publishers VALUES (?, ?)");
            preparedStatement.setString(2, publisherName);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePublisherFromDB(Publisher publisher) {

        int pk = publisher.getPublisherPK();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM publishers WHERE publisherPK = ?");
            preparedStatement.setInt(1, pk);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }

    public boolean editPublisherInDB(Publisher publisher) {

        int pk = publisher.getPublisherPK();
        String publisherName = publisher.getName();

        try {
            connection = DriverManager.getConnection(jdbcUrl);

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE publishers SET publisherName = ? WHERE publisherPK = ?");
            preparedStatement.setString(1, publisherName);
            preparedStatement.setInt(2, pk);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            return false;
        }
    }
}
