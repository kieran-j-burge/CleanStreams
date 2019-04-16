package CleanStreams.Services.Impl;

import CleanStreams.DAO.GameDAO;
import CleanStreams.DAO.LeagueDAO;
import CleanStreams.DTO.*;
//import CleanStreams.DTO.Standing;
import CleanStreams.Services.DataService;
import CleanStreams.Services.GameService;
import CleanStreams.Tools.HttpTools;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import twitter4j.JSONArray;
import twitter4j.JSONException;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameDAO gameDAO;
    private HttpTools httpTools;
    private LeagueDAO leagueDAO;
    private DataService dataService;


    public GameServiceImpl(GameDAO gameDAO, HttpTools httpTools, LeagueDAO leagueDAO, DataService dataService) {
        this.gameDAO = gameDAO;
        this.httpTools = httpTools;
        this.leagueDAO = leagueDAO;
        this.dataService = dataService;
    }


    @Override
    public void storeCountries() {
        try {
            String data = httpTools.httpGetURL("https://apifootball.com/api/?action=get_countries&APIkey=aec4fdc3c841ad7b08ac13242f6a6dfc229446b4408bf4a6cb2b7ebe2baef4cf");

            JSONArray array = new JSONArray(data);

            List<Country> countryList = new ArrayList<>();
            for (int i = 0; i<array.length(); i++){
                countryList.add(new Country(Integer.parseInt(array.getJSONObject(i).getString("country_id")),array.getJSONObject(i).getString("country_name")));
            }

            gameDAO.storeCountries(countryList);

        }catch (Exception e){
            e.printStackTrace();
        }

    }




    @Override
    public void storeEvents() {
        List<League> leagueList = leagueDAO.getAllLeaguesInUse();
        for (League l : leagueList) {
            try {

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                Date todayDate = new Date();
                Date tenDaysDate = new Date(todayDate.getTime() + (1000 * 60 * 60 * 24 * 10));

                String today = dateFormat.format(todayDate).replaceAll("/", "-");
                String tenDays = dateFormat.format(tenDaysDate).replaceAll("/", "-");
//
                String data = httpTools.httpGetURL("https://apifootball.com/api/?action=get_events&from=" + today + "&to=" + tenDays + "&league_id="+l.getLeague_id()+"&APIkey=aec4fdc3c841ad7b08ac13242f6a6dfc229446b4408bf4a6cb2b7ebe2baef4cf");

                JSONArray array = new JSONArray(data);

                List<MatchStore> matchList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    Date date = new Date();
                    date = getDate(array, i);


                    List<Goalscorer> goalscorerList = new ArrayList<>();
                    JSONArray goalscorerArray = array.getJSONObject(i).getJSONArray("goalscorer");
                    for (int y = 0; y < goalscorerArray.length(); y++) {
                        goalscorerList.add(new Goalscorer(goalscorerArray.getJSONObject(y).getString("score"),
                                goalscorerArray.getJSONObject(y).getString("time").substring(0, goalscorerArray.getJSONObject(y).getString("time").length() - 1),
                                goalscorerArray.getJSONObject(y).getString("away_scorer"),
                                goalscorerArray.getJSONObject(y).getString("home_scorer")
                        ));
                    }


                    List<Player> startingHomeLineupList = new ArrayList<>();
                    List<Player> substitutesHomeList = new ArrayList<>();
                    List<Substitutions> substitutionsHomeList = new ArrayList<>();

                    List<Player> startingAwayLineupList = new ArrayList<>();
                    List<Player> substitutesAwayList = new ArrayList<>();
                    List<Substitutions> substitutionsAwayList = new ArrayList<>();

                    JSONArray startingHomeLineupArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("home").getJSONArray("starting_lineups");

                    for (int y = 0; y < startingHomeLineupArray.length(); y++) {
                        startingHomeLineupList.add(new Player(startingHomeLineupArray.getJSONObject(y).getString("lineup_player"),
                                startingHomeLineupArray.getJSONObject(y).getString("lineup_number"),
                                startingHomeLineupArray.getJSONObject(y).getString("lineup_position")));
                    }

                    JSONArray startingAwayLineupArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("away").getJSONArray("starting_lineups");

                    for (int y = 0; y < startingAwayLineupArray.length(); y++) {
                        startingAwayLineupList.add(new Player(startingAwayLineupArray.getJSONObject(y).getString("lineup_player"),
                                startingAwayLineupArray.getJSONObject(y).getString("lineup_number"),
                                startingAwayLineupArray.getJSONObject(y).getString("lineup_position")));
                    }

                    JSONArray subHomeLineupArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("home").getJSONArray("substitutes");

                    for (int y = 0; y < subHomeLineupArray.length(); y++) {
                        substitutesHomeList.add(new Player(subHomeLineupArray.getJSONObject(y).getString("lineup_player"),
                                subHomeLineupArray.getJSONObject(y).getString("lineup_number"),
                                subHomeLineupArray.getJSONObject(y).getString("lineup_position")));
                    }

                    JSONArray subAwayLineupArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("away").getJSONArray("substitutes");

                    for (int y = 0; y < subAwayLineupArray.length(); y++) {
                        substitutesAwayList.add(new Player(subAwayLineupArray.getJSONObject(y).getString("lineup_player"),
                                subAwayLineupArray.getJSONObject(y).getString("lineup_number"),
                                subAwayLineupArray.getJSONObject(y).getString("lineup_position")));
                    }

                    JSONArray subCompletedHomeArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("home").getJSONArray("substitutions");

                    for (int y = 0; y < subCompletedHomeArray.length(); y++) {
                        substitutionsHomeList.add(new Substitutions(subCompletedHomeArray.getJSONObject(y).getString("lineup_player"),
                                subCompletedHomeArray.getJSONObject(y).getString("lineup_time")));
                    }

                    JSONArray subCompletedAwayArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("home").getJSONArray("substitutions");

                    for (int y = 0; y < subCompletedAwayArray.length(); y++) {
                        substitutionsAwayList.add(new Substitutions(subCompletedAwayArray.getJSONObject(y).getString("lineup_player"),
                                subCompletedAwayArray.getJSONObject(y).getString("lineup_time")));
                    }


                    Lineup homeLineup = new Lineup(startingHomeLineupList, substitutesHomeList, substitutionsHomeList);
                    Lineup awayLineup = new Lineup(startingAwayLineupList, substitutesAwayList, substitutionsAwayList);




                    matchList.add(new MatchStore(Integer.parseInt(array.getJSONObject(i).getString("match_id")),
                            Integer.parseInt(array.getJSONObject(i).getString("country_id")),
                            Integer.parseInt(array.getJSONObject(i).getString("league_id")),
                            date,
                            array.getJSONObject(i).getString("match_status"),
                            array.getJSONObject(i).getString("match_hometeam_name"),
                            array.getJSONObject(i).getString("match_awayteam_name"),
                            array.getJSONObject(i).getString("match_hometeam_score"),
                            array.getJSONObject(i).getString("match_awayteam_score"),
                            array.getJSONObject(i).getString("match_hometeam_halftime_score"),
                            array.getJSONObject(i).getString("match_awayteam_halftime_score"),
                            array.getJSONObject(i).getString("match_hometeam_extra_score"),
                            array.getJSONObject(i).getString("match_awayteam_extra_score"),
                            array.getJSONObject(i).getString("match_hometeam_penalty_score"),
                            array.getJSONObject(i).getString("match_awayteam_penalty_score"),
                            array.getJSONObject(i).getString("match_live")
//                            goalscorerList,
//                            homeLineup,
//                            awayLineup
                    ));
                }

                gameDAO.storeMatches(matchList);

            } catch (JSONException j) {
                j.printStackTrace();
            }
        }
    }

    @Override
    public List<MatchGet> getNextNMatches(Integer n) {
        return gameDAO.getNextNMatches(n);
    }

    @Override
    public MatchGet getMatchInfo(Integer match_id) {
        return gameDAO.getMatchInfo(match_id);

    }

    @Override
    public void storeLiveEvents() {

        //Try Catch (Throws JSON Exception)
            try {
                //Get today's date and convert to string
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date todayDate = new Date();
                String today = dateFormat.format(todayDate).replaceAll("/", "-");

                //Retrieve live games json d1ata from APIFootball and convert to JSON array
                String data = httpTools.httpGetURL("https://apifootball.com/api/?action=get_events&from=" + today + "&to=" + today + "&match_live=1&APIkey=aec4fdc3c841ad7b08ac13242f6a6dfc229446b4408bf4a6cb2b7ebe2baef4cf");
                JSONArray array = new JSONArray(data);

                //For every live match
                List<MatchStore> matchList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {

                    //Get the date from live game
                    Date date;
                    date = getDate(array, i);


                    //Parse all goalscorers and store in a list
                    List<Goalscorer> goalscorerList = new ArrayList<>();
                    JSONArray goalscorerArray = array.getJSONObject(i).getJSONArray("goalscorer");
                    for (int y = 0; y < goalscorerArray.length(); y++) {
                        goalscorerList.add(new Goalscorer(goalscorerArray.getJSONObject(y).getString("score"),
                                goalscorerArray.getJSONObject(y).getString("time").substring(0, goalscorerArray.getJSONObject(y).getString("time").length() - 1),
                                goalscorerArray.getJSONObject(y).getString("away_scorer"),
                                goalscorerArray.getJSONObject(y).getString("home_scorer")
                        ));
                    }

                    //Home and Away lineup list declarations
                    List<Player> startingHomeLineupList = new ArrayList<>();
                    List<Player> substitutesHomeList = new ArrayList<>();
                    List<Substitutions> substitutionsHomeList = new ArrayList<>();

                    List<Player> startingAwayLineupList = new ArrayList<>();
                    List<Player> substitutesAwayList = new ArrayList<>();
                    List<Substitutions> substitutionsAwayList = new ArrayList<>();

                    //Parse and store home team lineup
                    JSONArray startingHomeLineupArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("home").getJSONArray("starting_lineups");
                    for (int y = 0; y < startingHomeLineupArray.length(); y++) {
                        startingHomeLineupList.add(new Player(startingHomeLineupArray.getJSONObject(y).getString("lineup_player"),
                                startingHomeLineupArray.getJSONObject(y).getString("lineup_number"),
                                startingHomeLineupArray.getJSONObject(y).getString("lineup_position")));
                    }

                    //Parse and store away team lineup
                    JSONArray startingAwayLineupArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("away").getJSONArray("starting_lineups");
                    for (int y = 0; y < startingAwayLineupArray.length(); y++) {
                        startingAwayLineupList.add(new Player(startingAwayLineupArray.getJSONObject(y).getString("lineup_player"),
                                startingAwayLineupArray.getJSONObject(y).getString("lineup_number"),
                                startingAwayLineupArray.getJSONObject(y).getString("lineup_position")));
                    }

                    //Parse and store home team subs
                    JSONArray subHomeLineupArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("home").getJSONArray("substitutes");
                    for (int y = 0; y < subHomeLineupArray.length(); y++) {
                        substitutesHomeList.add(new Player(subHomeLineupArray.getJSONObject(y).getString("lineup_player"),
                                subHomeLineupArray.getJSONObject(y).getString("lineup_number"),
                                subHomeLineupArray.getJSONObject(y).getString("lineup_position")));
                    }

                    //Parse and store away team subs
                    JSONArray subAwayLineupArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("away").getJSONArray("substitutes");
                    for (int y = 0; y < subAwayLineupArray.length(); y++) {
                        substitutesAwayList.add(new Player(subAwayLineupArray.getJSONObject(y).getString("lineup_player"),
                                subAwayLineupArray.getJSONObject(y).getString("lineup_number"),
                                subAwayLineupArray.getJSONObject(y).getString("lineup_position")));
                    }

                    //Parse and store home team completed subs
                    JSONArray subCompletedHomeArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("home").getJSONArray("substitutions");
                    for (int y = 0; y < subCompletedHomeArray.length(); y++) {
                        substitutionsHomeList.add(new Substitutions(subCompletedHomeArray.getJSONObject(y).getString("lineup_player"),
                                subCompletedHomeArray.getJSONObject(y).getString("lineup_time")));
                    }

                    //Parse and store away team completed subs
                    JSONArray subCompletedAwayArray = array.getJSONObject(i).getJSONObject("lineup").getJSONObject("home").getJSONArray("substitutions");
                    for (int y = 0; y < subCompletedAwayArray.length(); y++) {
                        substitutionsAwayList.add(new Substitutions(subCompletedAwayArray.getJSONObject(y).getString("lineup_player"),
                                subCompletedAwayArray.getJSONObject(y).getString("lineup_time")));
                    }

                    //Complete home and away team lineups
                    Lineup homeLineup = new Lineup(startingHomeLineupList, substitutesHomeList, substitutionsHomeList);
                    Lineup awayLineup = new Lineup(startingAwayLineupList, substitutesAwayList, substitutionsAwayList);

                    //Add live match data to match list
                    matchList.add(new MatchStore(Integer.parseInt(array.getJSONObject(i).getString("match_id")),
                            Integer.parseInt(array.getJSONObject(i).getString("country_id")),
                            Integer.parseInt(array.getJSONObject(i).getString("league_id")),
                            date,
                            array.getJSONObject(i).getString("match_status"),
                            array.getJSONObject(i).getString("match_hometeam_name"),
                            array.getJSONObject(i).getString("match_awayteam_name"),
                            array.getJSONObject(i).getString("match_hometeam_score"),
                            array.getJSONObject(i).getString("match_awayteam_score"),
                            array.getJSONObject(i).getString("match_hometeam_halftime_score"),
                            array.getJSONObject(i).getString("match_awayteam_halftime_score"),
                            array.getJSONObject(i).getString("match_hometeam_extra_score"),
                            array.getJSONObject(i).getString("match_awayteam_extra_score"),
                            array.getJSONObject(i).getString("match_hometeam_penalty_score"),
                            array.getJSONObject(i).getString("match_awayteam_penalty_score"),
                            array.getJSONObject(i).getString("match_live")
                    ));
                }
                //Store live match updated data
                dataService.storeKeyEventsInUpdate(matchList);
                for (MatchStore m:matchList){
                    gameDAO.storeLiveEvents(m);
                }

            } catch (JSONException j) {
                j.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
    }

    private Date getDate(JSONArray array, int i) {
        Date date;
        try {
            String dateString = array.getJSONObject(i).getString("match_date") + " " + array.getJSONObject(i).getString("match_time") + ":00";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = sdf.parse(dateString);
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    @Override
    public List<MatchGet> getSearchedMatches(String search) {
        return gameDAO.getSearchedMatches(search);
    }

    @Override
    public List<MatchGet> getAdminGames(String game) {
        return gameDAO.getAdminGames(game);
    }

    @Override
    public List<MatchGet> checkGameForStreamUpdate() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date todayDate = new Date();
        todayDate = DateUtils.addHours(todayDate,1);
        String hourBeforeString = dateFormat.format(todayDate).replaceAll("/", "-");
        List<MatchGet> matchGetList = gameDAO.checkGameForStreamUpdate(hourBeforeString);

        return matchGetList;
    }

    @Override
    public List<MatchGet> getLiveMatches() {
        return gameDAO.getLiveMatches();
    }

    @Override
    public List<MatchGet> getLeagueLiveGames(Integer league_id) {
        return gameDAO.getLeagueLiveGames(league_id);
    }

    @Override
    public List<MatchGet> getLeagueRecentGames(Integer league_id) {
        return gameDAO.getLeagueRecentGames(league_id);
    }

    @Override
    public void checkGamesStillLive() {
        List<MatchGet> matchGetList = new ArrayList<>();
        matchGetList = gameDAO.getLiveMatches();
        for (MatchGet m : matchGetList){
            String data = httpTools.httpGetURL("https://apifootball.com/api/?action=get_events&match_id="+m.getMatch_id()+"&APIkey=aec4fdc3c841ad7b08ac13242f6a6dfc229446b4408bf4a6cb2b7ebe2baef4cf");
            try {
                JSONArray array = new JSONArray(data);
                Integer matchLive=1;
                for (int i = 0; i < array.length(); i++) {
                     matchLive = Integer.parseInt(array.getJSONObject(i).getString("match_live"));
                }
                if (matchLive == 1){

                }
                else {
                    gameDAO.setMatchLiveOff(m.getMatch_id());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Lineup> getLineUps(Integer match_id) {
        return gameDAO.getLineUps(match_id);
    }

    @Override
    public List<MatchGet> getResults() {
        return gameDAO.getResults();
    }


}
