package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goalscorer {

    private String score;
    private String time;
    private String home_scorer;
    private String away_scorer;
}
