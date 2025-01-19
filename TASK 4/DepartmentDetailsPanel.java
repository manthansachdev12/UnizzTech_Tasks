import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepartmentDetailsPanel extends JPanel {

    // Table for displaying department details
    private JTable departmentTable;
    private DefaultTableModel tableModel;

    // Panel components
    private JButton addDepartmentButton;
    private JButton updateDepartmentButton;
    private JButton deleteDepartmentButton;

    // Colors
    private final Color PRIMARY_DARK = new Color(47, 54, 64);
    private final Color PRIMARY_LIGHT = new Color(240, 242, 245);
    private final Color BUTTON_COLOR = new Color(72, 126, 176);
    private final Color BUTTON_HOVER_COLOR = new Color(87, 141, 191);
    private final Color TEXT_COLOR = new Color(236, 240, 241);

    public DepartmentDetailsPanel() {
        setLayout(new BorderLayout());
        setBackground(PRIMARY_LIGHT);

        // Title Label
        JLabel titleLabel = new JLabel("Department Details");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(PRIMARY_DARK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Table for Department Details
        String[] columnNames = {"Department Name", "Department Head", "Contact"};
        tableModel = new DefaultTableModel(columnNames, 0);
        departmentTable = new JTable(tableModel);
        departmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        departmentTable.setBackground(PRIMARY_LIGHT);
        departmentTable.setForeground(TEXT_COLOR);

        JScrollPane tableScrollPane = new JScrollPane(departmentTable);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(PRIMARY_LIGHT);

        addDepartmentButton = createButton("Add Department");
        updateDepartmentButton = createButton("Update Department");
        deleteDepartmentButton = createButton("Delete Department");

        // Add buttons to the panel
        buttonPanel.add(addDepartmentButton);
        buttonPanel.add(updateDepartmentButton);
        buttonPanel.add(deleteDepartmentButton);

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
                if (button == addDepartmentButton) {
                    handleAddDepartment();
                } else if (button == updateDepartmentButton) {
                    handleUpdateDepartment();
                } else if (button == deleteDepartmentButton) {
                    handleDeleteDepartment();
                }
            }
        });

        return button;
    }

    private void handleAddDepartment() {
        // You can implement a form for adding a new department here
        String departmentName = JOptionPane.showInputDialog(this, "Enter Department Name:");
        String departmentHead = JOptionPane.showInputDialog(this, "Enter Department Head:");
        String departmentContact = JOptionPane.showInputDialog(this, "Enter Department Contact:");

        // Add the new department to the table
        if (departmentName != null && departmentHead != null && departmentContact != null) {
            tableModel.addRow(new Object[]{departmentName, departmentHead, departmentContact});
        }
    }

    private void handleUpdateDepartment() {
        int selectedRow = departmentTable.getSelectedRow();
        if (selectedRow != -1) {
            String newDepartmentName = JOptionPane.showInputDialog(this, "Update Department Name:", tableModel.getValueAt(selectedRow, 0));
            String newDepartmentHead = JOptionPane.showInputDialog(this, "Update Department Head:", tableModel.getValueAt(selectedRow, 1));
            String newDepartmentContact = JOptionPane.showInputDialog(this, "Update Department Contact:", tableModel.getValueAt(selectedRow, 2));

            // Update the selected row
            tableModel.setValueAt(newDepartmentName, selectedRow, 0);
            tableModel.setValueAt(newDepartmentHead, selectedRow, 1);
            tableModel.setValueAt(newDepartmentContact, selectedRow, 2);
        } else {
            JOptionPane.showMessageDialog(this, "No department selected for update.");
        }
    }

    private void handleDeleteDepartment() {
        int selectedRow = departmentTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirmDelete = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this department?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No department selected for deletion.");
        }
    }
}
