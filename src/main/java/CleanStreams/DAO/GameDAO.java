package CleanStreams.DAO;

import CleanStreams.DTO.*;

import java.util.List;

public interface GameDAO {

    void storeCountries(List<Country> countryList);
    void storeStandings(List<Standing> standingList);
    void storeMatches(List<MatchStore> matchesList);
    List<MatchGet> getNextNMatches(Integer n);
    MatchGet getMatchInfo(Integer match_id);
    void storeLiveEvents(MatchStore m);
    List<MatchGet> getSearchedMatches(String search);
    List<MatchGet> getAdminGames(String game);
    List<MatchGet> checkGameForStreamUpdate(String hourBeforeString);
    List<MatchGet> getLiveMatches();
    List<MatchGet> getLeagueLiveGames(Integer league_id);
    List<MatchGet> getLeagueRecentGames(Integer league_id);
    void setMatchLiveOff(Integer match_id);
    List<Lineup> getLineUps(Integer match_id);
    List<MatchGet> getResults();
    List<Orders> getOrders();
}
