# Cricket Match Ticket Reservation System

A Java Swing desktop application for browsing cricket matches, selecting seats, and booking tickets, built as a 5-person group project demonstrating OOP, exception handling, input validation, and team-based GitHub workflow.

## Tech Stack
- Java (Swing for UI)
- JDBC + MySQL (data persistence)
- Git / GitHub (version control & collaboration)

## Entities
| Entity | Description |
|---|---|
| `User` (abstract) → `Admin`, `Customer` | System users with role-based access |
| `Match` | A cricket match available for booking |
| `Stand` (abstract) → `GeneralStand`, `PavilionStand`, `VipStand` | Seating block within a match's venue, with tiered pricing |
| `Seat` | Individual seat within a Stand |
| `Booking` | A customer's confirmed/cancelled ticket purchase |

## OOP Concepts Demonstrated
- **Abstraction** — `User` and `Stand` are abstract base classes
- **Inheritance** — `Admin`/`Customer` extend `User`; `GeneralStand`/`PavilionStand`/`VipStand` extend `Stand`
- **Polymorphism** — `calculatePrice()` behaves differently per stand type; `getDashboardTitle()` differs per user role
- **Encapsulation** — all entity fields are private with public getters/setters

## Exception Handling
Custom checked exceptions (in `exceptions/`), all extending `CricketBookingException`:
- `InvalidLoginException`
- `InvalidInputException`
- `SeatAlreadyBookedException`
- `MatchNotFoundException`
- `InsufficientSeatsException`

## Project Structure
```
src/
 ├── model/        → entity classes
 ├── exceptions/    → custom exception classes
 ├── dao/           → data access interfaces (DB operations)
 ├── service/       → business logic layer
 ├── util/          → ValidationUtil, DBConnection
 └── ui/
      ├── auth/       → Login / Register screens
      ├── match/      → Match listing, admin match management
      ├── seating/    → Seat selection grid
      ├── booking/    → Booking confirmation, bill
      └── dashboard/  → My Bookings, Admin reports
```

## Module Ownership

| Member | Module | Folder |
|---|---|---|
| Member A | Authentication | `ui/auth`, `dao/UserDAO` impl |
| Member B | Match Management | `ui/match`, `dao/MatchDAO` impl |
| Member C | Seat Selection | `ui/seating`, `dao/SeatDAO` impl |
| Member D | Booking & Billing | `ui/booking`, `dao/BookingDAO` impl |
| Member E | Dashboard & Integration | `ui/dashboard`, final merge/testing |

## Getting Started
1. Clone the repo and create your own feature branch: `git checkout -b feature/your-module`
2. Set up a local MySQL database and update credentials in `util/DBConnection.java`
3. Run `ui/auth/LoginFrame.java` to launch the app (currently uses placeholder login: `admin@cricket.com` / `admin123`)
4. Implement your module's DAO + UI, commit small and often, open a Pull Request into `main`

## Database Setup (to be finalized as a team)
Suggested tables: `users`, `matches`, `stands`, `seats`, `bookings`. Write the SQL schema together as your first team task before splitting into modules.
