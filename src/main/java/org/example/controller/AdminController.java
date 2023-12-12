package org.example.controller;

import org.example.model.RE.ProductRE;
import org.example.model.VO.IdVO;
import org.example.model.VO.InsertVO;
import org.example.model.entity.Seller;
import org.example.model.entity.User;
import org.example.service.AdminService;
import org.example.service.GoodsService;
import org.example.service.SellerService;
import org.example.service.UserService;
import org.example.util.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class AdminController {
    @Resource
    private UserService userService;
    @Resource
    private SellerService sellerService;
    @Resource
    private AdminService adminService;
    @Resource
    private GoodsService goodsService;
    @PostMapping("get_all_users")
    public Response<List<User>> getAllUsers(@RequestBody IdVO idVO){
        List<User> users =adminService.getAllUsers();
        if(users!=null){
            return new Response<>(Response.SUCCESS,"返回所有用户成功",users);
        }
        else{
            return new Response<>(Response.FAIL,"返回所有用户失败",null);
        }
    }
    @PostMapping("get_all_goods")
    public Response<List<ProductRE>> getAllGoods(@RequestBody IdVO idVO){
        List<ProductRE> products = goodsService.getAllGoods();
        if(products!=null){
            return new Response<>(Response.SUCCESS,"返回所有商品成功",products);
        }
        else {
            return new Response<>(Response.FAIL, "返回所有商品失败", null);
        }
    }
    @PostMapping("get_all_sellers")
    public Response<List<Seller>> getAllSellers(@RequestBody IdVO idVO){
        List<Seller> sellers =adminService.getAllSellers();
        if(sellers!=null){
            return new Response<>(Response.SUCCESS,"返回所有商家成功",sellers);
        }
        else{
            return new Response<>(Response.FAIL,"返回所有商家失败",null);
        }
    }
    @PostMapping("insert_user")
    public Response<Boolean> insertUser(@RequestBody InsertVO insertVO){
        Integer type=insertVO.getType();
        Boolean succeed=userService.insertUser(insertVO);
        if(succeed==true){
            return new Response<>(Response.SUCCESS,"插入用户成功",true);
        }
        return new Response<>(Response.FAIL,"插入用户失败",false);
    }

    @PostMapping("insert_seller")
    public Response<Boolean> insertSeller(@RequestBody InsertVO insertVO){
        Integer type=insertVO.getType();
        Boolean succeed=sellerService.insertSeller(insertVO);
        if(succeed==true){
            return new Response<>(Response.SUCCESS,"插入商家成功",true);
        }
        return new Response<>(Response.FAIL,"插入商家失败",false);
    }

}
