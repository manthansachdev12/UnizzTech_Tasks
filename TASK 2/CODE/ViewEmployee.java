import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewEmployee extends JFrame {
    public ViewEmployee() {
        setTitle("View Employees");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 40, 49)); // Dark theme background

        // Title
        JLabel titleLabel = new JLabel("Employee Records", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 211, 105)); // Accent color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Table to display employee data
        JTable table;
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Employee ID");
        model.addColumn("Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Job Title");
        model.addColumn("Salary");
        model.addColumn("Phone");
        model.addColumn("Email");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Fetch data from database
        try {
            Conn c = new Conn();
            String query = "SELECT employeeid, name, age, gender, jobTitle, salary, phone, email FROM employees";
            PreparedStatement pstmt = c.connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("employeeid");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String jobTitle = rs.getString("jobTitle");
                double salary = rs.getDouble("salary");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                // Add rows to the table
                model.addRow(new Object[]{id, name, age, gender, jobTitle, salary, phone, email});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data from the database!");
        }

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(57, 62, 70)); // Button color
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            new MainDashboard();
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(34, 40, 49)); // Dark background for buttons
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
