package CleanStreams.DAO;

import CleanStreams.DTO.Channel;
import CleanStreams.DTO.Stream;

import java.util.List;

public interface StreamsDAO {
    List<Stream> getStreamsList(Integer match_id);
    void postStream(Integer match_id, String url,String title);
    Channel getChannelInfo(Integer channel_id);
}
