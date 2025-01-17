import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateBook extends JFrame {
    private JTextField bookIdField, titleField, authorField, genreField;
    private JButton updateButton, backButton;

    public UpdateBook() {
        setTitle("Update Book");
        setSize(300, 300);  // Adjusted size to accommodate bookIdField
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fields to search and update book details
        bookIdField = new JTextField(20);
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        genreField = new JTextField(20);
        updateButton = new JButton("Update Book");
        backButton = new JButton("Back to Dashboard");

        // Action listeners for buttons
        updateButton.addActionListener(e -> updateBookDetails());
        backButton.addActionListener(e -> goBackToDashboard());

        // Layout for searching and updating book
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));  // Adjusted grid layout for an extra input field (bookId)
        panel.add(new JLabel("Book ID:"));
        panel.add(bookIdField);
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreField);
        panel.add(new JLabel());
        panel.add(updateButton);
        panel.add(new JLabel());
        panel.add(backButton);

        add(panel);
    }

    // Method to update book details after searching by ID
    private void updateBookDetails() {
        String bookIdText = bookIdField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();

        if (bookIdText.isEmpty() || title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdText); // Convert bookId to integer

            // Query to check if the book exists
            String checkQuery = "SELECT book_id FROM books WHERE book_id = ?";
            try (Connection conn = DatabaseConnection.connect();
                 PreparedStatement stmt = conn.prepareStatement(checkQuery)) {

                stmt.setInt(1, bookId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Book found, proceed to update
                    String updateQuery = "UPDATE books SET title = ?, author = ?, genre = ? WHERE book_id = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setString(1, title);
                        updateStmt.setString(2, author);
                        updateStmt.setString(3, genre);
                        updateStmt.setInt(4, bookId);

                        int rowsUpdated = updateStmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(this, "Book updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to update the book.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No book found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid book ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while updating the book.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to navigate back to the dashboard
    private void goBackToDashboard() {
        new Dashboard(1).setVisible(true);  // Assuming 1 as the user ID (update as per your requirements)
        dispose(); // Close the current frame
    }
}
