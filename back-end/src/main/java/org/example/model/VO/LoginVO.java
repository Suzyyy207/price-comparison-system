package org.example.model.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//接受前端登陆请求的参数

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    public Integer getId() {
        return user_id;
    }

    public void setId(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getType() {
        return user_type;
    }

    public void setType(Integer user_type) {
        this.user_type = user_type;
    }

    private Integer user_id;
    private Integer user_type;
//    private String phone;
//    private String email;
//    private String idNumber;
//    private List<Order> buyOrders;
}
