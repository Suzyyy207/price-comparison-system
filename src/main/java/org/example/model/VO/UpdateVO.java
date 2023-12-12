package org.example.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVO {
    private int id;
    private int age;
    private int sex;
    private String phone;

    private String name;
    private String address;
}
