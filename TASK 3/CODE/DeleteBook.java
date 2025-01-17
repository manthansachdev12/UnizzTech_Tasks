import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteBook extends JFrame {
    private JTextField bookIdField;
    private JButton deleteButton, backButton;

    public DeleteBook() {
        setTitle("Delete Book");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Field to enter book ID to delete
        bookIdField = new JTextField(20);
        deleteButton = new JButton("Delete Book");
        backButton = new JButton("Back to Dashboard");

        deleteButton.addActionListener(e -> deleteBook());
        backButton.addActionListener(e -> goBackToDashboard());

        // Layout for deleting book
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Enter Book ID:"));
        panel.add(bookIdField);
        panel.add(new JLabel());
        panel.add(deleteButton);
        panel.add(new JLabel());
        panel.add(backButton);

        add(panel);
    }

    // Method to delete book
    private void deleteBook() {
        int bookId = Integer.parseInt(bookIdField.getText());

        // Delete the book from the database
        String query = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No book found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the book.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to navigate back to the dashboard
    private void goBackToDashboard() {
        new Dashboard(1).setVisible(true);  // Assuming 1 as the user ID (update as per your requirements)
        dispose(); // Close the current frame
    }
}
