import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddEmployee extends JFrame {
    public AddEmployee() {
        setTitle("Add Employee");
        getContentPane().setBackground(new Color(34, 40, 49)); // Dark background theme

        // Title Label
        JLabel titleLabel = new JLabel("Add Employee", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 211, 105)); // Accent color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Form Labels and Fields
        JLabel nameLabel = createLabel("Name:");
        JLabel ageLabel = createLabel("Age:");
        JLabel genderLabel = createLabel("Gender:");
        JLabel jobLabel = createLabel("Job Title:");
        JLabel salaryLabel = createLabel("Salary:");
        JLabel phoneLabel = createLabel("Phone:");
        JLabel emailLabel = createLabel("Email:");

        JTextField nameField = createTextField();
        JTextField ageField = createTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField jobField = createTextField();
        JTextField salaryField = createTextField();
        JTextField phoneField = createTextField();
        JTextField emailField = createTextField();

        // Buttons
        JButton submitButton = createButton("Submit");
        JButton backButton = createButton("Back");

        // Back button action
        backButton.addActionListener(e -> {
            new MainDashboard(); // Open MainDashboard
            dispose(); // Close the current window
        });

        // Submit button action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String ageText = ageField.getText();
                String gender = (String) genderBox.getSelectedItem();
                String job = jobField.getText();
                String salaryText = salaryField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                // Input validation
                if (name.isEmpty() || ageText.isEmpty() || job.isEmpty() || salaryText.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int age;
                double salary;
                try {
                    age = Integer.parseInt(ageText);
                    salary = Double.parseDouble(salaryText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Age and Salary must be valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Conn c = new Conn();
                    String query = "INSERT INTO employees (name, age, gender, jobTitle, salary, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = c.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                    pstmt.setString(1, name);
                    pstmt.setInt(2, age);
                    pstmt.setString(3, gender);
                    pstmt.setString(4, job);
                    pstmt.setDouble(5, salary);
                    pstmt.setString(6, phone);
                    pstmt.setString(7, email);
                    pstmt.executeUpdate();

                    // Retrieve the generated employeeid
                    ResultSet generatedKeys = pstmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int employeeId = generatedKeys.getInt(1);
                        JOptionPane.showMessageDialog(null, "Employee Added Successfully! Employee ID: " + employeeId);
                    } else {
                        JOptionPane.showMessageDialog(null, "Employee Added Successfully, but could not fetch Employee ID.");
                    }

                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding employee. Please check the database connection.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Layout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 10, 10));
        formPanel.setBackground(new Color(34, 40, 49)); // Dark background for form
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding added

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        formPanel.add(genderLabel);
        formPanel.add(genderBox);
        formPanel.add(jobLabel);
        formPanel.add(jobField);
        formPanel.add(salaryLabel);
        formPanel.add(salaryField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        // Panel for buttons (Submit and Back)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(34, 40, 49)); // Dark background for buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding added
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        // Adding everything to the frame
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Helper methods for consistency in UI design
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(57, 62, 70)); // Button background
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 30)); // Button size
        return button;
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
