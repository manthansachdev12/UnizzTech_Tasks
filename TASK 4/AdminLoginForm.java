import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Color primaryColor = new Color(70, 130, 180);
    private Color backgroundColor = new Color(240, 240, 240);
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font headerFont = new Font("Segoe UI", Font.BOLD, 24);

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/admin_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Mypassw0rd$$";

    public AdminLoginForm() {
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Changed from EXIT_ON_CLOSE to DISPOSE_ON_CLOSE
        setSize(750, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);

        // Create split panels for a two-column design
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(375);
        splitPane.setDividerSize(0);
        splitPane.setBorder(null);

        // Left Panel (Welcome Message)
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(primaryColor);

        // Welcome content panel
        JPanel welcomeContent = new JPanel();
        welcomeContent.setLayout(new BoxLayout(welcomeContent, BoxLayout.Y_AXIS));
        welcomeContent.setBackground(primaryColor);

        JLabel welcomeLabel = new JLabel("Admin Panel");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Authorized Personnel Only");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        welcomeContent.add(welcomeLabel);
        welcomeContent.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomeContent.add(subtitleLabel);

        leftPanel.add(welcomeContent);

        // Right Panel (Login Form)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(backgroundColor);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Form content
        JLabel formTitle = new JLabel("Administrator Login");
        formTitle.setFont(headerFont);
        formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Username field
        JLabel usernameLabel = new JLabel("Admin Username");
        usernameLabel.setFont(mainFont);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameField = createStyledTextField();
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Password field
        JLabel passwordLabel = new JLabel("Admin Password");
        passwordLabel.setFont(mainFont);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordField = createStyledPasswordField();
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Login button
        JButton loginButton = createStyledButton("Login as Administrator");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add components to right panel
        rightPanel.add(formTitle);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        rightPanel.add(usernameLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(usernameField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel.add(passwordLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        rightPanel.add(loginButton);

        // Add panels to split pane
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);

        // Add split pane to frame
        add(splitPane, BorderLayout.CENTER);

        // Add action listener
        loginButton.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            if (validateAdminLogin(username, password)) {
                JOptionPane.showMessageDialog(this,
                        "Administrator Login Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Close the login window and open dashboard
                dispose();
                new AdminDashboard().setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid administrator credentials",
                        "Access Denied",
                        JOptionPane.ERROR_MESSAGE);
                // Clear password field for security
                passwordField.setText("");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private boolean validateAdminLogin(String username, String password) throws SQLException {
        String query = "SELECT * FROM admin_users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password); // In production, use password hashing!

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if credentials are valid
            }
        }
    }

    // UI component creation methods remain the same
    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(mainFont);
        field.setMaximumSize(new Dimension(300, 40));
        field.setPreferredSize(new Dimension(300, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setFont(mainFont);
        field.setMaximumSize(new Dimension(300, 40));
        field.setPreferredSize(new Dimension(300, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(mainFont);
        button.setForeground(Color.WHITE);
        button.setBackground(primaryColor);
        button.setMaximumSize(new Dimension(300, 40));
        button.setPreferredSize(new Dimension(300, 40));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(primaryColor.darker());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(primaryColor);
            }
        });
        return button;
    }

    public static void main(String[] args) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            AdminLoginForm loginForm = new AdminLoginForm();
            loginForm.setVisible(true);
        });
    }
}