package CleanStreams.DAO;

import CleanStreams.DTO.League;
import CleanStreams.DTO.MatchGet;
import CleanStreams.DTO.Standing;

import java.util.List;

public interface LeagueDAO {

    List<League> getAllMajorLeagues();

    List<League> getAllOtherLeagues();

    List<League> getAllCupLeagues();

    void storeLeagues(List<League> leagueList);

    void storeStandings(List<Standing> standingsList);

    List<League> getAllLeaguesInUse();

    List<League> getAdminLeagues(String league);

    List<Standing> getLeagueTableByMatchId(Integer match_id);

    List<Standing> getLeagueTableByLeague(Integer league_id);

}