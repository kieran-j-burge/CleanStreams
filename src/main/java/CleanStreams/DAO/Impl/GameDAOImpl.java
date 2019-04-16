package CleanStreams.DAO.Impl;

import CleanStreams.DAO.GameDAO;
import CleanStreams.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameDAOImpl implements GameDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GameDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void storeCountries(List<Country> countryList) {
        for (Country c : countryList){
            jdbcTemplate.update("INSERT INTO countries (country_id,name) VALUES (?,?) ON DUPLICATE KEY UPDATE country_id=country_id ",c.getCountry_id(),c.getCountry_name());
        }
    }


    @Override
    public void storeStandings(List<Standing> standingList) {

    }

    @Override
    public void storeMatches(List<MatchStore> matchesList) {
        for (MatchStore m : matchesList){
            jdbcTemplate.update("INSERT INTO matches (match_id,country_id,league_id,date,status,home_team_name,away_team_name,home_team_score,away_team_score,home_team_half_score,away_team_half_score,home_team_extra_score,away_team_extra_score,home_team_pen_score,away_team_pen_score,match_live) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE match_id=match_id ",m.getMatch_id(),m.getCountry_id(),m.getLeague_id(),m.getDate(),m.getStatus(),m.getHome_team_name(),m.getAway_team_name(),m.getHome_team_score(),m.getAway_team_score(),m.getHome_team_half_score(),m.getAway_team_half_score(),m.getHome_team_extra_score(),m.getAway_team_extra_score(),m.getHome_team_pen_score(),m.getAway_team_pen_score(),m.getMatch_live());

//            for (Player p : m.getHomeLineup().getStartingLineup()){
//
//            }
//            jdbcTemplate.update("INSERT INTO home_lineup (lineup_id,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE lineup_id=lineup_id ",p.);
//
//            for (Player p : m.getAwayLineup().getStartingLineup()){
//
//            }

        }
    }

    @Override
    public List<MatchGet> getNextNMatches(Integer n) {
        List<MatchGet> matchList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM matches WHERE date IS NOT NULL  AND date >  time(now()) ORDER BY date ASC LIMIT ?;",
                new Object[]{n},
                (rs, rowNum) -> matchList.add(new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        "league_name",
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live")
                )));
        return matchList;
    }

    @Override
    public MatchGet getMatchInfo(Integer match_id) {
        return jdbcTemplate.queryForObject("SELECT * FROM matches m INNER JOIN leagues l ON m.league_id = l.league_id WHERE match_id = ?;",
                new Object[]{match_id},
                (rs, rowNum) -> new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live")
                ));
    }

    @Override
    public void storeLiveEvents(MatchStore m) {
            jdbcTemplate.update("UPDATE matches SET status = ?,home_team_score = ?,away_team_score = ?,home_team_half_score = ?,away_team_half_score = ?,home_team_extra_score = ?,away_team_extra_score = ?,home_team_pen_score = ?,away_team_pen_score = ?, match_live =? WHERE match_id = ?",m.getStatus(),m.getHome_team_score(),m.getAway_team_score(),m.getHome_team_half_score(),m.getAway_team_half_score(),m.getHome_team_extra_score(),m.getAway_team_extra_score(),m.getHome_team_pen_score(),m.getAway_team_pen_score(),m.getMatch_live(),m.getMatch_id());
    }

    @Override
    public List<MatchGet> getSearchedMatches(String search) {
        search = "%"+search+"%";
        List<MatchGet> matchList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM matches WHERE date IS NOT NULL AND date > time(now()) AND home_team_name LIKE ? OR date IS NOT NULL AND date > time(now()) AND away_team_name LIKE ? ORDER BY date ASC;",
                new Object[]{search,search},
                (rs, rowNum) -> matchList.add(new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        "league_name",
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live"))
                ));
        return matchList;
    }

    @Override
    public List<MatchGet> getAdminGames(String game) {
        game = "%"+game+"%";
        List<MatchGet> matchList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM matches WHERE date IS NOT NULL AND date > time(now()) AND home_team_name LIKE ? OR date IS NOT NULL AND date > time(now()) AND away_team_name LIKE ? ORDER BY date ASC;",
                new Object[]{game,game},
                (rs, rowNum) -> matchList.add(new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        "league",
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live")
                )));
        return matchList;
    }

    @Override
    public List<MatchGet> checkGameForStreamUpdate(String hourBeforeString) {
        List<MatchGet> matchList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM matches WHERE date > now() AND date < ? AND streams_status IS NULL ;",
                new Object[]{hourBeforeString},
                (rs, rowNum) -> matchList.add(new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        "league",
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live")
                )));
        return matchList;
    }

    @Override
    public List<MatchGet> getLiveMatches() {
        List<MatchGet> matchList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM matches WHERE match_live = 1",
                new Object[]{},
                (rs, rowNum) -> matchList.add(new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        "league",
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live")
                )));
        return matchList;
    }

    @Override
    public List<MatchGet> getLeagueLiveGames(Integer league_id) {
        List<MatchGet> matchList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM matches WHERE match_live = 1 AND league_id = ? ORDER BY date DESC;",
                new Object[]{league_id},
                (rs, rowNum) -> matchList.add(new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        "league",
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live")
                )));
        return matchList;
    }

    @Override
    public List<MatchGet> getLeagueRecentGames(Integer league_id) {
        List<MatchGet> matchList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM matches WHERE match_live != 1 AND league_id = ? AND date < now() ORDER BY date DESC;",
                new Object[]{league_id},
                (rs, rowNum) -> matchList.add(new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        "league",
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live")
                )));
        return matchList;
    }

    @Override
    public void setMatchLiveOff(Integer match_id) {
        jdbcTemplate.update("UPDATE matches SET match_live = 0 WHERE match_id = ?",match_id);
    }

    @Override
    public List<Lineup> getLineUps(Integer match_id) {
        List<Lineup> lineupList = new ArrayList<>();
        return lineupList;
    }

    @Override
    public List<MatchGet> getResults() {
        List<MatchGet> matchList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM matches WHERE match_live != 1 AND date < now() AND date(date) = date(now()) ORDER BY date DESC;",
                new Object[]{},
                (rs, rowNum) -> matchList.add(new MatchGet(
                        rs.getInt("match_id"),
                        rs.getInt("country_id"),
                        rs.getInt("league_id"),
                        "league",
                        rs.getString("date").split(" ")[0].substring(5),
                        rs.getString("date").split(" ")[1].substring(0,5),
                        rs.getString("status"),
                        rs.getString("home_team_name").replace("amp;"," "),
                        rs.getString("away_team_name").replace("amp;"," "),
                        rs.getString("home_team_score"),
                        rs.getString("away_team_score"),
                        rs.getString("home_team_half_score"),
                        rs.getString("away_team_half_score"),
                        rs.getString("home_team_extra_score"),
                        rs.getString("away_team_extra_score"),
                        rs.getString("home_team_pen_score"),
                        rs.getString("away_team_pen_score"),
                        rs.getString("match_live")
                )));
        return matchList;
    }

    @Override
    public List<Orders> getOrders() {
        List<Orders> orderList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM tbl_orders WHERE customer_id = 1;",
                new Object[]{},
                (rs, rowNum) -> orderList.add(new Orders(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("value")
                )));
        return orderList;
    }




}
