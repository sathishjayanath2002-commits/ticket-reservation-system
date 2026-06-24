package com.cricket.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuFrame extends JFrame {
    
    // Modern Color Palette (Premium Dark Navy Theme)
    private final Color COLOR_BG = new Color(24, 30, 54);        // Dark Midnight Navy
    private final Color COLOR_CARD = new Color(46, 51, 73);      // Lighter Slate Blue for panels
    private final Color COLOR_TEXT = new Color(255, 255, 255);   // Crisp White Text
    private final Color COLOR_MUTED = new Color(154, 161, 178);  // Soft Gray Subtext
    private final Color COLOR_ACCENT = new Color(0, 122, 255);   // Electric Royal Blue
    
    public MainMenuFrame() {
        setTitle("Cricket Ticket Management System");
        setSize(800, 500); // Expanded for a cleaner, spacious layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Root Panel setup with modern background color
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(COLOR_BG);
        rootPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Generous breathing room padding
        setContentPane(rootPanel);

        // --- 1. HEADER SECTION ---
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headerPanel.setBackground(COLOR_BG);
        
        JLabel lblTitle = new JLabel("CRICKET TICKET MANAGEMENT PORTAL", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(COLOR_TEXT);
        
        JLabel lblSubtitle = new JLabel("Logged in as Administrative Operator", SwingConstants.CENTER);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitle.setForeground(COLOR_MUTED);
        
        headerPanel.add(lblTitle);
        headerPanel.add(lblSubtitle);
        rootPanel.add(headerPanel, BorderLayout.NORTH);

        // --- 2. CENTER DASHBOARD CARD (Welcome Canvas) ---
        JPanel centerCard = new JPanel(new GridBagLayout());
        centerCard.setBackground(COLOR_CARD);
        centerCard.setBorder(BorderFactory.createLineBorder(new Color(74, 85, 104), 1, true));
        
        JLabel lblDisplay = new JLabel("Select an operation from the command deck below.");
        lblDisplay.setFont(new Font("Segoe UI Light", Font.ITALIC, 16));
        lblDisplay.setForeground(COLOR_TEXT);
        centerCard.add(lblDisplay);
        rootPanel.add(centerCard, BorderLayout.CENTER);

        // --- 3. BOTTOM COMPONENT: MODERN GRID TOOLBAR ---
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 15, 15)); // 15px gap between items
        buttonPanel.setBackground(COLOR_BG);
        buttonPanel.setBorder(new EmptyBorder(25, 0, 0, 0)); // Pad top edge from center card
        
        JButton btnBook = createModernButton("View Matches");
        JButton btnReserve = createModernButton("Book a Ticket");
        JButton btnHistory = createModernButton("View History");
        JButton btnAddMatch = createModernButton("Add Match");
        
        buttonPanel.add(btnBook);
        buttonPanel.add(btnReserve);
        buttonPanel.add(btnHistory);
        buttonPanel.add(btnAddMatch);
        rootPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Initialize backend connections
        com.cricket.dao.MatchDAO matchDAO = new com.cricket.dao.MatchDAO();
        com.cricket.service.ReservationService reservationService = new com.cricket.service.ReservationService();

        // --- ACTION LOGIC DECK ---
        
        // 📋 VIEW MATCHES
        btnBook.addActionListener((ActionEvent e) -> {
            java.util.List<com.cricket.model.Match> matches = matchDAO.getAllMatches();
            StringBuilder matchSchedule = new StringBuilder("--- Available Match Fixtures ---\n\n");
            for (com.cricket.model.Match m : matches) {
                matchSchedule.append("ID: ").append(m.getMatchId()).append("\n")
                             .append("Teams: ").append(m.getTeams()).append("\n")
                             .append("Schedule: ").append(m.getDateTime()).append("\n")
                             .append("----------------------------------------\n");
            }
            showStyledDialog(matchSchedule.toString(), "Match Schedule Repository");
        });

        // 🎫 TICKET RESERVATION
        btnReserve.addActionListener((ActionEvent e) -> {
            String matchId = showStyledInput("Enter Match ID to book (e.g., M001):");
            if (matchId == null) return;

            com.cricket.model.Match selectedMatch = null;
            for (com.cricket.model.Match m : matchDAO.getAllMatches()) {
                if (m.getMatchId().equalsIgnoreCase(matchId.trim())) {
                    selectedMatch = m;
                    break;
                }
            }

            if (selectedMatch == null) {
                showStyledError("Match ID designation not found in system storage.");
                return;
            }

            String seat = showStyledInput("Enter Seat ID (Format: A-F followed by numbers, e.g., B14):");
            if (seat == null) return;

            String priceStr = showStyledInput("Enter Ticket Fare Amount (LKR):");
            if (priceStr == null) return;

            try {
                double price = Double.parseDouble(priceStr);
                com.cricket.model.Ticket bookedTicket = reservationService.bookTicket(selectedMatch, seat.trim(), price);

                String receipt = "--- TICKET CONFIGURATION AUDIT ---\n\n" +
                                 "Receipt Ref: " + bookedTicket.getTicketId() + "\n" +
                                 "Fixture: " + bookedTicket.getMatch().getTeams() + "\n" +
                                 "Seat Track: " + bookedTicket.getSeatNumber() + "\n" +
                                 "Fare Paid: LKR " + bookedTicket.getPrice() + "\n" +
                                 "--------------------------------------------------";
                showStyledDialog(receipt, "Transaction Complete");

            } catch (NumberFormatException ex) {
                showStyledError("Invalid numeric character configuration entered for price.");
            } catch (com.cricket.util.AppException ex) {
                showStyledError(ex.getMessage());
            }
        });

        // 🔍 VIEW HISTORY
        btnHistory.addActionListener((ActionEvent e) -> {
            java.util.List<com.cricket.model.Ticket> records = reservationService.getAllBookedTickets();
            if (records.isEmpty()) {
                showStyledDialog("No transaction history records located in current session.", "Ledger Log Empty");
                return;
            }
            
            StringBuilder historyLayout = new StringBuilder("--- System Transaction Archives ---\n\n");
            for (com.cricket.model.Ticket t : records) {
                historyLayout.append("Receipt ID: ").append(t.getTicketId()).append("\n")
                             .append("Match: ").append(t.getMatch().getTeams()).append("\n")
                             .append("Seat Allocation: ").append(t.getSeatNumber()).append("\n")
                             .append("Price: LKR ").append(t.getPrice()).append("\n")
                             .append("----------------------------------------------------\n");
            }
            showStyledDialog(historyLayout.toString(), "Master Sales Ledger");
        });

        // ➕ ADD NEW MATCH
        btnAddMatch.addActionListener((ActionEvent e) -> {
            String id = showStyledInput("Assign Unique Match ID (e.g., M003):");
            if (id == null) return;

            String teams = showStyledInput("Enter Competing Teams (e.g., South Africa vs New Zealand):");
            if (teams == null) return;

            String dateTime = showStyledInput("Schedule Timestamp (YYYY-MM-DD HH:MM):");
            if (dateTime == null) return;

            com.cricket.model.Match newMatch = new com.cricket.model.Match(id, teams, dateTime);
            matchDAO.addMatch(newMatch);
            showStyledDialog("New schedule profile indexed successfully.", "Database Updated");
        });
    }

    // --- HELPER COMPONENT DESIGN ENGINES ---

    private JButton createModernButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(COLOR_TEXT);
        btn.setBackground(COLOR_CARD);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(74, 85, 104), 1, true));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover animations
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(COLOR_ACCENT);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(COLOR_CARD);
            }
        });
        return btn;
    }

    private void showStyledDialog(String content, String title) {
        UIManager.put("OptionPane.background", COLOR_CARD);
        UIManager.put("Panel.background", COLOR_CARD);
        UIManager.put("OptionPane.messageForeground", COLOR_TEXT);
        JOptionPane.showMessageDialog(this, content, title, JOptionPane.PLAIN_MESSAGE);
    }

    private String showStyledInput(String prompt) {
        UIManager.put("OptionPane.background", COLOR_CARD);
        UIManager.put("Panel.background", COLOR_CARD);
        UIManager.put("OptionPane.messageForeground", COLOR_TEXT);
        String input = JOptionPane.showInputDialog(this, prompt);
        return (input == null || input.trim().isEmpty()) ? null : input.trim();
    }

    private void showStyledError(String errMsg) {
        UIManager.put("OptionPane.background", COLOR_CARD);
        UIManager.put("Panel.background", COLOR_CARD);
        UIManager.put("OptionPane.messageForeground", new Color(255, 75, 75));
        JOptionPane.showMessageDialog(this, errMsg, "System Security Error", JOptionPane.ERROR_MESSAGE);
    }
}