import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class RemoveEmployee extends JFrame {
    public RemoveEmployee() {
        setTitle("Remove Employee");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(34, 40, 49));

        // Header Label
        JLabel headerLabel = new JLabel("Remove Employee", JLabel.CENTER);
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

        // Employee ID Field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Enter Employee ID:"), gbc);
        gbc.gridx = 1;
        JTextField idField = new JTextField(15);
        formPanel.add(idField, gbc);

        // Remove Button
        JButton removeButton = createButton("Remove");

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(34, 40, 49));
        buttonPanel.add(removeButton);
        formPanel.add(buttonPanel, gbc);

        // Back Button
        JButton backButton = createButton("Back");
        gbc.gridy = 2;
        buttonPanel.add(backButton);
        formPanel.add(buttonPanel, gbc);

        // Remove Action Listener
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Conn c = new Conn();
                    String query = "DELETE FROM employees WHERE employeeid = ?";
                    PreparedStatement pstmt = c.connection.prepareStatement(query);
                    pstmt.setInt(1, id);

                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Employee Removed Successfully!");
                        dispose(); // Close window after successful removal
                    } else {
                        JOptionPane.showMessageDialog(null, "Employee ID not found! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        idField.setText(""); // Clear the input field for a new attempt
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Back Action Listener
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current window
                new MainDashboard().setVisible(true); // Open MainDashboard
            }
        });

        add(headerLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
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
        new RemoveEmployee();
    }
}
