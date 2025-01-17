import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddBook extends JFrame {
    private JTextField titleField, authorField, genreField;

    public AddBook() {
        setTitle("Add Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Title:");
        JLabel authorLabel = new JLabel("Author:");
        JLabel genreLabel = new JLabel("Genre:");
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        genreField = new JTextField(20);
        JButton addButton = new JButton("Add Book");
        JButton backButton = new JButton("Back to Dashboard");

        // Action listener for the Add Book button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = genreField.getText();

                if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled!");
                } else {
                    BookDAO bookDAO = new BookDAO();
                    Connection conn = DatabaseConnection.connect();
                    try {
                        String query = "INSERT INTO books (title, author, genre, status) VALUES (?, ?, ?, 'available')";
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, title);
                        stmt.setString(2, author);
                        stmt.setString(3, genre);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Book added successfully!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to add book.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Action listener for the Back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard(1).setVisible(true);  // Assuming userId 1 for the demo; modify as needed
                dispose();  // Close the AddBook window
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));  // Changed to accommodate 5 rows (4 for form, 1 for back button)
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(genreLabel);
        panel.add(genreField);
        panel.add(addButton);
        panel.add(backButton);  // Add the back button to the panel

        add(panel);
    }
}
