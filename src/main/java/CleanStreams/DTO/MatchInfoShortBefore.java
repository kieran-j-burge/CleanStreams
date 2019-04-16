package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchInfoShortBefore {

    private Integer match_id;
    private Integer league_id;
    private Date date;
    private String status;
    private String home_team_name;
    private String away_team_name;
}
