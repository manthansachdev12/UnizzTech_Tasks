import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class PickUpServicePanel extends JPanel {
    // Table components
    private JTable serviceTable;
    private DefaultTableModel tableModel;

    // Form components
    private JTextField customerNameField;
    private JTextField roomNumberField;
    private JComboBox<String> serviceTypeComboBox;
    private JButton requestButton;
    private JButton refreshButton;

    // Colors
    private final Color PRIMARY_DARK = new Color(47, 54, 64);
    private final Color PRIMARY_LIGHT = new Color(240, 242, 245);
    private final Color ACCENT_COLOR = new Color(72, 126, 176);
    private final Color HOVER_COLOR = new Color(87, 141, 191);
    private final Color TEXT_COLOR = new Color(236, 240, 241);
    private final Color BORDER_COLOR = new Color(218, 225, 231);

    // Fonts
    private final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    public PickUpServicePanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(PRIMARY_LIGHT);
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Create main components
        createRequestPanel();
        createTablePanel();
    }

    private void createRequestPanel() {
        // Left panel for request form
        JPanel leftPanel = new JPanel(new BorderLayout(0, 20));
        leftPanel.setBackground(PRIMARY_LIGHT);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(PRIMARY_LIGHT);
        JLabel titleLabel = new JLabel("Request Pick-Up Service");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_DARK);
        titlePanel.add(titleLabel);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(PRIMARY_LIGHT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Customer Name
        addFormField(formPanel, "Customer Name:",
                customerNameField = createStyledTextField(), gbc, 0);

        // Room Number
        addFormField(formPanel, "Room Number:",
                roomNumberField = createStyledTextField(), gbc, 1);

        // Service Type
        serviceTypeComboBox = createStyledComboBox(
                new String[]{"Pick-Up", "Cleaning", "Laundry"}
        );
        addFormField(formPanel, "Service Type:", serviceTypeComboBox, gbc, 2);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(PRIMARY_LIGHT);

        requestButton = createStyledButton("Request Service", ACCENT_COLOR);
        refreshButton = createStyledButton("Refresh Status", PRIMARY_DARK);

        buttonPanel.add(requestButton);
        buttonPanel.add(refreshButton);

        // Add components to left panel
        leftPanel.add(titlePanel, BorderLayout.NORTH);
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);

        // Add action listeners
        requestButton.addActionListener(e -> handleRequestService());
        refreshButton.addActionListener(e -> handleRefreshStatus());
    }

    private void createTablePanel() {
        // Right panel for table
        JPanel rightPanel = new JPanel(new BorderLayout(0, 15));
        rightPanel.setBackground(PRIMARY_LIGHT);
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Table Header
        JLabel tableTitle = new JLabel("Service Requests Status");
        tableTitle.setFont(HEADER_FONT);
        tableTitle.setForeground(PRIMARY_DARK);

        // Create Table
        String[] columnNames = {"Customer Name", "Room Number", "Service Type", "Request Time", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        serviceTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Style Table
        serviceTable.setRowHeight(30);
        serviceTable.setFont(LABEL_FONT);
        serviceTable.setSelectionBackground(ACCENT_COLOR);
        serviceTable.setSelectionForeground(TEXT_COLOR);
        serviceTable.setGridColor(BORDER_COLOR);
        serviceTable.getTableHeader().setFont(HEADER_FONT);
        serviceTable.getTableHeader().setBackground(PRIMARY_DARK);
        serviceTable.getTableHeader().setForeground(TEXT_COLOR);

        // Scrollpane for table
        JScrollPane scrollPane = new JScrollPane(serviceTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        rightPanel.add(tableTitle, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.CENTER);
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(15);
        field.setFont(LABEL_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(LABEL_FONT);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return comboBox;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(LABEL_FONT);
        button.setForeground(TEXT_COLOR);
        button.setBackground(bgColor);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void addFormField(JPanel panel, String labelText, JComponent field,
                              GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }

    // Database methods remain the same
    private void handleRequestService() {
        String customerName = customerNameField.getText();
        String roomNumber = roomNumberField.getText();
        String serviceType = (String) serviceTypeComboBox.getSelectedItem();

        if (customerName != null && !customerName.isEmpty() && !roomNumber.isEmpty()) {
            requestServiceInDatabase(customerName, Integer.parseInt(roomNumber), serviceType);
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
        }
    }

    private void handleRefreshStatus() {
        fetchServiceStatus("SELECT * FROM services", null);
    }

    private void requestServiceInDatabase(String customerName, int roomNumber, String serviceType) {
        String query = "INSERT INTO services (customer_name, room_number, service_type) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customerName);
            stmt.setInt(2, roomNumber);
            stmt.setString(3, serviceType);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Service Request Submitted Successfully!");
                handleRefreshStatus();
            } else {
                JOptionPane.showMessageDialog(this, "Error submitting service request.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error requesting service: " + e.getMessage());
        }
    }

    private void fetchServiceStatus(String query, String customerName) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
            tableModel.setRowCount(0);

            while (rs.next()) {
                String name = rs.getString("customer_name");
                int roomNumber = rs.getInt("room_number");
                String serviceType = rs.getString("service_type");
                Timestamp requestTime = rs.getTimestamp("request_time");
                String status = rs.getString("status");

                tableModel.addRow(new Object[]{
                        name, roomNumber, serviceType, requestTime, status
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching service status: " + e.getMessage());
        }
    }
}