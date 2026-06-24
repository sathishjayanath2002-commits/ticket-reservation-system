package com.cricket.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
    // Cohesive Modern Color Palette
    private final Color COLOR_BG = new Color(24, 30, 54);        // Midnight Navy
    private final Color COLOR_CARD = new Color(46, 51, 73);      // Slate Blue
    private final Color COLOR_TEXT = new Color(255, 255, 255);   // Crisp White
    private final Color COLOR_MUTED = new Color(154, 161, 178);  // Soft Gray
    private final Color COLOR_ACCENT = new Color(0, 122, 255);   // Electric Blue

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Cricket Reservation System - Secure Gateway");
        setSize(450, 380); // Slightly larger for comfortable, spacious layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Container config with spacious layout padding
        JPanel rootPanel = new JPanel();
        rootPanel.setBackground(COLOR_BG);
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.setBorder(new EmptyBorder(35, 40, 35, 40));
        setContentPane(rootPanel);

        // --- 1. BRANDING HEADER SECTION ---
        JLabel lblLogoText = new JLabel("C M R S");
        lblLogoText.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblLogoText.setForeground(COLOR_ACCENT);
        lblLogoText.setAlignmentX(Component.CENTER_ALIGNMENT);
        rootPanel.add(lblLogoText);

        JLabel lblSubText = new JLabel("Enter operational credentials to access backend");
        lblSubText.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubText.setForeground(COLOR_MUTED);
        lblSubText.setAlignmentX(Component.CENTER_ALIGNMENT);
        rootPanel.add(lblSubText);
        
        rootPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Breathing room spacer

        // --- 2. CREDENTIALS INPUT WRAPPERS ---
        // Username Input field setup
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(COLOR_TEXT);
        lblUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        rootPanel.add(lblUser);
        rootPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtUsername = new JTextField();
        styleInputField(txtUsername);
        rootPanel.add(txtUsername);
        
        rootPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Field spacer

        // Password Input field setup
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setForeground(COLOR_TEXT);
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        rootPanel.add(lblPass);
        rootPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtPassword = new JPasswordField();
        styleInputField(txtPassword);
        rootPanel.add(txtPassword);

        rootPanel.add(Box.createRigidArea(new Dimension(0, 35))); // Button padding spacer

        // --- 3. MODERNIZED ACTION TRIGGER ---
        btnLogin = new JButton("Secure Login");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setForeground(COLOR_TEXT);
        btnLogin.setBackground(COLOR_ACCENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45)); // Stretches button width smoothly
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder());
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Interactive hover color shifting
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(COLOR_ACCENT.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(COLOR_ACCENT);
            }
        });
        rootPanel.add(btnLogin);

        // --- ACTION CONTROLLER LAYER ---
        btnLogin.addActionListener((ActionEvent e) -> {
            String user = txtUsername.getText();
            String pass = new String(txtPassword.getPassword());
            
            if (user.equals("admin") && pass.equals("1234")) {
                this.dispose(); // Gracefully close active authentication panel
                new MainMenuFrame().setVisible(true); // Call dashboard interface
            } else {
                showStyledError("Access Denied: Authentication configuration parameter mismatch.");
            }
        });
    }

    // Modern flat styling helper for input fields
    private void styleInputField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(COLOR_TEXT);
        field.setBackground(COLOR_CARD);
        field.setCaretColor(COLOR_TEXT); // Makes the blinking cursor white
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        // Adds structural 5px internal text boundaries so typing looks professional
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(74, 85, 104), 1, true),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private void showStyledError(String errMsg) {
        UIManager.put("OptionPane.background", COLOR_CARD);
        UIManager.put("Panel.background", COLOR_CARD);
        UIManager.put("OptionPane.messageForeground", new Color(255, 75, 75));
        JOptionPane.showMessageDialog(this, errMsg, "Security Alert", JOptionPane.ERROR_MESSAGE);
    }
}