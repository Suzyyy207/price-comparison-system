package org.example.model.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertVO {
    private int age;
    private int sex;
    private String phone;

    private String name;
    private Integer type;
    private String address;
}
