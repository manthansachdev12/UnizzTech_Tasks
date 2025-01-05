import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboard extends JFrame {
    public MainDashboard() {
        setTitle("Main Dashboard");
        setLayout(new GridLayout(2, 3, 10, 10));
        getContentPane().setBackground(new Color(34, 40, 49)); // Dark background theme

        // Title Label
        JLabel titleLabel = new JLabel("Employee Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 211, 105)); // Accent color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Buttons
        JButton addButton = createButton("Add Employee");
        JButton viewButton = createButton("View Employees");
        JButton updateButton = createButton("Update Employee");
        JButton removeButton = createButton("Remove Employee");
        JButton logoutButton = createButton("Logout");

        // Button Actions
        addButton.addActionListener(e -> {
            new AddEmployee(); // Open AddEmployee window
            dispose(); // Close the MainDashboard window
        });

        viewButton.addActionListener(e -> {
            new ViewEmployee(); // Open ViewEmployee window
            dispose(); // Close the MainDashboard window
        });

        updateButton.addActionListener(e -> {
            new UpdateEmployee(); // Open UpdateEmployee window
            dispose(); // Close the MainDashboard window
        });

        removeButton.addActionListener(e -> {
            new RemoveEmployee(); // Open RemoveEmployee window
            dispose(); // Close the MainDashboard window
        });

        logoutButton.addActionListener(e -> {
            new WelcomeScreen(); // Open WelcomeScreen window
            dispose(); // Close the MainDashboard window
        });

        // Adding components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 10, 10));
        panel.setBackground(new Color(34, 40, 49)); // Dark background for the panel
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(updateButton);
        panel.add(removeButton);
        panel.add(logoutButton);

        // Add title and button panel to the frame
        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // Set frame size and visibility
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Helper method for button creation
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(57, 62, 70)); // Button background
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40)); // Button size
        return button;
    }

    public static void main(String[] args) {
        new MainDashboard();
    }
}
