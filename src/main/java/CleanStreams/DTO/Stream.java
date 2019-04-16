package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stream {
    private Integer stream_id;
    private Integer match_id;
    private String url;
    private String name;
}
