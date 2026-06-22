package model;

/**
 * Customer user - can browse matches, book seats, view their own bookings.
 * Demonstrates: Inheritance, Polymorphism (overrides getDashboardTitle)
 */
public class Customer extends User {

    public Customer(int id, String name, String email, String password) {
        super(id, name, email, password, "CUSTOMER");
    }

    @Override
    public String getDashboardTitle() {
        return "My Cricket Dashboard";
    }
}
