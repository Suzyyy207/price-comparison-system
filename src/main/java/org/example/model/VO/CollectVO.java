package org.example.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectVO {
    private Integer userId;
    private Integer goodsId;
    private double expectPrice;
}
