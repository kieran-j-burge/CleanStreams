package CleanStreams.Controllers;

import CleanStreams.DTO.MatchGet;
import CleanStreams.Services.ChannelService;
import CleanStreams.Services.GameService;
import CleanStreams.Services.LeagueService;
import CleanStreams.Services.StreamsService;
import CleanStreams.Tools.HttpTools;
import CleanStreams.Tools.TwitterLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
public class PageController {

    private GameService gameService;
    private LeagueService leagueService;
    private ChannelService channelService;
    private StreamsService streamsService;
    private HttpTools httpTools;
    private TwitterLib twitterLib;



    @Autowired
    public PageController(GameService gameService, LeagueService leagueService, ChannelService channelService, StreamsService streamsService, HttpTools httpTools,TwitterLib twitterLib) {
        this.gameService = gameService;
        this.leagueService = leagueService;
        this.channelService = channelService;
        this.streamsService = streamsService;
        this.httpTools = httpTools;
        this.twitterLib = twitterLib;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(Model model, HttpSession session) throws Exception {

        model.addAttribute("game_list",gameService.getNextNMatches(100));
        model.addAttribute("live_game_list",gameService.getLiveMatches());
        return "webpage/home";
    }

    @RequestMapping(value = "/leagues", method = RequestMethod.GET)
    public String getLeaguesPage(Model model, HttpSession session) throws Exception {

        model.addAttribute("major_league_list",leagueService.getAllMajorLeagues());
        model.addAttribute("other_league_list",leagueService.getAllOtherLeagues());
        model.addAttribute("cup_league_list",leagueService.getAllCupLeagues());


        return "webpage/leagues-page";
    }

    @RequestMapping(value = "/leagues/{league_id}", method = RequestMethod.GET)
    public String getLeaguesPage(Model model, HttpSession session,@PathVariable("league_id") Integer league_id) throws Exception {

        model.addAttribute("league_table",leagueService.getLeagueTableByLeague(league_id));
        model.addAttribute("league_live_games_list",gameService.getLeagueLiveGames(league_id));
        model.addAttribute("league_recent_games_list",gameService.getLeagueRecentGames(league_id));


        return "webpage/league-info-page";
    }


    @RequestMapping(value = "/match/{match_id}", method = RequestMethod.GET)
    public String getMatchPage(Model model, HttpSession session, @PathVariable("match_id") int match_id) throws Exception {

        model.addAttribute("match_info",gameService.getMatchInfo(match_id));
        model.addAttribute("league_table",leagueService.getLeagueTableByMatchId(match_id));
        model.addAttribute("lineup_list",gameService.getLineUps(match_id));


        return "webpage/match-info-page";
    }


    @RequestMapping(value = "/streams/{match_id}", method = RequestMethod.GET)
    public String getStreamsPage(Model model, HttpSession session, @PathVariable("match_id") Integer match_id) throws Exception {

        model.addAttribute("streams_list",streamsService.getStreamsList(match_id));

        return "webpage/match-streams-page";
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String getResultsPage(Model model, HttpSession session) throws Exception {

        model.addAttribute("results_list",gameService.getResults());

        return "webpage/results-page";
    }

    @RequestMapping(value = "/channel/{channel_id}", method = RequestMethod.GET)
    public String getLiveStreamPage(Model model, HttpSession session, @PathVariable("channel_id") String channel_id) throws Exception {

        model.addAttribute("live_stream_code", streamsService.getChannelInfo(Integer.parseInt(channel_id)));

        return "webpage/live-channel";
    }


}

