import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Dashboard");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background Image
        JLabel background = new JLabel(new ImageIcon("bg.jpg"));
        background.setLayout(new GridLayout(2,2,20,20));
        background.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JButton b1 = createCard("Add Product");
        JButton b2 = createCard("Place Order");
        JButton b3 = createCard("View Orders");
        JButton b4 = createCard("Logout");

        background.add(b1);
        background.add(b2);
        background.add(b3);
        background.add(b4);

        b1.addActionListener(e -> new AddProduct());
        b2.addActionListener(e -> new PlaceOrder());
        b3.addActionListener(e -> new ViewOrders());
        b4.addActionListener(e -> {
            new Login();
            dispose();
        });

        setContentPane(background); // IMPORTANT
        setVisible(true);
    }

    JButton createCard(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setBackground(new Color(255,255,255,200)); // slight transparency
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        return btn;
    }
}