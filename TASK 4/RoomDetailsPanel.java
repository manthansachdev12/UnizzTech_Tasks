import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomDetailsPanel extends JPanel {

    // Table for displaying room details
    private JTable roomTable;
    private DefaultTableModel tableModel;

    // Panel components
    private JButton addRoomButton;
    private JButton updateRoomButton;
    private JButton deleteRoomButton;

    // Colors
    private final Color PRIMARY_DARK = new Color(47, 54, 64);
    private final Color PRIMARY_LIGHT = new Color(240, 242, 245);
    private final Color BUTTON_COLOR = new Color(72, 126, 176);
    private final Color BUTTON_HOVER_COLOR = new Color(87, 141, 191);
    private final Color TEXT_COLOR = new Color(236, 240, 241);

    public RoomDetailsPanel() {
        setLayout(new BorderLayout());
        setBackground(PRIMARY_LIGHT);

        // Title Label
        JLabel titleLabel = new JLabel("Room Details");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_DARK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Table for Room Details
        String[] columnNames = {"Room Number", "Room Type", "Availability", "Price", "Special Features"};
        tableModel = new DefaultTableModel(columnNames, 0);
        roomTable = new JTable(tableModel);
        roomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomTable.setBackground(PRIMARY_LIGHT);
        roomTable.setForeground(TEXT_COLOR);

        JScrollPane tableScrollPane = new JScrollPane(roomTable);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(PRIMARY_LIGHT);

        addRoomButton = createButton("Add Room");
        updateRoomButton = createButton("Update Room");
        deleteRoomButton = createButton("Delete Room");

        // Add buttons to the panel
        buttonPanel.add(addRoomButton);
        buttonPanel.add(updateRoomButton);
        buttonPanel.add(deleteRoomButton);

        // Adding components to the main panel
        add(titleLabel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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

        // Action listeners for buttons
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button == addRoomButton) {
                    handleAddRoom();
                } else if (button == updateRoomButton) {
                    handleUpdateRoom();
                } else if (button == deleteRoomButton) {
                    handleDeleteRoom();
                }
            }
        });

        return button;
    }

    private void handleAddRoom() {
        // You can implement a form for adding a new room here
        String roomNumber = JOptionPane.showInputDialog(this, "Enter Room Number:");
        String roomType = JOptionPane.showInputDialog(this, "Enter Room Type (e.g., Single, Double, Suite):");
        String availability = JOptionPane.showInputDialog(this, "Enter Availability (Available/Not Available):");
        String price = JOptionPane.showInputDialog(this, "Enter Room Price:");
        String specialFeatures = JOptionPane.showInputDialog(this, "Enter Special Features (e.g., Sea View, AC):");

        // Add the new room to the table
        if (roomNumber != null && roomType != null && availability != null && price != null && specialFeatures != null) {
            tableModel.addRow(new Object[]{roomNumber, roomType, availability, price, specialFeatures});
        }
    }

    private void handleUpdateRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow != -1) {
            String newRoomNumber = JOptionPane.showInputDialog(this, "Update Room Number:", tableModel.getValueAt(selectedRow, 0));
            String newRoomType = JOptionPane.showInputDialog(this, "Update Room Type:", tableModel.getValueAt(selectedRow, 1));
            String newAvailability = JOptionPane.showInputDialog(this, "Update Availability:", tableModel.getValueAt(selectedRow, 2));
            String newPrice = JOptionPane.showInputDialog(this, "Update Price:", tableModel.getValueAt(selectedRow, 3));
            String newSpecialFeatures = JOptionPane.showInputDialog(this, "Update Special Features:", tableModel.getValueAt(selectedRow, 4));

            // Update the selected row
            tableModel.setValueAt(newRoomNumber, selectedRow, 0);
            tableModel.setValueAt(newRoomType, selectedRow, 1);
            tableModel.setValueAt(newAvailability, selectedRow, 2);
            tableModel.setValueAt(newPrice, selectedRow, 3);
            tableModel.setValueAt(newSpecialFeatures, selectedRow, 4);
        } else {
            JOptionPane.showMessageDialog(this, "No room selected for update.");
        }
    }

    private void handleDeleteRoom() {
        int selectedRow = roomTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirmDelete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this room?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No room selected for deletion.");
        }
    }
}
