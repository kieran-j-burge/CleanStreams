package CleanStreams.Tools;

import CleanStreams.DTO.MatchGet;
import CleanStreams.Services.StreamsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpTools {

    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    private StreamsService streamsService;

    @Autowired
    public HttpTools(StreamsService streamsService) {
        this.streamsService = streamsService;
    }


    public String httpGetURL(String url_string){
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(url_string);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public void updateGoogleSearchStreams(MatchGet game){

        String search = game.getHome_team_name()+ " vs " + game.getAway_team_name() + " watch free online stream";
        search = search.replaceAll(" ","+");
        //Fetch the page
        final Document doc;

        try {
            doc = Jsoup.connect("https://google.com/search?q="+search).userAgent(USER_AGENT).get();
//            System.out.println(doc);
            //Traverse the results
//            "h3.r a"
            for (Element result : doc.select(".g .r")){

                final String title = result.select("h3").text();
                System.out.println(title);

                final String url = result.select("a").attr("href");
                System.out.println(url);
                streamsService.postStream(game.getMatch_id(),url,title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
