package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class League {
    private Integer league_id;
    private String league_name;
    private Integer country_id;
    private String country_name;
}
