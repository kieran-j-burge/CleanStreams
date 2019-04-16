package CleanStreams.Services;

import CleanStreams.DTO.MatchStore;

import java.util.List;

public interface DataService {

    void storeKeyEventsInUpdate(List<MatchStore> matchList);
}
