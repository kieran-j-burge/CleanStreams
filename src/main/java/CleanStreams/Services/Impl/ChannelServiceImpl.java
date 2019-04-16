package CleanStreams.Services.Impl;

import CleanStreams.DAO.ChannelDAO;
import CleanStreams.DTO.Channel;
import CleanStreams.Services.ChannelService;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl implements ChannelService {

    private ChannelDAO channelDAO;

    public ChannelServiceImpl(ChannelDAO channelDAO) {
        this.channelDAO = channelDAO;
    }

    @Override
    public Channel getChannelInfo(Integer channel) {
        return channelDAO.getChannelInfo(channel);
    }
}
