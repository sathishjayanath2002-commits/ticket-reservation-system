package com.cricket.dao;

import com.cricket.model.Match;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {
    private List<Match> mockDatabase = new ArrayList<>();

    public MatchDAO() {
        // Pre-loaded schedule simulation data
        mockDatabase.add(new Match("M001", "Sri Lanka vs India", "2026-10-15 14:00"));
        mockDatabase.add(new Match("M002", "Australia vs England", "2026-10-18 19:00"));
    }

    public List<Match> getAllMatches() {
        return mockDatabase;
    }
}