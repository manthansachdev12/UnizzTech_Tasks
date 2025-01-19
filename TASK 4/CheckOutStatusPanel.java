import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOutStatusPanel extends JPanel {

    // Table for displaying checkout status
    private JTable checkoutTable;
    private DefaultTableModel tableModel;

    // Panel components
    private JTextField customerNameField;
    private JTextField roomNumberField;
    private JButton refreshButton;
    private JButton checkoutButton;

    // Colors
    private final Color PRIMARY_DARK = new Color(47, 54, 64);
    private final Color PRIMARY_LIGHT = new Color(240, 242, 245);
    private final Color BUTTON_COLOR = new Color(72, 126, 176);
    private final Color BUTTON_HOVER_COLOR = new Color(87, 141, 191);
    private final Color TEXT_COLOR = new Color(236, 240, 241);

    public CheckOutStatusPanel() {
        setLayout(new BorderLayout());
        setBackground(PRIMARY_LIGHT);

        // Title Label
        JLabel titleLabel = new JLabel("Checkout Status");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_DARK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Checkout Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 10));
        formPanel.setBackground(PRIMARY_LIGHT);

        JLabel nameLabel = new JLabel("Customer Name: ");
        customerNameField = new JTextField(20);

        JLabel roomLabel = new JLabel("Room Number: ");
        roomNumberField = new JTextField(5);

        formPanel.add(nameLabel);
        formPanel.add(customerNameField);
        formPanel.add(roomLabel);
        formPanel.add(roomNumberField);

        // Buttons
        refreshButton = createButton("Refresh Status");
        checkoutButton = createButton("Checkout");

        // Table for displaying checkout statuses
        String[] columnNames = {"Customer Name", "Room Number", "Checkout Time", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        checkoutTable = new JTable(tableModel);
        checkoutTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        checkoutTable.setBackground(PRIMARY_LIGHT);
        checkoutTable.setForeground(TEXT_COLOR);

        JScrollPane tableScrollPane = new JScrollPane(checkoutTable);

        // Adding components to the main panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(PRIMARY_LIGHT);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(refreshButton);

        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(tableScrollPane, BorderLayout.EAST);

        // Set up event handlers
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheckout();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRefreshStatus();
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect for the button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });

        return button;
    }

    private void handleCheckout() {
        String customerName = customerNameField.getText();
        String roomNumber = roomNumberField.getText();

        if (customerName != null && !customerName.isEmpty() && !roomNumber.isEmpty()) {
            // Request checkout if customer name and room number are valid
            checkoutInDatabase(customerName, Integer.parseInt(roomNumber));
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        }
    }

    private void handleRefreshStatus() {
        // Refresh the checkout status table
        fetchCheckoutStatus("SELECT * FROM checkouts", null);
    }

    private void checkoutInDatabase(String customerName, int roomNumber) {
        String query = "UPDATE checkouts SET status = 'Completed' WHERE customer_name = ? AND room_number = ? AND status = 'Pending'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customerName);
            stmt.setInt(2, roomNumber);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Checkout Successful!");
                handleRefreshStatus(); // Refresh the table after the checkout
            } else {
                JOptionPane.showMessageDialog(this, "No pending checkout found for this customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error during checkout: " + e.getMessage());
        }
    }

    private void fetchCheckoutStatus(String query, String customerName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0);  // Clear existing rows

            while (rs.next()) {
                String name = rs.getString("customer_name");
                int roomNumber = rs.getInt("room_number");
                Timestamp checkoutTime = rs.getTimestamp("checkout_time");
                String status = rs.getString("status");

                tableModel.addRow(new Object[]{
                        name,
                        roomNumber,
                        checkoutTime,
                        status
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching checkout status: " + e.getMessage());
        }
    }
}
