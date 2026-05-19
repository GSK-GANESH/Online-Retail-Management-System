import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PlaceOrder extends JFrame implements ActionListener {

    JTextField productIdField, quantityField, customerIdField;
    JComboBox<String> statusBox;

    public PlaceOrder() {

        setTitle("Place Order");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Product ID:"));
        productIdField = new JTextField();
        panel.add(productIdField);

        panel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        panel.add(new JLabel("Customer ID:"));
        customerIdField = new JTextField();
        panel.add(customerIdField);

        panel.add(new JLabel("Status:"));
        statusBox = new JComboBox<>(new String[]{"Pending", "Completed", "Cancelled"});
        panel.add(statusBox);

        JButton orderBtn = new JButton("Place Order");
        orderBtn.addActionListener(this);

        panel.add(new JLabel(""));
        panel.add(orderBtn);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO orders (product_id, quantity, customer_id, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(productIdField.getText()));
            ps.setInt(2, Integer.parseInt(quantityField.getText()));
            ps.setInt(3, Integer.parseInt(customerIdField.getText()));
            ps.setString(4, statusBox.getSelectedItem().toString());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Order Placed");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}