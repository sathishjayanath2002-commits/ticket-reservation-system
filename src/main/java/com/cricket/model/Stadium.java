package com.cricket.model;

public class Stadium {
    private String stadiumName;
    private String city;
    private int totalCapacity;

    public Stadium(String stadiumName, String city, int totalCapacity) {
        this.stadiumName = stadiumName;
        this.city = city;
        this.totalCapacity = totalCapacity;
    }

    public String getStadiumName() { return stadiumName; }
    public String getCity() { return city; }
    public int getTotalCapacity() { return totalCapacity; }
}