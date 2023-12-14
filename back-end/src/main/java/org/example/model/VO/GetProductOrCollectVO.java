package org.example.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductOrCollectVO {
    private Integer user_id;
    private Integer get_type;
}
