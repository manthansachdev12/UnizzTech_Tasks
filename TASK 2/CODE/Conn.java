import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    public Connection connection;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement", "root", "Mypassw0rd$$");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
