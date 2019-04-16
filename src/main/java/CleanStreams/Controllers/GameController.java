package CleanStreams.Controllers;


import CleanStreams.Services.ChannelService;
import CleanStreams.Services.GameService;
import CleanStreams.Services.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class GameController {

    private GameService gameService;
    private LeagueService leagueService;
    private ChannelService channelService;


    @Autowired
    public GameController(GameService gameService, LeagueService leagueService, ChannelService channelService) {
        this.gameService = gameService;
        this.leagueService = leagueService;
        this.channelService = channelService;
    }


    @RequestMapping(value = "/search/game/{search}", method = RequestMethod.GET)
    public String searchGame(Model model, HttpSession session,
                             @PathVariable("search") String search) throws Exception {

        model.addAttribute("game_list", gameService.getSearchedMatches(search));

        return "fragments::game-info-med";
    }

    @RequestMapping(value = "/games/home_games", method = RequestMethod.GET)
    public String getLeaguesPage(Model model, HttpSession session) throws Exception {

        model.addAttribute("game_list",gameService.getNextNMatches(100));
        return "fragments::game-info-med";
    }

}

