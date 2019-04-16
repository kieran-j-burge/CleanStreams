package CleanStreams.Services;

import CleanStreams.DTO.MatchGet;

public interface TwitterService {
    MatchGet checkForHalfHourTweet();
    MatchGet checkForHalfTimeTweet();
    String getMsgB();
    String getMsgE();
    String checkForGoal();
}
