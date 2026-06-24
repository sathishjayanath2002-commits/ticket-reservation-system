package com.cricket.dao;

import com.cricket.model.Match;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO {
    // Made static so the data survives and stays shared when clicking between buttons
    private static List<Match> mockDatabase = new ArrayList<>();

    // Static initializer block to load the default match fixtures once
    static {
        mockDatabase.add(new Match("M001", "Sri Lanka vs India", "2026-10-15 14:00"));
        mockDatabase.add(new Match("M002", "Australia vs England", "2026-10-18 19:00"));
    }

    public List<Match> getAllMatches() {
        return mockDatabase;
    }

    // New method that allows adding matches dynamically via the UI
    public void addMatch(Match newMatch) {
        mockDatabase.add(newMatch);
    }
}