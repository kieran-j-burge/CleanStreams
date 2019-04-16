package CleanStreams.Services.Impl;

import CleanStreams.DAO.StreamsDAO;
import CleanStreams.DTO.Channel;
import CleanStreams.DTO.Stream;
import CleanStreams.Services.StreamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Service
public class StreamsServiceImpl implements StreamsService {

    private StreamsDAO streamsDAO;

    @Autowired
    public StreamsServiceImpl(StreamsDAO streamsDAO) {
        this.streamsDAO = streamsDAO;
    }

    @Override
    public List<Stream> getStreamsList(Integer match_id) {
        return streamsDAO.getStreamsList(match_id);
    }

    @Override
    public void postStream(Integer match_id, String url,String title) {
        try {
            streamsDAO.postStream(match_id,url,title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Channel getChannelInfo(Integer channel_id) {
        return streamsDAO.getChannelInfo(channel_id);
    }
}
