package loginSystem;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.Traveling; // Assuming Traveling class is in src package

// Encapsulation and Abstraction with User class
abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract void displayDashboard();
}

// Inheritance and Polymorphism with RegularUser and AdminUser classes
class RegularUser extends User {
    public RegularUser(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayDashboard() {
        System.out.println("Welcome, regular user " + getUsername());
        Traveling info = new Traveling();
        Traveling.main(null);
    }
}

class AdminUser extends User {
    public AdminUser(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayDashboard() {
        System.out.println("Welcome, admin user " + getUsername());
    }
}

public class Login {

    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 153, 102));
        frame.setBounds(200, 200, 500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("E-Ticket System");
        lblNewLabel.setBackground(Color.GREEN);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setBounds(160, 11, 173, 34);
        frame.getContentPane().add(lblNewLabel);

        JLabel lbIUsername = new JLabel("Username:");
        lbIUsername.setBackground(new Color(102, 204, 51));
        lbIUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbIUsername.setBounds(130, 89, 104, 17);
        frame.getContentPane().add(lbIUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(130, 137, 86, 14);
        frame.getContentPane().add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setBackground(Color.LIGHT_GRAY);
        txtUsername.setBounds(248, 89, 119, 20);
        frame.getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setBackground(Color.LIGHT_GRAY);
        txtPassword.setBounds(248, 136, 119, 20);
        frame.getContentPane().add(txtPassword);

        JButton btnLogin = new JButton("Log In");
        btnLogin.setBackground(new Color(0, 153, 255));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateUser(txtUsername.getText(), new String(txtPassword.getPassword()));
            }
        });
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogin.setBounds(188, 191, 89, 23);
        frame.getContentPane().add(btnLogin);

        JButton btnReset = new JButton("Reset");
        btnReset.setBackground(new Color(255, 204, 102));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText(null);
                txtPassword.setText(null);
            }
        });
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnReset.setBounds(58, 193, 89, 23);
        frame.getContentPane().add(btnReset);

        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(new Color(255, 0, 102));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frmLoginSystem = new JFrame("Exit");
                if (JOptionPane.showConfirmDialog(frmLoginSystem, "Do you want to exit?", "Login System",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExit.setBounds(315, 193, 89, 23);
        frame.getContentPane().add(btnExit);

        JSeparator separator1 = new JSeparator();
        separator1.setBackground(Color.GREEN);
        separator1.setBounds(47, 166, 373, 2);
        frame.getContentPane().add(separator1);

        JSeparator separator2 = new JSeparator();
        separator2.setBackground(Color.GREEN);
        separator2.setBounds(160, 44, 166, 14);
        frame.getContentPane().add(separator2);
    }

    private void authenticateUser(String username, String password) {
        User user;
        if ("shakib".equals(username) && "1174".equals(password)) {
            user = new RegularUser(username, password);
        } else if ("admin".equals(username) && "adminpass".equals(password)) {
            user = new AdminUser(username, password);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Password or Username!\nTry again.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        user.displayDashboard();
        txtUsername.setText(null);
        txtPassword.setText(null);
    }
}
