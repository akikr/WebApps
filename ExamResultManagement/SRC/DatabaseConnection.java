import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() throws Exception {
        Connection connection=null;
        try {
            // Get parameters
            String driver = "com.mysql.cj.jdbc.Driver";
            String dburl = "jdbc:mysql://localhost:3305/demo";
            String userName = "Student";
            String password = "dell";

            // Get a connection to Database
            Class.forName(driver);
            connection = DriverManager.getConnection(dburl, userName, password);

        } catch (Exception e) { e.printStackTrace(); }

        return connection;
    }
}
