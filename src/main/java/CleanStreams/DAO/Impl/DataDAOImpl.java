package CleanStreams.DAO.Impl;

import CleanStreams.DAO.DataDAO;
import CleanStreams.DTO.MatchStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataDAOImpl implements DataDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public DataDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void storeKeyEventsInUpdate(List<MatchStore> matchList) {
    }

}
