import java.sql.Connection;

public class PublisherDBHandler {
    // this address will need to be changed based on where the database is stored locally
    // tim home pc address: "jdbc:sqlite:/D:\\University of Greenwich\\Year 3\\JVM\\JVM Coursework\\Databases\\libraryDB.db"
    // tim laptop address: "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db"
    // atif home pc address:
    // atif laptop address:
    String jdbcUrl = "jdbc:sqlite:/C:\\Uni\\Com Sci\\Year 3\\JVM Programming Languages\\Coursework\\Library Management System\\Databases\\libraryDB.db";
    Connection connection;
}
