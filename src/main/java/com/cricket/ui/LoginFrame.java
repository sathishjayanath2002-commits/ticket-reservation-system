package com.cricket.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Cricket Reservation System - Login");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("   Username:"));
        txtUsername = new JTextField();
        add(txtUsername);

        add(new JLabel("   Password:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        btnLogin = new JButton("Secure Login");
        add(btnLogin);

        btnLogin.addActionListener((ActionEvent e) -> {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());
            
            if (user.equals("admin") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Access Granted!");
                this.dispose(); // Close login window
                new MainMenuFrame().setVisible(true); // Open application panel
            } else {
                JOptionPane.showMessageDialog(this, "Access Denied: Invalid parameters.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
