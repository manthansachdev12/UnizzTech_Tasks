import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationPanel extends JPanel {

    private JTextField customerNameField;
    private JComboBox<String> roomTypeComboBox;
    private JFormattedTextField checkInField;
    private JFormattedTextField checkOutField;
    private JButton submitButton;

    public ReservationPanel() {
        setLayout(new BorderLayout());

        // Panel Title
        JLabel title = new JLabel("New Reservation", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setOpaque(true);
        title.setBackground(new Color(70, 130, 180)); // Steel Blue
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(0, 50)); // Fixed height
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customer Name
        JLabel customerNameLabel = new JLabel("Customer Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(customerNameLabel, gbc);

        customerNameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(customerNameField, gbc);

        // Room Type
        JLabel roomTypeLabel = new JLabel("Room Type:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(roomTypeLabel, gbc);

        roomTypeComboBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
        gbc.gridx = 1;
        formPanel.add(roomTypeComboBox, gbc);

        // Check-In Date
        JLabel checkInLabel = new JLabel("Check-In Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(checkInLabel, gbc);

        checkInField = new JFormattedTextField();
        checkInField.setColumns(20);
        gbc.gridx = 1;
        formPanel.add(checkInField, gbc);

        // Check-Out Date
        JLabel checkOutLabel = new JLabel("Check-Out Date (YYYY-MM-DD):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(checkOutLabel, gbc);

        checkOutField = new JFormattedTextField();
        checkOutField.setColumns(20);
        gbc.gridx = 1;
        formPanel.add(checkOutField, gbc);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(submitButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Submit Button Action Listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReservationToDatabase();
            }
        });
    }

    // Insert reservation data into the database
    private void addReservationToDatabase() {
        String customerName = customerNameField.getText();
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        String checkInDate = checkInField.getText();
        String checkOutDate = checkOutField.getText();

        if (customerName.isEmpty() || checkInDate.isEmpty() || checkOutDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO Bookings (customer_name, room_type, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, roomType);
            preparedStatement.setString(3, checkInDate);
            preparedStatement.setString(4, checkOutDate);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Reservation added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFormFields();
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving reservation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Clear form fields after successful submission
    private void clearFormFields() {
        customerNameField.setText("");
        roomTypeComboBox.setSelectedIndex(0);
        checkInField.setText("");
        checkOutField.setText("");
    }
}
