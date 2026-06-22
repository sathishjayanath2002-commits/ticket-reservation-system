package model;

import java.time.LocalDateTime;

/**
 * Represents a cricket match that customers can book tickets for.
 * Demonstrates: Encapsulation
 */
public class Match {
    private int id;
    private String team1;
    private String team2;
    private String venue;
    private LocalDateTime dateTime;
    private String status; // "UPCOMING", "ONGOING", "COMPLETED", "CANCELLED"

    public Match(int id, String team1, String team2, String venue, LocalDateTime dateTime, String status) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.venue = venue;
        this.dateTime = dateTime;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTeam1() { return team1; }
    public void setTeam1(String team1) { this.team1 = team1; }

    public String getTeam2() { return team2; }
    public void setTeam2(String team2) { this.team2 = team2; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMatchTitle() {
        return team1 + " vs " + team2;
    }

    @Override
    public String toString() {
        return getMatchTitle() + " | " + venue + " | " + dateTime;
    }
}
