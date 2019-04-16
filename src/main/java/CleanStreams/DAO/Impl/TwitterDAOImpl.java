package CleanStreams.DAO.Impl;

import CleanStreams.DAO.TwitterDAO;
import CleanStreams.DTO.MatchGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDAOImpl implements TwitterDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TwitterDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MatchGet checkForHalfHourTweet(String halfHourDateString, String fifteenMinsPastNowDateString) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM matches WHERE date < ? AND date > ? AND tweet_status IS NULL ORDER BY date ASC LIMIT 1;",
                    new Object[]{fifteenMinsPastNowDateString,halfHourDateString},
                    (rs, rowNum) -> new MatchGet(
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
                    ));
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setTwitterStatus(Integer match_id) {
        System.out.println("Twitter status-" + match_id);
        jdbcTemplate.update("UPDATE matches SET tweet_status = 1 WHERE match_id = ?",match_id);
    }

    @Override
    public MatchGet checkForHalfHourTweetMajor(String halfHourDateString, String fifteenMinsPastNowDateString) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM matches WHERE league_id IN (SELECT league_id FROM leagues WHERE league_type = 1) AND date < ? AND date > ? AND tweet_status IS NULL ORDER BY date ASC LIMIT 1;",
                    new Object[]{fifteenMinsPastNowDateString,halfHourDateString},
                    (rs, rowNum) -> new MatchGet(
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
                    ));
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getMsgB(Integer msgBBeforeCount) {
        return jdbcTemplate.queryForObject("SELECT * FROM msg_b WHERE msg_id = ?;",
                new Object[]{msgBBeforeCount},
                (rs, rowNum) -> rs.getString("msg"));
    }

    @Override
    public String getMsgE(Integer msgECount) {
        return jdbcTemplate.queryForObject("SELECT * FROM msg_e WHERE msg_id = ?;",
                new Object[]{msgECount},
                (rs, rowNum) -> rs.getString("msg"));
    }
}
