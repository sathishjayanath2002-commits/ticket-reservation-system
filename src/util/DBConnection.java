package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Single shared place to get a database connection.
 * Update DB_URL / USER / PASSWORD to match your local MySQL setup.
 * Everyone on the team should use DBConnection.getConnection() —
 * never open connections manually in your own module.
 */
public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cricket_ticket_db";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password_here";

    private static Connection connection;

    private DBConnection() {
        // prevent instantiation
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found. Add the connector JAR to your classpath.", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}
