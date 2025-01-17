import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame {
    private int loggedInUserId;

    public Dashboard(int userId) {
        this.loggedInUserId = userId; // Storing the logged-in user ID
        setTitle("Library Management System");
        setSize(700, 400);  // Adjusted size for a spacious layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Create the title label at the top with a dark theme
        JLabel titleLabel = new JLabel("Library Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); // White text for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Create buttons for various actions with dark mode styling
        JButton addBookButton = createButton("Add Book");
        JButton updateBookButton = createButton("Update Book");
        JButton deleteBookButton = createButton("Delete Book");
        JButton searchBookButton = createButton("Search Book");
        JButton logoutButton = createButton("Logout");

        // Button Actions
        addBookButton.addActionListener(e -> {
            new AddBook().setVisible(true);
            dispose();
        });

        updateBookButton.addActionListener(e -> {
            new UpdateBook().setVisible(true);
            dispose();
        });

        deleteBookButton.addActionListener(e -> {
            new DeleteBook().setVisible(true);
            dispose();
        });

        searchBookButton.addActionListener(e -> {
            new SearchBook().setVisible(true);
            dispose();
        });

        logoutButton.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            dispose();
        });

        // Create a panel for buttons with GridLayout (Dark mode style)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));  // 2 rows, 3 columns, 10px padding between buttons
        buttonPanel.setBackground(new Color(34, 40, 49));  // Dark background for the panel
        buttonPanel.add(addBookButton);
        buttonPanel.add(updateBookButton);
        buttonPanel.add(deleteBookButton);
        buttonPanel.add(searchBookButton);
        buttonPanel.add(logoutButton);

        // Set the overall background color for the frame (dark theme)
        getContentPane().setBackground(new Color(34, 40, 49));

        // Add title and button panel to the frame
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Set frame size and visibility
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Helper method for button creation with dark mode styling
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(57, 62, 70)); // Dark background
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 30)); // Smaller button size (narrow and not as tall)
        button.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 1)); // Grey border with thinner line
        button.setMargin(new Insets(5, 10, 5, 10)); // Reduced padding inside the button (tighten the look)
        return button;
    }
}
