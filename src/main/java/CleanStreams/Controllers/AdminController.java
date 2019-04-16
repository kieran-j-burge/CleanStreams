package CleanStreams.Controllers;

import CleanStreams.DTO.Account;
import CleanStreams.Services.GameService;
import CleanStreams.Services.LeagueService;
import CleanStreams.Services.StreamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class AdminController {

    private LeagueService leagueService;
    private GameService gameService;
    private StreamsService streamsService;

    @Autowired
    public AdminController(LeagueService leagueService, GameService gameService,StreamsService streamsService) {
        this.leagueService = leagueService;
        this.gameService = gameService;
        this.streamsService = streamsService;
    }

    @RequestMapping(value = "/admin", method = GET)
    public String logIn(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            return "webpage/admin";
        }
        else{
            return "redirect:/";

        }

    }

    @RequestMapping(value = "/admin/streams", method = GET)
    public String getStreamsPage(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("account",account);
            return "webpage/admin-streams";
        }
        else{
            return "redirect:/";

        }

    }

    @RequestMapping(value = "/search/admin/league/{league}", method = GET)
    public String getAdminLeagues(Model model, HttpSession session, @PathVariable("league") String league ){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("league_list",leagueService.getAdminLeagues(league));
            return "fragments::admin-leagues";
        }
        else{
            return "redirect:/";

        }

    }

    @RequestMapping(value = "/search/admin/game/{game}", method = GET)
    public String getAdminGames(Model model, HttpSession session, @PathVariable("game") String game ){
        Account account = (Account) session.getAttribute("account");

        if (account == null){
            return "redirect:/";
        }
        else if (account.getPermissions() == 1){
            model.addAttribute("game_list",gameService.getAdminGames(game));
            System.out.println(gameService.getAdminGames(game).size());

            return "fragments::admin-games";
        }
        else{
            return "redirect:/";

        }

    }

//    @RequestMapping(value = "/admin/stream-upload/{league_id}/{stream}", method = RequestMethod.POST)
//    public String postStream(Model model, HttpSession session, @PathVariable("league_id") String league_id, @PathVariable("stream") String stream){
//
//        stream = stream.replaceAll("£",".");
//        stream = stream.replaceAll("`","/");
//
//        stream = "https://" + stream;
//
//        Account account = (Account) session.getAttribute("account");
//        System.out.println(stream+"..."+league_id);
//        if (account == null){
//        }
//        else if (account.getPermissions() == 1){
//            streamsService.postStream(stream,Integer.parseInt(league_id));
//        }
//        else{
//
//        }
//        return "fragments::success-msg";
//    }



}
