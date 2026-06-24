package com.cricket;

import com.cricket.ui.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Launches the Swing UI on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            System.out.println("Initializing Cricket Ticket Reservation System...");
            new LoginFrame().setVisible(true);
        });
    }
}