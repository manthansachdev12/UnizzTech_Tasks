import java.sql.*;

public class UserDAO {

    public int authenticateUser(String username, String password) {
        String query = "SELECT id FROM users WHERE name = ? AND password = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");  // Return the userId if authenticated
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Return -1 if authentication fails
    }
}
