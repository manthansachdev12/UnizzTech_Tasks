import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame {
    public Login() {
        setTitle("Login");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(34, 40, 49));

        // Header Label
        JLabel headerLabel = new JLabel("Login to Continue", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(new Color(255, 211, 105));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(34, 40, 49));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        formPanel.add(usernameField, gbc);

        // Password Field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        // Buttons
        JButton loginButton = createButton("Login");
        JButton cancelButton = createButton("Cancel");

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(34, 40, 49));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        formPanel.add(buttonPanel, gbc);

        // Login Action Listener
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Authenticate user
            if (authenticateUser(username, password)) {
                new MainDashboard().setVisible(true);
                dispose(); // Close the login screen
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cancel Action Listener
        cancelButton.addActionListener(e -> {
            dispose(); // Close the login screen
            new WelcomeScreen(); // Open the WelcomeScreen
        });

        add(headerLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private boolean authenticateUser(String username, String password) {
        try {
            // Simple connection setup and user authentication
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement", "root", "Mypassw0rd$$");
            String query = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                conn.close();
                return true; // Valid user
            }
            conn.close();
            return false; // Invalid credentials
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(57, 62, 70)); // Dark button color to match theme
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 30));
        return button;
    }

    public static void main(String[] args) {
        new Login();
    }
}
