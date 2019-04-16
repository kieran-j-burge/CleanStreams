package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchGet {

    private Integer match_id;
    private Integer country_id;
    private Integer league_id;
    private String league_name;
    private String date;
    private String time;
    private String status;
    private String home_team_name;
    private String away_team_name;
    private String home_team_score;
    private String away_team_score;
    private String home_team_half_score;
    private String away_team_half_score;
    private String home_team_extra_score;
    private String away_team_extra_score;
    private String home_team_pen_score;
    private String away_team_pen_score;
//    private Integer home_team_system;
//    private Integer away_team_system;
    private String match_live;
//    private List<Goalscorer> goalscorerList;
//    private Lineup homeLineup;
//    private Lineup awayLineup;
//    private Statistics statistics;
}
