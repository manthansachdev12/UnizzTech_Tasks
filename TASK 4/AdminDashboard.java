import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminDashboard extends JFrame {
    private Color primaryColor = new Color(70, 130, 180);
    private Color selectedColor = new Color(50, 100, 150);
    private Color backgroundColor = new Color(240, 240, 240);
    private Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font headerFont = new Font("Segoe UI", Font.BOLD, 24);

    private JButton selectedButton;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        JPanel sidePanel = createSidePanel();
        JPanel mainContentPanel = createMainContentPanel();

        add(sidePanel, BorderLayout.WEST);
        add(mainContentPanel, BorderLayout.CENTER);
    }

    private JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(200, getHeight()));
        sidePanel.setBackground(primaryColor);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Admin Info Panel
        JLabel adminLabel = new JLabel("Admin Panel");
        adminLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        adminLabel.setForeground(Color.WHITE);
        adminLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 20, 15));
        sidePanel.add(adminLabel);

        // Menu Buttons
        String[] menuItems = {
                "Add Employee",
                "Add Rooms",
                "Add Driver",
                "Logout"
        };

        for (String menuItem : menuItems) {
            JButton button = createMenuButton(menuItem);
            sidePanel.add(button);
            sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        return sidePanel;
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(mainFont);
        button.setForeground(Color.WHITE);
        button.setBackground(primaryColor);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setMaximumSize(new Dimension(200, 40));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != selectedButton) {
                    button.setBackground(primaryColor.darker());
                    button.setContentAreaFilled(true);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != selectedButton) {
                    button.setContentAreaFilled(false);
                }
            }
        });

        button.addActionListener(e -> handleMenuClick(button, text));
        return button;
    }

    private JPanel createMainContentPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        // Cards Panel
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.setBackground(backgroundColor);

        // Add different panels
        cardPanel.add(createEmployeePanel(), "Add Employee");
        cardPanel.add(createRoomsPanel(), "Add Rooms");
        cardPanel.add(createDriverPanel(), "Add Driver");

        mainPanel.add(cardPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createEmployeePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add form fields
        String[] labels = {"Name:", "Age:", "Gender:", "Job:", "Salary:", "Phone:", "Employee ID:", "Email:"};
        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            fields[i] = new JTextField(20);
            formPanel.add(fields[i], gbc);
        }

        // Save Button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(primaryColor);
        saveButton.setForeground(Color.BLUE);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                saveEmployee(fields);
                clearFields(fields);
                showSuccess("Employee added successfully!");
            } catch (SQLException ex) {
                showError("Error adding employee: " + ex.getMessage());
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createRoomsPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = {"Room Number:", "Room Type:", "Bed Type:", "Price:", "Status:"};
        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            fields[i] = new JTextField(20);
            formPanel.add(fields[i], gbc);
        }

        JButton saveButton = new JButton("Add Room");
        saveButton.setBackground(primaryColor);
        saveButton.setForeground(Color.BLUE);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                saveRoom(fields);
                clearFields(fields);
                showSuccess("Room added successfully!");
            } catch (SQLException ex) {
                showError("Error adding room: " + ex.getMessage());
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createDriverPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 20));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] labels = {"Name:", "Age:", "Car Company:", "Car Model:", "License No:", "Phone:", "Location:"};
        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            fields[i] = new JTextField(20);
            formPanel.add(fields[i], gbc);
        }

        JButton saveButton = new JButton("Add Driver");
        saveButton.setBackground(primaryColor);
        saveButton.setForeground(Color.BLUE);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                saveDriver(fields);
                clearFields(fields);
                showSuccess("Driver added successfully!");
            } catch (SQLException ex) {
                showError("Error adding driver: " + ex.getMessage());
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        return panel;
    }

    // Database Operations
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/admin_db", "root", "Mypassw0rd$$");
    }

    private void saveEmployee(JTextField[] fields) throws SQLException {
        String sql = "INSERT INTO employees (name, age, gender, job, salary, phone, employee_id, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < fields.length; i++) {
                stmt.setString(i + 1, fields[i].getText());
            }
            stmt.executeUpdate();
        }
    }

    private void saveRoom(JTextField[] fields) throws SQLException {
        String sql = "INSERT INTO rooms (room_number, room_type, bed_type, price, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < fields.length; i++) {
                stmt.setString(i + 1, fields[i].getText());
            }
            stmt.executeUpdate();
        }
    }

    private void saveDriver(JTextField[] fields) throws SQLException {
        String sql = "INSERT INTO drivers (name, age, car_company, car_model, license_no, phone, location) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < fields.length; i++) {
                stmt.setString(i + 1, fields[i].getText());
            }
            stmt.executeUpdate();
        }
    }

    private void clearFields(JTextField[] fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private void handleMenuClick(JButton button, String menuItem) {
        if (selectedButton != null) {
            selectedButton.setBackground(primaryColor);
            selectedButton.setContentAreaFilled(false);
        }
        button.setBackground(selectedColor);
        button.setContentAreaFilled(true);
        selectedButton = button;

        if (menuItem.equals("Logout")) {
            handleLogout();
        } else {
            cardLayout.show(cardPanel, menuItem);
        }
    }

    private void handleLogout() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new AdminLoginForm().setVisible(true);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new AdminDashboard().setVisible(true);
        });
    }
}