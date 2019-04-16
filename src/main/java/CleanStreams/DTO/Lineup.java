package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lineup {

    private List<Player> startingLineup;
    private List<Player> substitutes;
    private List<Substitutions> substitutions;

}