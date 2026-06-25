package ui.auth;

import exceptions.InvalidInputException;
import exceptions.InvalidLoginException;
import util.ValidationUtil;

import javax.swing.*;
import java.awt.*;

/**
 * Mandatory Login screen for the Cricket Ticket Reservation System.
 *
 * NOTE: This currently uses a hardcoded check as a placeholder so the
 * screen is runnable immediately. Replace authenticate() with a real
 * call to UserDAO.login(...) once the Authentication module is wired
 * up to the database.
 */
public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LoginFrame() {
        setTitle("Cricket Ticket Reservation - Login");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Cricket Match Ticket Reservation");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(18);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(18);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        JButton registerButton = new JButton("Don't have an account? Register");
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setForeground(Color.BLUE);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(statusLabel, gbc);

        add(panel);

        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e ->
                statusLabel.setText("Registration screen not wired up yet."));
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        try {
            ValidationUtil.validateEmail(email);
            ValidationUtil.validateNotEmpty(password, "Password");

            authenticate(email, password);

            statusLabel.setForeground(new Color(0, 128, 0));
            statusLabel.setText("Login successful! Welcome.");
            // TODO: open MatchListFrame / AdminDashboardFrame depending on role

        } catch (InvalidInputException ex) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText(ex.getMessage());
        } catch (InvalidLoginException ex) {
            statusLabel.setForeground(Color.RED);
            statusLabel.setText(ex.getMessage());
        }
    }

    /**
     * Placeholder authentication logic.
     * Replace with: userDAO.login(email, password)
     */
    private void authenticate(String email, String password) throws InvalidLoginException {
        boolean isPlaceholderValid = email.equalsIgnoreCase("admin@cricket.com") && password.equals("admin123");
        if (!isPlaceholderValid) {
            throw new InvalidLoginException("Invalid email or password.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
