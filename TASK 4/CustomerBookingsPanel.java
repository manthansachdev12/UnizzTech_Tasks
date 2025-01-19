import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerBookingsPanel extends JPanel {

    private JTable bookingsTable;
    private DefaultTableModel tableModel;

    public CustomerBookingsPanel() {
        setLayout(new BorderLayout());

        // Panel Title
        JLabel title = new JLabel("Customer Bookings", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setOpaque(true);
        title.setBackground(new Color(70, 130, 180)); // Steel Blue
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(0, 50)); // Fixed height
        add(title, BorderLayout.NORTH);

        // Table Model
        String[] columnNames = {"ID", "Customer Name", "Room Type", "Check-In Date", "Check-Out Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookingsTable = new JTable(tableModel);
        bookingsTable.setRowHeight(25);
        bookingsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        bookingsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        bookingsTable.getTableHeader().setBackground(new Color(100, 149, 237)); // Cornflower Blue
        bookingsTable.getTableHeader().setForeground(Color.WHITE);

        // Scroll Pane for Table
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch Data from Database
        loadBookingsFromDatabase();
    }

    // Fetch data from MySQL database and populate the table
    private void loadBookingsFromDatabase() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM Bookings";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Populate table
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customerName = resultSet.getString("customer_name");
                String roomType = resultSet.getString("room_type");
                String checkInDate = resultSet.getString("check_in_date");
                String checkOutDate = resultSet.getString("check_out_date");

                tableModel.addRow(new Object[]{id, customerName, roomType, checkInDate, checkOutDate});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading bookings: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
