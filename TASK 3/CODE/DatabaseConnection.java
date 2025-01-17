import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/library_db"; // Use your database name
            String user = "root"; // MySQL username
            String password = "Mypassw0rd$$"; // MySQL password

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
