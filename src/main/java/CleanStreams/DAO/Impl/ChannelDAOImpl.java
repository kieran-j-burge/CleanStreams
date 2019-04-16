package CleanStreams.DAO.Impl;

import CleanStreams.DAO.ChannelDAO;
import CleanStreams.DTO.Channel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelDAOImpl implements ChannelDAO {

    private JdbcTemplate jdbcTemplate;

    public ChannelDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Channel getChannelInfo(Integer channel) {
        return jdbcTemplate.queryForObject("SELECT * FROM channel WHERE channel_id = ?",
                new Object[]{channel},
                ((rs, rowNum) -> new Channel(
                        rs.getString("name"),
                        rs.getString("code")
                )));
    }
}
