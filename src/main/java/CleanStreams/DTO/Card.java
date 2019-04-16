package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private Integer time;
    private String home_fault;
    private String away_fault;
    private String card;
}
