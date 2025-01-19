import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerFormsPanel extends JPanel {

    // Form components
    private JTextField nameField;
    private JTextField contactField;
    private JComboBox<String> roomTypeComboBox;
    private JTextArea specialRequestArea;
    private JButton submitButton;

    // Colors for styling
    private final Color PRIMARY_DARK = new Color(47, 54, 64);
    private final Color PRIMARY_LIGHT = new Color(240, 242, 245);
    private final Color BUTTON_COLOR = new Color(72, 126, 176);
    private final Color BUTTON_HOVER_COLOR = new Color(87, 141, 191);
    private final Color TEXT_COLOR = new Color(236, 240, 241);

    public CustomerFormsPanel() {
        setLayout(new BorderLayout());
        setBackground(PRIMARY_LIGHT);

        // Title Label
        JLabel titleLabel = new JLabel("Customer Form");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_DARK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBackground(PRIMARY_LIGHT);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        // Contact Field
        JLabel contactLabel = new JLabel("Contact No:");
        contactField = new JTextField();
        formPanel.add(contactLabel);
        formPanel.add(contactField);

        // Room Type ComboBox
        JLabel roomLabel = new JLabel("Room Type:");
        String[] roomTypes = {"Single", "Double", "Suite"};
        roomTypeComboBox = new JComboBox<>(roomTypes);
        formPanel.add(roomLabel);
        formPanel.add(roomTypeComboBox);

        // Special Request Text Area
        JLabel specialRequestLabel = new JLabel("Special Requests:");
        specialRequestArea = new JTextArea(3, 20);
        specialRequestArea.setLineWrap(true);
        specialRequestArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(specialRequestArea);
        formPanel.add(specialRequestLabel);
        formPanel.add(scrollPane);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBackground(BUTTON_COLOR);
        submitButton.setForeground(TEXT_COLOR);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect on button
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButton.setBackground(BUTTON_COLOR);
            }
        });

        // Action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        // Adding components to the main panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(PRIMARY_LIGHT);
        buttonPanel.add(submitButton);

        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleSubmit() {
        String name = nameField.getText();
        String contact = contactField.getText();
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        String specialRequest = specialRequestArea.getText();

        // Here, you can handle the form submission (e.g., save the data or display a confirmation)
        JOptionPane.showMessageDialog(this, "Form submitted successfully!\n" +
                "Name: " + name + "\nContact: " + contact + "\nRoom Type: " + roomType + "\nSpecial Request: " + specialRequest);
    }
}
