package com.cricket.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        setTitle("Main Desktop Dashboard");
        setSize(700, 400); // Widened slightly to fit 4 buttons comfortably
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Welcome to Cricket Ticket Management Portal", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblWelcome, BorderLayout.CENTER);

        // Updated grid panel setup to hold 4 functional buttons side-by-side
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        
        JButton btnBook = new JButton("View Matches");
        JButton btnReserve = new JButton("Book a Ticket");
        JButton btnHistory = new JButton("View Booked Tickets");
        JButton btnAddMatch = new JButton("Add New Match");
        
        buttonPanel.add(btnBook);
        buttonPanel.add(btnReserve);
        buttonPanel.add(btnHistory);
        buttonPanel.add(btnAddMatch);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize our data and service layers
        com.cricket.dao.MatchDAO matchDAO = new com.cricket.dao.MatchDAO();
        com.cricket.service.ReservationService reservationService = new com.cricket.service.ReservationService();

        // 📋 VIEW MATCHES LOGIC
        btnBook.addActionListener((ActionEvent e) -> {
            java.util.List<com.cricket.model.Match> matches = matchDAO.getAllMatches();
            StringBuilder matchSchedule = new StringBuilder("--- Available Match Fixtures ---\n\n");
            for (com.cricket.model.Match m : matches) {
                matchSchedule.append("ID: ").append(m.getMatchId()).append("\n")
                             .append("Teams: ").append(m.getTeams()).append("\n")
                             .append("Schedule: ").append(m.getDateTime()).append("\n")
                             .append("----------------------------------------\n");
            }
            JOptionPane.showMessageDialog(this, matchSchedule.toString(), "Match Booking Center", JOptionPane.INFORMATION_MESSAGE);
        });

        // 🎫 TICKET RESERVATION LOGIC
        btnReserve.addActionListener((ActionEvent e) -> {
            String matchId = JOptionPane.showInputDialog(this, "Enter the Match ID you want to book (e.g., M001):");
            if (matchId == null || matchId.trim().isEmpty()) return;

            com.cricket.model.Match selectedMatch = null;
            for (com.cricket.model.Match m : matchDAO.getAllMatches()) {
                if (m.getMatchId().equalsIgnoreCase(matchId.trim())) {
                    selectedMatch = m;
                    break;
                }
            }

            if (selectedMatch == null) {
                JOptionPane.showMessageDialog(this, "Match ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String seat = JOptionPane.showInputDialog(this, "Enter Seat Number (Format: Letter A-F followed by numbers, e.g., A12):");
            if (seat == null || seat.trim().isEmpty()) return;

            String priceStr = JOptionPane.showInputDialog(this, "Enter Ticket Price (LKR / USD):");
            if (priceStr == null || priceStr.trim().isEmpty()) return;

            try {
                double price = Double.parseDouble(priceStr);
                com.cricket.model.Ticket bookedTicket = reservationService.bookTicket(selectedMatch, seat.trim(), price);

                String receipt = "--- TICKET PURCHASE CONFIRMED ---\n\n" +
                                 "Receipt Reference: " + bookedTicket.getTicketId() + "\n" +
                                 "Fixture: " + bookedTicket.getMatch().getTeams() + "\n" +
                                 "Seat Layout ID: " + bookedTicket.getSeatNumber() + "\n" +
                                 "Total Paid: " + bookedTicket.getPrice() + "\n" +
                                 "---------------------------------------------";
                JOptionPane.showMessageDialog(this, receipt, "Reservation Successful", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Price Format! Please enter numeric digits.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (com.cricket.util.AppException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Failed", JOptionPane.WARNING_MESSAGE);
            }
        });

        // 🔍 VIEW BOOKED TICKETS LOGIC
        btnHistory.addActionListener((ActionEvent e) -> {
            java.util.List<com.cricket.model.Ticket> records = reservationService.getAllBookedTickets();
            
            if (records.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No tickets have been reserved yet.", "Sales History Empty", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            StringBuilder historyLayout = new StringBuilder("--- Active Ticket Reservation Records ---\n\n");
            for (com.cricket.model.Ticket t : records) {
                historyLayout.append("Receipt Reference: ").append(t.getTicketId()).append("\n")
                             .append("Fixture Match: ").append(t.getMatch().getTeams()).append("\n")
                             .append("Seat: ").append(t.getSeatNumber()).append("\n")
                             .append("Cost: ").append(t.getPrice()).append("\n")
                             .append("----------------------------------------------------\n");
            }
            JOptionPane.showMessageDialog(this, historyLayout.toString(), "Reservation Database View", JOptionPane.INFORMATION_MESSAGE);
        });

        // ➕ ADD MATCH LOGIC
        btnAddMatch.addActionListener((ActionEvent e) -> {
            String id = JOptionPane.showInputDialog(this, "Enter Match ID (e.g., M003):");
            if (id == null || id.trim().isEmpty()) return;

            String teams = JOptionPane.showInputDialog(this, "Enter Teams (e.g., New Zealand vs Pakistan):");
            if (teams == null || teams.trim().isEmpty()) return;

            String dateTime = JOptionPane.showInputDialog(this, "Enter Schedule Date/Time (e.g., 2026-11-02 14:00):");
            if (dateTime == null || dateTime.trim().isEmpty()) return;

            com.cricket.model.Match newMatch = new com.cricket.model.Match(id, teams, dateTime);
            matchDAO.addMatch(newMatch);

            JOptionPane.showMessageDialog(this, "Match Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}