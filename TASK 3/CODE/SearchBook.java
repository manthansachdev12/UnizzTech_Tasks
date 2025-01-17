import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SearchBook extends JFrame {
    private JTextField searchField;

    public SearchBook() {
        setTitle("Search Book");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel searchLabel = new JLabel("Search (Title/Author/Genre):");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back to Dashboard");

        // Action listener for the Search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                BookDAO bookDAO = new BookDAO();
                List<Book> books = bookDAO.searchBooks(searchQuery);

                if (books.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No books found!");
                } else {
                    StringBuilder result = new StringBuilder("Search Results:\n");
                    for (Book book : books) {
                        result.append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, result.toString());
                }
            }
        });

        // Action listener for the Back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard(1).setVisible(true);  // Assuming userId 1 for the demo; modify as needed
                dispose();  // Close the SearchBook window
            }
        });

        // Setting up the panel layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));  // Changed to accommodate 3 rows (2 for the form, 1 for back button)
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(backButton);  // Add the back button to the panel

        add(panel);
    }
}
