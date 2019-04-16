package CleanStreams.DAO;

import CleanStreams.DTO.MatchStore;

import java.util.List;

public interface DataDAO {
    void storeKeyEventsInUpdate(List<MatchStore> matchList);
}
