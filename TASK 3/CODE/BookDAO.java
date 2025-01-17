import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    // Existing methods ...

    public List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR genre LIKE ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Prepare the query parameters to search for the given text in title, author, or genre
            String searchQuery = "%" + query + "%";
            stmt.setString(1, searchQuery);
            stmt.setString(2, searchQuery);
            stmt.setString(3, searchQuery);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int bookId = rs.getInt("book_id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String genre = rs.getString("genre");
                    String status = rs.getString("status");

                    books.add(new Book(bookId, title, author, genre, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public boolean updateBookStatus(int bookId, String status) {
        String sql = "UPDATE books SET status = ? WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, bookId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
