package CleanStreams;

import CleanStreams.Services.GameService;
import CleanStreams.Services.LeagueService;
import CleanStreams.Tools.HttpTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Startup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private GameService gameService;
    private LeagueService leagueService;
    private HttpTools httpTools;

    public Startup(GameService gameService, LeagueService leagueService,HttpTools httpTools) {
        this.gameService = gameService;
        this.leagueService = leagueService;
        this.httpTools = httpTools;
    }


    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

//        System.out.println("startup");
//        gameService.storeCountries();
//        leagueService.storeLeagues();
//        leagueService.storeStandings();
//        gameService.storeEvents();

    }
}