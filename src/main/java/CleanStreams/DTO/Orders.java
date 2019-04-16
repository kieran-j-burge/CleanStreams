package CleanStreams.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    private Integer order_id;
    private Integer customer_id;
    private Integer value;
}
