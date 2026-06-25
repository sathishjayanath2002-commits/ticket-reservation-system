package dao;

import exceptions.MatchNotFoundException;
import model.Match;
import java.util.List;

/**
 * Data access contract for Match-related DB operations.
 * Member working on Match Management module implements this.
 */
public interface MatchDAO {
    List<Match> getAllUpcomingMatches();
    Match getMatchById(int id) throws MatchNotFoundException;
    boolean addMatch(Match match);
    boolean updateMatch(Match match);
    boolean deleteMatch(int id);
}
