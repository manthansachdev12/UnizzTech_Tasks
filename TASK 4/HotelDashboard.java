import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class HotelDashboard extends JFrame {
    // UI Components
    private JPanel mainPanel;
    private JPanel navigationPanel;
    private JPanel contentPanel;
    private JPanel headerPanel;

    // Navigation buttons
    private JButton bookingsButton;
    private JButton reservationsButton;
    private JButton departmentDetailsButton;
    private JButton roomDetailsButton;
    private JButton checkStatusButton;
    private JButton pickUpServiceButton;
    private JButton checkOutStatusButton;
    private JButton cancelButton;
    private JLabel titleLabel;

    // Colors
    private final Color PRIMARY_DARK = new Color(47, 54, 64);
    private final Color PRIMARY_LIGHT = new Color(240, 242, 245);
    private final Color ACCENT_COLOR = new Color(72, 126, 176);
    private final Color HOVER_COLOR = new Color(87, 141, 191);
    private final Color TEXT_COLOR = new Color(236, 240, 241);
    private final Color CANCEL_COLOR = new Color(192, 57, 43);
    private final Color CANCEL_HOVER_COLOR = new Color(231, 76, 60);

    public HotelDashboard() {
        setTitle("Hotel Reservation Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 700));

        // Initialize main container with custom background
        mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(PRIMARY_LIGHT);

        // Create and style header
        createHeader();

        // Create and style navigation
        createNavigation();

        // Initialize content panel with modern styling
        createContentPanel();

        // Add main components to frame
        add(mainPanel);

        // Apply modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    private void createHeader() {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_DARK);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        titleLabel = new JLabel("Hotel Paradise Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(TEXT_COLOR);

        headerPanel.add(titleLabel, BorderLayout.WEST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    private void createNavigation() {
        navigationPanel = new JPanel();
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.setBackground(PRIMARY_DARK);
        navigationPanel.setPreferredSize(new Dimension(250, getHeight()));
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        // Create navigation buttons
        bookingsButton = createNavButton("Customer Bookings", "bookings");
        reservationsButton = createNavButton("Reservations", "reservations");
        departmentDetailsButton = createNavButton("Department Details", "departmentDetails");
        roomDetailsButton = createNavButton("Room Details", "roomDetails");
        checkStatusButton = createNavButton("Check Status", "checkStatus");
        pickUpServiceButton = createNavButton("Pick Up Service", "pickUpService");
        checkOutStatusButton = createNavButton("Check Out Status", "checkOutStatus");
        cancelButton = createCancelButton();

        // Add padding between buttons
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navigationPanel.add(bookingsButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navigationPanel.add(reservationsButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navigationPanel.add(departmentDetailsButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navigationPanel.add(roomDetailsButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navigationPanel.add(checkStatusButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navigationPanel.add(pickUpServiceButton);
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navigationPanel.add(checkOutStatusButton);
        navigationPanel.add(Box.createVerticalGlue());

        // Add cancel button at the bottom with some padding
        navigationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        navigationPanel.add(cancelButton);

        mainPanel.add(navigationPanel, BorderLayout.WEST);
    }

    private void createContentPanel() {
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(PRIMARY_LIGHT);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Welcome panel with modern styling
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(PRIMARY_LIGHT);

        JLabel welcomeLabel = new JLabel("Welcome to Hotel Paradise Dashboard");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(PRIMARY_DARK);

        welcomePanel.add(welcomeLabel);

        // Add panels to card layout
        contentPanel.add(welcomePanel, "home");
        contentPanel.add(new CustomerBookingsPanel(), "bookings");
        contentPanel.add(new ReservationPanel(), "reservations");
        contentPanel.add(new DepartmentDetailsPanel(), "departmentDetails");
        contentPanel.add(new RoomDetailsPanel(), "roomDetails");
        contentPanel.add(new CheckStatusPanel(), "checkStatus");
        contentPanel.add(new PickUpServicePanel(), "pickUpService");
        contentPanel.add(new CheckOutStatusPanel(), "checkOutStatus");

        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createNavButton(String text, String command) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(220, 45));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setForeground(TEXT_COLOR);
        button.setBackground(PRIMARY_DARK);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Modern hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_DARK);
            }
        });

        // Add action listener
        button.addActionListener(e -> showPanel(command));

        return button;
    }

    private JButton createCancelButton() {
        JButton button = new JButton("Cancel");
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(220, 45));
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(TEXT_COLOR);
        button.setBackground(CANCEL_COLOR);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CANCEL_COLOR, 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Modern hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(CANCEL_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(CANCEL_COLOR);
            }
        });

        // Add action listener to dispose only this frame
        button.addActionListener(e -> dispose());

        return button;
    }

    private void showPanel(String panelName) {
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HotelDashboard::new);
    }
}
