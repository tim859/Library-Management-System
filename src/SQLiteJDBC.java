import java.sql.*;

public class SQLiteJDBC {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String jdbcUrl = "jdbc:sqlite:/C:/Uni/Com Sci/Year 3/JVM Programming Languages/Coursework/Library Management System/Databases/libraryDB.db";
        Connection connection;

        try {
            connection = DriverManager.getConnection(jdbcUrl);
            String sql = "SELECT ROWID, * FROM books";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Integer id = result.getInt("rowid");
                String title = result.getString("bookTitle");
                String author = result.getString("bookAuthor");
                String year = result.getString("bookYearOfPublication");
                String publisher = result.getString("bookPublisher");
                String subject = result.getString("bookSubject");

                System.out.println(id + " | " + title + " | " + author + " | " + year);
            }

        } catch (SQLException e) {

            System.out.println("Error connecting to SQL database");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}