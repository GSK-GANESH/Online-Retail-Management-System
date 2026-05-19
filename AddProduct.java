import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddProduct extends JFrame implements ActionListener {

    JTextField nameField, priceField, quantityField;

    public AddProduct() {

        setTitle("Add Product");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Price:"));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        panel.add(quantityField);

        JButton addBtn = new JButton("Add Product");
        addBtn.addActionListener(this);

        panel.add(new JLabel(""));
        panel.add(addBtn);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, nameField.getText());
            ps.setDouble(2, Double.parseDouble(priceField.getText()));
            ps.setInt(3, Integer.parseInt(quantityField.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Product Added");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}