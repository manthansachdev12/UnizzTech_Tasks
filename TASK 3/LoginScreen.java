import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Login");
        setSize(600, 400);  // Spacious screen for a modern look
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Create the title label at the top
        JLabel titleLabel = new JLabel("Login to Continue", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);  // White text for dark mode

        // Create labels for username and password
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setForeground(Color.WHITE);  // White text for labels
        passwordLabel.setForeground(Color.WHITE);  // White text for labels

        // Create the text fields with rounded corners and modern font
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBackground(new Color(0x1C1C1C));  // Darker background for username
        usernameField.setForeground(Color.WHITE);  // White text
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(0x2C2C2C), 2));  // Darker border for username

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBackground(new Color(0x1C1C1C));  // Darker background for password
        passwordField.setForeground(Color.WHITE);  // White text
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0x2C2C2C), 2));  // Darker border for password

        // Create the login and cancel buttons with smaller sizes
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(0x1ABC9C));  // Darker blue color for login
        loginButton.setForeground(Color.WHITE);  // White text
        loginButton.setFocusPainted(false);  // Remove focus border
        loginButton.setPreferredSize(new Dimension(120, 40));  // Smaller button size
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(0x16A085), 2));  // Darker blue border for login

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setBackground(new Color(0xC0392B));  // Darker red color for cancel
        cancelButton.setForeground(Color.WHITE);  // White text
        cancelButton.setFocusPainted(false);  // Remove focus border
        cancelButton.setPreferredSize(new Dimension(120, 40));  // Smaller button size
        cancelButton.setBorder(BorderFactory.createLineBorder(new Color(0x992D22), 2));  // Darker red border for cancel

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                UserDAO userDAO = new UserDAO();
                int userId = userDAO.authenticateUser(username, password);

                if (userId != -1) {  // If the user is authenticated
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    new Dashboard(userId).setVisible(true);  // Pass userId to Dashboard
                    dispose();  // Close the login screen
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the application when cancel is clicked
            }
        });

        // Create a panel with modern dark design
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());  // Use GridBagLayout for flexible arrangement
        panel.setBackground(new Color(0x121212));  // Darkest background for the panel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));  // Padding around the content

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding for grid elements

        // Add the title label at the top
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        // Add username label and text field
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Add password label and text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Add login and cancel buttons under the input fields (below the password field)
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(loginButton, gbc);

        gbc.gridx = 1;
        panel.add(cancelButton, gbc);

        // Add the panel to the frame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);  // Display the Login screen
            }
        });
    }
}
