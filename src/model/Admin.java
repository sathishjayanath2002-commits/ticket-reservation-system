package model;

/**
 * Admin user - can manage matches, view all bookings, generate reports.
 * Demonstrates: Inheritance, Polymorphism (overrides getDashboardTitle)
 */
public class Admin extends User {

    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password, "ADMIN");
    }

    @Override
    public String getDashboardTitle() {
        return "Admin Control Panel";
    }
}
