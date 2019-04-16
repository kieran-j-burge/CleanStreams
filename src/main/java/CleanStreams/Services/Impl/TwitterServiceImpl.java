package CleanStreams.Services.Impl;

import CleanStreams.DAO.GameDAO;
import CleanStreams.DAO.TwitterDAO;
import CleanStreams.DTO.MatchGet;
import CleanStreams.Services.TwitterService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TwitterServiceImpl implements TwitterService {

    private TwitterDAO twitterDAO;
    private GameDAO gameDAO;
    private Integer[] msgBBeforeIndex = {1,8};
    private Integer[] msgBHTIndex = {9,12};
    private Integer[] msgEIndex = {1,10};
    private Integer msgBBeforeCount = 1;
    private Integer msgBHTCount = 9;
    private Integer msgECount = 1;


    public TwitterServiceImpl(TwitterDAO twitterDAO,GameDAO gameDAO) {
        this.twitterDAO = twitterDAO;
        this.gameDAO = gameDAO;
    }

    @Override
    public MatchGet checkForHalfHourTweet() {
        String halfHourDateString;
        String fifteenMinsPastNowDateString;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date halfHourDate = new Date();
        halfHourDate = DateUtils.addMinutes(halfHourDate,-30);

        Date fifteenMinsPastNowDate = new Date();
        fifteenMinsPastNowDate = DateUtils.addMinutes(fifteenMinsPastNowDate,15);
        halfHourDateString = dateFormat.format(halfHourDate).replaceAll("/", "-");
        fifteenMinsPastNowDateString = dateFormat.format(fifteenMinsPastNowDate).replaceAll("/", "-");

//        System.out.println(fifteenMinsPastNowDateString +"-----"+halfHourDateString);

        MatchGet matchGet = twitterDAO.checkForHalfHourTweetMajor(halfHourDateString, fifteenMinsPastNowDateString);
        if (matchGet == null){
            matchGet = twitterDAO.checkForHalfHourTweet(halfHourDateString, fifteenMinsPastNowDateString);
            if (matchGet == null){
                System.out.println("No major games");
                return null;
            }
            else {
                twitterDAO.setTwitterStatus(matchGet.getMatch_id());
                return matchGet;
            }
        }
        else {
            twitterDAO.setTwitterStatus(matchGet.getMatch_id());
            return matchGet;
        }
    }

    @Override
    public MatchGet checkForHalfTimeTweet() {
        return null;
    }

    @Override
    public String getMsgB() {
        String msgB = "";
        if (msgBBeforeCount == 8){
            msgB = twitterDAO.getMsgB(msgBBeforeCount);
            msgBBeforeCount = 1;
            return msgB;
        }
        else {
            msgB = twitterDAO.getMsgB(msgBBeforeCount);
            msgBBeforeCount++;
            return msgB;
        }
    }

    @Override
    public String getMsgE() {
        String msgE = "";
        if (msgECount == 10){
            msgE = twitterDAO.getMsgE(msgECount);
            msgECount = 1;
            return msgE;
        }
        else {
            msgE = twitterDAO.getMsgE(msgECount);
            msgECount++;
            return msgE;
        }
    }

    @Override
    public String checkForGoal() {
        String tweet = null;


        return tweet;
    }
}
