package CleanStreams.Services.Impl;

import CleanStreams.DAO.LeagueDAO;
import CleanStreams.DTO.League;
import CleanStreams.DTO.Standing;
import CleanStreams.Services.LeagueService;
import CleanStreams.Tools.HttpTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.JSONArray;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    private LeagueDAO leagueDAO;
    private HttpTools httpTools;

    @Autowired
    public LeagueServiceImpl(LeagueDAO leagueDAO, HttpTools httpTools) {
        this.leagueDAO = leagueDAO;
        this.httpTools = httpTools;
    }

    @Override
    public List<League> getAllMajorLeagues() {
        return leagueDAO.getAllMajorLeagues();
    }

    @Override
    public List<League> getAllOtherLeagues() {
        return leagueDAO.getAllOtherLeagues();
    }

    @Override
    public List<League> getAllCupLeagues() {
        return leagueDAO.getAllCupLeagues();
    }

    @Override
    public void storeLeagues() {
        try {
            String data = httpTools.httpGetURL("https://apifootball.com/api/?action=get_leagues&APIkey=aec4fdc3c841ad7b08ac13242f6a6dfc229446b4408bf4a6cb2b7ebe2baef4cf");

            JSONArray array = new JSONArray(data);

            List<League> leagueList = new ArrayList<>();
            for (int i = 0; i<array.length(); i++){
                leagueList.add(new League(Integer.parseInt(array.getJSONObject(i).getString("league_id")),array.getJSONObject(i).getString("league_name"),Integer.parseInt(array.getJSONObject(i).getString("country_id")),array.getJSONObject(i).getString("country_name")));
            }

            leagueDAO.storeLeagues(leagueList);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void storeStandings() {
        try {
            List<League> leagueList = leagueDAO.getAllLeaguesInUse();

            for (League l : leagueList){

                String data = httpTools.httpGetURL("https://apifootball.com/api/?action=get_standings&league_id="+l.getLeague_id()+"&APIkey=aec4fdc3c841ad7b08ac13242f6a6dfc229446b4408bf4a6cb2b7ebe2baef4cf");

                try {
                    JSONArray array = new JSONArray(data);
                    List<Standing> standingsList = new ArrayList<>();
                    for (int i = 0; i<array.length(); i++){
                        standingsList.add(new Standing(Integer.parseInt(array.getJSONObject(i).getString("league_id")),array.getJSONObject(i).getString("country_name"),array.getJSONObject(i).getString("league_name"),array.getJSONObject(i).getString("team_name"),array.getJSONObject(i).getString("overall_league_position"),array.getJSONObject(i).getString("overall_league_payed"),array.getJSONObject(i).getString("overall_league_W"),array.getJSONObject(i).getString("overall_league_D"),array.getJSONObject(i).getString("overall_league_L"),array.getJSONObject(i).getString("overall_league_PTS"),array.getJSONObject(i).getString("overall_league_GF"),array.getJSONObject(i).getString("overall_league_GA")));
                    }
                    leagueDAO.storeStandings(standingsList);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<League> getAdminLeagues(String league) {
        return leagueDAO.getAdminLeagues(league);
    }

    @Override
    public List<Standing> getLeagueTableByLeague(Integer league_id) {
        return leagueDAO.getLeagueTableByLeague(league_id);
    }

    @Override
    public List<Standing> getLeagueTableByMatchId(Integer match_id) {
        return leagueDAO.getLeagueTableByMatchId(match_id);
    }
}
