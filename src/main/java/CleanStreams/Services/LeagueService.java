package CleanStreams.Services;

import CleanStreams.DTO.League;
import CleanStreams.DTO.MatchGet;
import CleanStreams.DTO.Standing;

import java.util.List;

public interface LeagueService {
    List<League> getAllMajorLeagues();
    List<League> getAllOtherLeagues();
    List<League> getAllCupLeagues();
    void storeLeagues();
    void storeStandings();
    List<League> getAdminLeagues(String league);
    List<Standing> getLeagueTableByLeague(Integer league_id);
    List<Standing> getLeagueTableByMatchId(Integer match_id);

}
