package CleanStreams.DAO.Impl;

import CleanStreams.DAO.LeagueDAO;
import CleanStreams.DTO.League;
import CleanStreams.DTO.MatchGet;
import CleanStreams.DTO.MatchInfoShortBefore;
import CleanStreams.DTO.Standing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueDAOImpl implements LeagueDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LeagueDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<League> getAllMajorLeagues() {
        List<League> leagueList = new ArrayList<>();
        jdbcTemplate.query("SELECT l.league_id, l.name , c.country_id, c.name AS 'c_name' FROM leagues l INNER JOIN countries c ON l.country_id=c.country_id WHERE league_type = 1;",
                new Object[]{},
                (rs, rowNum) -> leagueList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getString("c_name"))
                ));
        return leagueList;
    }

    @Override
    public List<League> getAllOtherLeagues() {
        List<League> leagueList = new ArrayList<>();
        jdbcTemplate.query("SELECT l.league_id, l.name , c.country_id, c.name AS 'c_name' FROM leagues l INNER JOIN countries c ON l.country_id=c.country_id WHERE league_type = 2;",
                new Object[]{},
                (rs, rowNum) -> leagueList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getString("c_name"))
                ));
        return leagueList;
    }

    @Override
    public List<League> getAllCupLeagues() {
        List<League> leagueList = new ArrayList<>();
        jdbcTemplate.query("SELECT l.league_id, l.name , c.country_id, c.name AS 'c_name' FROM leagues l INNER JOIN countries c ON l.country_id=c.country_id WHERE league_type = 3;",
                new Object[]{},
                (rs, rowNum) -> leagueList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getString("c_name"))
                ));
        return leagueList;
    }

    @Override
    public List<League> getAllLeaguesInUse() {
        List<League> leagueList = new ArrayList<>();
        jdbcTemplate.query("SELECT l.league_id, l.name , c.country_id, c.name AS 'c_name' FROM leagues l INNER JOIN countries c ON l.country_id=c.country_id;",
                new Object[]{},
                (rs, rowNum) -> leagueList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getString("c_name"))
                ));
        return leagueList;
    }

    @Override
    public List<League> getAdminLeagues(String league) {
        List<League> leagueList = new ArrayList<>();
        league = "%"+league+"%";
        jdbcTemplate.query("SELECT l.league_id, l.name , c.country_id, c.name AS 'c_name' FROM leagues l INNER JOIN countries c ON l.country_id=c.country_id WHERE in_use = 1 AND l.name LIKE ?;",
                new Object[]{league},
                (rs, rowNum) -> leagueList.add(new League(
                        rs.getInt("league_id"),
                        rs.getString("name"),
                        rs.getInt("country_id"),
                        rs.getString("c_name"))
                ));
        return leagueList;
    }

    @Override
    public List<Standing> getLeagueTableByMatchId(Integer match_id) {
        List<Standing> standingList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM standings WHERE league_id = (SELECT league_id FROM matches WHERE match_id = ?) ORDER BY position ASC;",
                new Object[]{match_id},
                (rs, rowNum) -> standingList.add(new Standing(
                        rs.getInt("league_id"),
                        rs.getString("country_name"),
                        rs.getString("league_name"),
                        rs.getString("team_name"),
                        rs.getString("position"),
                        rs.getString("played"),
                        rs.getString("win"),
                        rs.getString("draw"),
                        rs.getString("lose"),
                        rs.getString("points"),
                        rs.getString("goals_for"),
                        rs.getString("goals_against"))
                ));
        return standingList;
    }

    @Override
    public List<Standing> getLeagueTableByLeague(Integer league_id) {
        List<Standing> standingList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM standings WHERE league_id = ? ORDER BY position ASC;",
                new Object[]{league_id},
                (rs, rowNum) -> standingList.add(new Standing(
                        rs.getInt("league_id"),
                        rs.getString("country_name"),
                        rs.getString("league_name"),
                        rs.getString("team_name"),
                        rs.getString("position"),
                        rs.getString("played"),
                        rs.getString("win"),
                        rs.getString("draw"),
                        rs.getString("lose"),
                        rs.getString("points"),
                        rs.getString("goals_for"),
                        rs.getString("goals_against"))
                ));
        return standingList;
    }


    @Override
    public void storeLeagues(List<League> leagueList) {
        for (League l : leagueList){
            jdbcTemplate.update("INSERT INTO leagues (league_id,name,country_id) VALUES (?,?,?) ON DUPLICATE KEY UPDATE league_id=league_id ;",l.getLeague_id(),l.getLeague_name() ,l.getCountry_id());
        }
    }

    @Override
    public void storeStandings(List<Standing> standingsList) {
        for (Standing s : standingsList){
            jdbcTemplate.update("INSERT INTO clubs (league_id,name) VALUES (?,?) ON DUPLICATE KEY UPDATE name = name;",s.getLeague_id(),s.getTeam_name());

            jdbcTemplate.update("INSERT INTO standings (league_id,club_id,country_name,league_name,team_name,position,played,win,draw,lose,points,goals_for,goals_against) \n" +
                    "VALUES (?,(SELECT club_id FROM clubs WHERE league_id=? AND name = ?),?,?,?,?,?,?,?,?,?,?,?) \n" +
                    "ON DUPLICATE KEY UPDATE \n" +
                    "position = ?, \n" +
                    "played = ?, \n" +
                    "win = ?,\n" +
                    "draw = ?,\n" +
                    "lose = ?,\n" +
                    "points = ?,\n" +
                    "goals_for = ?, \n" +
                    "goals_against = ?;", s.getLeague_id(),s.getLeague_id(),s.getTeam_name(),s.getCountry_name(),s.getLeague_name(),s.getTeam_name(),s.getPosition(),s.getPlayed(),s.getWin(),s.getDraw(),s.getLose(),s.getPoints(),s.getGoals_for(),s.getGoals_against(),s.getPosition(),s.getPlayed(),s.getWin(),s.getDraw(),s.getLose(),s.getPoints(),s.getGoals_for(),s.getGoals_against());
        }
    }

}
