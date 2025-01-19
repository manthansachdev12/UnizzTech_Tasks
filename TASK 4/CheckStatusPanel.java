import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckStatusPanel extends JPanel {

    // Table for displaying booking status
    private JTable statusTable;
    private DefaultTableModel tableModel;

    // Panel components
    private JTextField searchField;
    private JButton searchButton;
    private JButton refreshButton;

    // Colors
    private final Color PRIMARY_DARK = new Color(47, 54, 64);
    private final Color PRIMARY_LIGHT = new Color(240, 242, 245);
    private final Color BUTTON_COLOR = new Color(72, 126, 176);
    private final Color BUTTON_HOVER_COLOR = new Color(87, 141, 191);
    private final Color TEXT_COLOR = new Color(236, 240, 241);

    public CheckStatusPanel() {
        setLayout(new BorderLayout());
        setBackground(PRIMARY_LIGHT);

        // Title Label
        JLabel titleLabel = new JLabel("Check Room Booking Status");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_DARK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Search Field and Button
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(PRIMARY_LIGHT);

        JLabel searchLabel = new JLabel("Search by Customer Name: ");
        searchField = new JTextField(20);
        searchButton = createButton("Search");
        refreshButton = createButton("Refresh");

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);

        // Table for Status Display
        String[] columnNames = {"Customer Name", "Room Number", "Reservation Date", "Check-in Date", "Check-out Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        statusTable = new JTable(tableModel);
        statusTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        statusTable.setBackground(PRIMARY_LIGHT);
        statusTable.setForeground(TEXT_COLOR);

        JScrollPane tableScrollPane = new JScrollPane(statusTable);

        // Adding components to the main panel
        add(titleLabel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        // Set up event handlers
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearchBooking();
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

    private void handleSearchBooking() {
        String customerName = searchField.getText();
        if (customerName != null && !customerName.isEmpty()) {
            // Perform the search based on the customer name
            fetchBookings("SELECT * FROM bookings WHERE customer_name LIKE ?", customerName);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a customer name to search.");
        }
    }

    private void handleRefreshStatus() {
        // Refresh the status to show all bookings
        fetchBookings("SELECT * FROM bookings", null);
    }

    private void fetchBookings(String query, String customerName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // If a customer name is provided, set it as a parameter
            if (customerName != null) {
                stmt.setString(1, "%" + customerName + "%");
            }

            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0);  // Clear existing rows

            while (rs.next()) {
                String name = rs.getString("customer_name");
                int roomNumber = rs.getInt("room_number");
                Date reservationDate = rs.getDate("reservation_date");
                Date checkInDate = rs.getDate("check_in_date");
                String status = rs.getString("status");

                tableModel.addRow(new Object[]{
                        name,
                        roomNumber,
                        reservationDate,
                        checkInDate,
                        status
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching booking data: " + e.getMessage());
        }
    }
}
