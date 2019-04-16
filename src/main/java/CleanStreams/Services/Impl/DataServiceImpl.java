package CleanStreams.Services.Impl;

import CleanStreams.DAO.DataDAO;
import CleanStreams.DTO.MatchGet;
import CleanStreams.DTO.MatchStore;
import CleanStreams.Services.DataService;
import CleanStreams.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DataServiceImpl implements DataService{

    @Autowired
    private DataDAO dataDAO;
    private GameService gameService;

    public DataServiceImpl(DataDAO dataDAO) {
        this.dataDAO = dataDAO;
    }


    @Override
    public void storeKeyEventsInUpdate(List<MatchStore> matchListNew) {
        List<MatchGet> oldMatchDataList = new ArrayList<>();

        for (MatchStore m : matchListNew){
            oldMatchDataList.add(gameService.getMatchInfo(m.getMatch_id()));
        }

        for (MatchStore m : matchListNew){
            System.out.println(m.getMatch_id());
        }

        for (MatchGet m : oldMatchDataList){
            System.out.println(m.getMatch_id());
        }

        Collections.sort(oldMatchDataList, Comparator.comparing(MatchGet::getMatch_id));
        Collections.sort(matchListNew, Comparator.comparing(MatchStore::getMatch_id));

        for (MatchStore m : matchListNew){
            System.out.println(m.getMatch_id());
        }

        for (MatchGet m : oldMatchDataList){
            System.out.println(m.getMatch_id());
        }

    }
}
