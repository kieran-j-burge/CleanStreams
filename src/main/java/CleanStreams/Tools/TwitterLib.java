package CleanStreams.Tools;

import CleanStreams.DTO.MatchGet;
import CleanStreams.Services.GameService;
import CleanStreams.Services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Date;

@Component
public class TwitterLib {

    private ConfigurationBuilder cb = new ConfigurationBuilder();
    private String consumerKey = "trQazWJobLZrrLk8dq8HOCzJW";
    private String consumerSecret = "L1yFD2PyHuN16BAkxLHsuVtBSQ21YuZLh4CJvsxf9XgKpAMNF1";
    private String accessToken = "1053355583821230080-THT3lJ1kijfBqc5PonedNsCymE8Rhc";
    private String accessTokenSecret = "VsIhE0gKNXazCy7FzGPiYjxPhQRyZyL7IqHtUoIPgJAXu";
    private Integer dailyLimit = 2400;
    private Integer halfHourLimit = 30;
    private Date lastTweet;

    private GameService gameService;
    private TwitterService twitterService;

    @Autowired
    public TwitterLib(TwitterService twitterService, GameService gameService) {
        this.twitterService = twitterService;
        this.gameService = gameService;
    }

    public void postTweet() {
            String msg_b = twitterService.getMsgB();
            String msg_e = twitterService.getMsgE();
            MatchGet match = twitterService.checkForHalfHourTweet();
            if (match == null){
                System.out.println("Null games returned overall");
                String tweet = twitterService.checkForGoal();
            }
            else {
                String tweet = msg_b + " "+ match.getHome_team_name()+" vs "+ match.getAway_team_name()+" " + msg_e;
                performTweet(tweet);
//                try {
//                    String tweet = msg_b + " "+ match.getHome_team_name()+" vs "+ match.getAway_team_name()+" " + msg_e;
//                    System.out.println(tweet);
//
//                    cb.setDebugEnabled(true)
//                            .setOAuthConsumerKey(consumerKey)
//                            .setOAuthConsumerSecret(consumerSecret)
//                            .setOAuthAccessToken(accessToken)
//                            .setOAuthAccessTokenSecret(accessTokenSecret);
//                    TwitterFactory tf = new TwitterFactory(cb.build());
//                    Twitter twitter = tf.getInstance();
//                    Status status = twitter.updateStatus(tweet);
//                    System.out.println("Successfully updated the status to [" + status.getText() + "].");
//                } catch (TwitterException e) {
//                    e.printStackTrace();
//                }
            }
    }

    public void postChannelTweet(){

    }

    private void performTweet(String tweetMsg) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        try
        {
            TwitterFactory factory = new TwitterFactory(cb.build());
            Twitter twitter = factory.getInstance();
            twitter.updateStatus(tweetMsg);
        }catch (TwitterException te) {
            te.printStackTrace();
            System.exit(-1);
        }
    }
}
