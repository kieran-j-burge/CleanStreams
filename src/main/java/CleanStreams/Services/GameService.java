package CleanStreams.Services;

import CleanStreams.DTO.Lineup;
import CleanStreams.DTO.MatchGet;
import CleanStreams.DTO.MatchStore;
import CleanStreams.DTO.MatchInfoShortBefore;

import java.util.List;

public interface GameService {

    void storeCountries();
    void storeEvents();
    List<MatchGet> getNextNMatches(Integer n);
    MatchGet getMatchInfo(Integer match_id);
    void storeLiveEvents();
    List<MatchGet> getSearchedMatches(String search);
    List<MatchGet> getAdminGames(String game);
    List<MatchGet> checkGameForStreamUpdate();
    List<MatchGet> getLiveMatches();
    List<MatchGet> getLeagueLiveGames(Integer league_id);
    List<MatchGet> getLeagueRecentGames(Integer league_id);
    void checkGamesStillLive();
    List<Lineup> getLineUps(Integer match_id);
    List<MatchGet> getResults();
}
