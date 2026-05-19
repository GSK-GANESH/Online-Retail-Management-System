import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField user;
    JPasswordField pass;

    public Login() {

        setTitle("Retail System");
        setSize(800, 500);        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));

        // LEFT PANEL
        JPanel left = new JPanel();
        left.setBackground(Color.WHITE);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(60, 80, 60, 80));

        JLabel title = new JLabel("Welcome Back");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Login to your account");
        subtitle.setForeground(Color.GRAY);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        user = new JTextField();
        pass = new JPasswordField();

        Dimension fieldSize = new Dimension(250, 40);
        user.setMaximumSize(fieldSize);
        pass.setMaximumSize(fieldSize);

        user.setBorder(BorderFactory.createTitledBorder("Username"));
        pass.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton login = new JButton("Login");
        login.setBackground(new Color(0,153,255));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setAlignmentX(Component.LEFT_ALIGNMENT);
        login.setMaximumSize(new Dimension(250, 40));
        login.addActionListener(this);

        JLabel forgot = new JLabel("Forgot Password?");
        forgot.setForeground(Color.BLUE);
        forgot.setAlignmentX(Component.LEFT_ALIGNMENT);

        left.add(title);
        left.add(Box.createVerticalStrut(10));
        left.add(subtitle);
        left.add(Box.createVerticalStrut(30));
        left.add(user);
        left.add(Box.createVerticalStrut(15));
        left.add(pass);
        left.add(Box.createVerticalStrut(25));
        left.add(login);
        left.add(Box.createVerticalStrut(15));
        left.add(forgot);

        add(left);

        // RIGHT PANEL
        JLabel right = new JLabel(new ImageIcon("bg.jpg"));
        right.setLayout(new BorderLayout());

        JLabel overlayText = new JLabel("Retail Management System", SwingConstants.CENTER);
        overlayText.setForeground(Color.WHITE);
        overlayText.setFont(new Font("Arial", Font.BOLD, 24));
        overlayText.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        right.add(overlayText, BorderLayout.SOUTH);

        add(right);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(1, user.getText());
            ps.setString(2, new String(pass.getPassword()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                new Dashboard();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}