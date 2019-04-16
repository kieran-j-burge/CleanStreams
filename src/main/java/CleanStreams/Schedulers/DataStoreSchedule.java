package CleanStreams.Schedulers;

import CleanStreams.DTO.MatchGet;
import CleanStreams.Services.GameService;
import CleanStreams.Services.LeagueService;
import CleanStreams.Services.StreamsService;
import CleanStreams.Tools.HttpTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataStoreSchedule {

    private GameService gameService;
    private StreamsService streamsService;
    private LeagueService leagueService;
    private HttpTools httpTools;

    @Autowired
    public DataStoreSchedule(GameService gameService, StreamsService streamsService, HttpTools httpTools, LeagueService leagueService) {
        this.gameService = gameService;
        this.streamsService = streamsService;
        this.httpTools = httpTools;
        this.leagueService = leagueService;
    }

    @Scheduled(cron="0 0 12 1/4 * ?")
    public void updateMatches() {
        try {
            gameService.storeEvents();

        } catch (Exception e){
            
        }
    }

    @Scheduled(cron="0 */1 * * * *")
    public void updateLiveMatches() {
        try {
            System.out.println("match live update ");
            gameService.storeLiveEvents();
        } catch (Exception e){

        }
    }

    @Scheduled(cron="0 */30 * * * *")
    public void updateStandings() {
        try {
            leagueService.storeStandings();

        } catch (Exception e){

        }
    }

    @Scheduled(cron="0 */5 * * * *")
    public void updateStreamsForGame() {
        try {
            List<MatchGet> matchGetList = gameService.checkGameForStreamUpdate();
            System.out.println(matchGetList.size());
            for (MatchGet match:matchGetList){
                httpTools.updateGoogleSearchStreams(match);
            }
        } catch (Exception e){

        }
    }

    @Scheduled(cron="0 */5 * * * *")
    public void isGameStillLive() {
        try {
            gameService.checkGamesStillLive();
        } catch (Exception e){

        }
    }



}
