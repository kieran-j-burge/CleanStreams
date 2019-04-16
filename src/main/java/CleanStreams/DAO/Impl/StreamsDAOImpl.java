package CleanStreams.DAO.Impl;

import CleanStreams.DAO.StreamsDAO;
import CleanStreams.DTO.Channel;
import CleanStreams.DTO.MatchGet;
import CleanStreams.DTO.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StreamsDAOImpl implements StreamsDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StreamsDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Stream> getStreamsList(Integer match_id) {
        List<Stream> streamList = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM streams WHERE match_id = ?;",
                new Object[]{match_id},
                (rs, rowNum) -> streamList.add(new Stream(
                        rs.getInt("stream_id"),
                        rs.getInt("match_id"),
                        rs.getString("url"),
                        rs.getString("name"))
                ));
        return streamList;
    }

    @Override
    public void postStream(Integer match_id, String url,String title) {
        jdbcTemplate.update("INSERT INTO streams (url,score,name,match_id) VALUES (?,0,?,?);",url,title,match_id);
        jdbcTemplate.update("UPDATE matches SET streams_status = 1 WHERE match_id = ?",match_id);
    }

    @Override
    public Channel getChannelInfo(Integer channel_id) {
        return jdbcTemplate.queryForObject("SELECT * FROM channel WHERE channel_id = ?;",
                new Object[]{channel_id},
                (rs, rowNum) -> new Channel(
                        rs.getString("name"),
                        rs.getString("code")
                ));
    }
}
