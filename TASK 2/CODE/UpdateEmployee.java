import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateEmployee extends JFrame {
    public UpdateEmployee() {
        setTitle("Update Employee");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 40, 49)); // Dark theme background
        setLayout(new BorderLayout(10, 10));

        // Title Panel
        JLabel titleLabel = new JLabel("Update Employee Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 211, 105));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(34, 40, 49));
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Components
        JLabel idLabel = createLabel("Enter Employee ID:");
        JTextField idField = createTextField();
        JButton fetchButton = createButton("Fetch Details");

        JLabel nameLabel = createLabel("Name:");
        JTextField nameField = createTextField();

        JLabel ageLabel = createLabel("Age:");
        JTextField ageField = createTextField();

        JLabel jobLabel = createLabel("Job Title:");
        JTextField jobField = createTextField();

        JLabel salaryLabel = createLabel("Salary:");
        JTextField salaryField = createTextField();

        JButton updateButton = createButton("Update");

        // Back Button
        JButton backButton = createButton("Back");
        backButton.addActionListener((ActionEvent e) -> {
            // Close the current window
            dispose();
            // Open MainDashboard window
            new MainDashboard(); // Make sure MainDashboard is a JFrame
        });

        // Add Components to Form
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(idField, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(fetchButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(ageLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(jobLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        formPanel.add(jobField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(salaryLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        formPanel.add(salaryField, gbc);

        gbc.gridx = 1; gbc.gridy = 6;
        formPanel.add(updateButton, gbc);

        gbc.gridx = 1; gbc.gridy = 7;
        formPanel.add(backButton, gbc); // Back button added under update button

        add(formPanel, BorderLayout.CENTER);

        // Fetch Button Action
        fetchButton.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(idField.getText());
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM employees WHERE employeeid = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    nameField.setText(rs.getString("name"));
                    ageField.setText(String.valueOf(rs.getInt("age")));
                    jobField.setText(rs.getString("jobTitle"));
                    salaryField.setText(String.valueOf(rs.getDouble("salary")));
                } else {
                    JOptionPane.showMessageDialog(null, "Employee not found!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Update Button Action
        updateButton.addActionListener((ActionEvent e) -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String job = jobField.getText();
            double salary = Double.parseDouble(salaryField.getText());

            try {
                Conn c = new Conn();
                String query = "UPDATE employees SET name = ?, age = ?, jobTitle = ?, salary = ? WHERE id = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, job);
                pstmt.setDouble(4, salary);
                pstmt.setInt(5, id);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Employee Updated Successfully!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

    // Helper Methods to Create Components
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(57, 62, 70));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        new UpdateEmployee();
    }
}
