package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Standing {
    private Integer league_id;
    private String country_name;
    private String league_name;
    private String team_name;
    private String position;
    private String played;
    private String win;
    private String draw;
    private String lose;
    private String points;
    private String goals_for;
    private String goals_against;
}
