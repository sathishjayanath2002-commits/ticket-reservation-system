package com.cricket.ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        setTitle("Main Desktop Dashboard");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Welcome to Cricket Ticket Management Portal", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblWelcome, BorderLayout.CENTER);

        JButton btnBook = new JButton("Proceed to Match Booking");
        add(btnBook, BorderLayout.SOUTH);
    }
}
