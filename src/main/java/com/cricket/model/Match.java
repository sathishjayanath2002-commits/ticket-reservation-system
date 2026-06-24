package com.cricket.model;

public class Match {
    private String matchId;
    private String teams; 
    private String dateTime;

    public Match(String matchId, String teams, String dateTime) {
        this.matchId = matchId;
        this.teams = teams;
        this.dateTime = dateTime;
    }

    public String getMatchId() { return matchId; }
    public String getTeams() { return teams; }
    public String getDateTime() { return dateTime; }
}