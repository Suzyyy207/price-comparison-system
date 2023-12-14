package org.example.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGoodsVO {
    private Integer id;
    private String name;//商品名

    private String location;//商品地址
    private String category;//商品种类

    private Integer sellerId;
    private Integer platformId;
    private Date productionDate;
    private Integer tag;//用来作为实际上是同一种商品的比较
}
