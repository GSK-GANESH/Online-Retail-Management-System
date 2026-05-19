import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewOrders extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewOrders() {

        setTitle("View Orders");
        setSize(700, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        // Columns
        model.addColumn("Order ID");
        model.addColumn("Product ID");
        model.addColumn("Quantity");
        model.addColumn("Customer ID");
        model.addColumn("Status");

        loadData();

        add(new JScrollPane(table));
        setVisible(true);
    }

    void loadData() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM orders";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("customer_id"),
                        rs.getString("status")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}