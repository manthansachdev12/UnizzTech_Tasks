import java.sql.*;

public class TransactionDAO {
    public boolean borrowBook(int bookId, int userId) {
        String updateBookQuery = "UPDATE books SET status = 'borrowed' WHERE book_id = ?";
        String insertTransactionQuery = "INSERT INTO transactions (book_id, user_id, borrow_date) VALUES (?, ?, CURDATE())";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertTransactionQuery)) {

            // Update book status
            updateStmt.setInt(1, bookId);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Insert borrow transaction
                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, userId);
                insertStmt.executeUpdate();
                return true; // Book borrowed successfully
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Borrow failed
    }
}
