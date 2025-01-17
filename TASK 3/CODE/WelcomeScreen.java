import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeScreen extends JFrame {

    public WelcomeScreen() {
        // Set the title and size of the window
        setTitle("Library Management System");
        setSize(700, 500);  // Increased the window size for better readability
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window on the screen

        // Create a label with the welcome text
        JLabel welcomeLabel = new JLabel("Welcome to the Library Management System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26));  // Larger font size for better visibility
        welcomeLabel.setForeground(Color.BLACK);  // Black text color for contrast

        // Create a panel to hold the welcome label
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);  // Set white background for simplicity
        panel.add(welcomeLabel, BorderLayout.CENTER);

        // Add padding around the content
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Add the panel to the frame
        add(panel);

        // Set a timer to transition to the Login screen after 3 seconds
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginScreen().setVisible(true);  // Open Login Screen
                dispose();  // Close the Welcome screen
            }
        });
        timer.setRepeats(false);  // Only trigger the timer once after 3 seconds
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WelcomeScreen().setVisible(true);  // Display the Welcome screen
            }
        });
    }
}
