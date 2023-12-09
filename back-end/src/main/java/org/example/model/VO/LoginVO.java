package org.example.model.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//接受前端登陆请求的参数

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private Integer id;
    private Integer type;
//    private String phone;
//    private String email;
//    private String idNumber;
//    private List<Order> buyOrders;
}
