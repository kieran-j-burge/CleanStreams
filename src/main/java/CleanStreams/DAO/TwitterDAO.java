package CleanStreams.DAO;

import CleanStreams.DTO.MatchGet;

public interface TwitterDAO {
    MatchGet checkForHalfHourTweet(String halfHourDateString, String fifteenMinsPastNowDateString);
    void setTwitterStatus(Integer match_id);
    MatchGet checkForHalfHourTweetMajor(String halfHourDateString, String fifteenMinsPastNowDateString);
    String getMsgB(Integer msgBBeforeCount);
    String getMsgE(Integer msgECount);
}
