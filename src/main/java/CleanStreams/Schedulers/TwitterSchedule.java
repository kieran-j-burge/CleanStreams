package CleanStreams.Schedulers;

import CleanStreams.DTO.MatchGet;
import CleanStreams.Tools.TwitterLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TwitterSchedule {

    private TwitterLib twitterLib;

    @Autowired
    public TwitterSchedule(TwitterLib twitterLib) {
        this.twitterLib = twitterLib;
    }

    @Scheduled(cron="0 */2 * * * *")
    public void checkForTweet() {
        try {
            twitterLib.postTweet();
        } catch (Exception e){

        }
    }

    @Scheduled(cron="0 0 17/24 ? * 1/1")
    public void postChannelTweet() {
        try {
            twitterLib.postChannelTweet();
        } catch (Exception e){

        }
    }

}
