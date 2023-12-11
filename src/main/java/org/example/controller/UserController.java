package org.example.controller;

import org.example.model.RE.InfoRE;
import org.example.model.RE.ProductRE;
import org.example.model.VO.*;
import org.example.model.entity.Admin;
import org.example.model.entity.Goods;
import org.example.model.entity.Seller;
import org.example.model.entity.User;
import org.example.service.AdminService;
import org.example.service.GoodsService;
import org.example.service.SellerService;
import org.example.util.Response;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private SellerService sellerService;
    @Resource
    private AdminService adminService;
    @Resource
    private GoodsService goodsService;
    @PostMapping("login")
    public Response<InfoRE> login(@RequestBody LoginVO loginVO){
        Integer id = loginVO.getUser_id();
        Integer type = loginVO.getUser_type();
        if(type==1){
            Seller seller=sellerService.getSellerById(id);
            if(seller==null){
                return new Response<>(Response.FAIL,"不存在该商家",null);
            }
            else{
                return new Response<>(Response.SUCCESS,"返回商家信息成功",new InfoRE(id, seller.getName()));
            }
        }
        else if(type==2){
            User user=userService.getUserById(id);
            if(user==null){
                return new Response<>(Response.FAIL,"不存在该用户",null);
            }
            else{
                return new Response<>(Response.SUCCESS,"返回用户信息成功",new InfoRE(id, user.getName()));
            }
        }
        else{
            Admin admin = adminService.getAdminById(id);
            if(admin==null){
                return new Response<>(Response.FAIL,"不存在该管理员",null);
            }
            else{
                return new Response<>(Response.SUCCESS,"返回管理员信息成功",new InfoRE(id, admin.getName()));
            }
        }
    }
    @PostMapping("get_user_profile")
    public Response<User> getUserInfoById(@RequestBody IdVO idVO){
        User user=userService.getUserById(idVO.getUser_id());
        if(user==null){
            return new Response<>(Response.FAIL,"不存在该用户",null);
        }
        return new Response<>(Response.SUCCESS,"返回用户信息成功",user);
    }
    @PostMapping("get_seller_profile")
    public Response<Seller> getSellerInfoById(@RequestBody IdVO idVO){
        Seller seller=sellerService.getSellerById(idVO.getUser_id());
        if(seller==null){
            return new Response<>(Response.FAIL,"不存在该商户",null);
        }
        return new Response<>(Response.SUCCESS,"返回商户信息成功",seller);
    }
    @PostMapping("get_user_products")
    public Response<List<Goods>> getProductOrCollect(@RequestBody GetProductOrCollectVO getProductOrCollectVO){
        Integer type= getProductOrCollectVO.getGet_type();
        if(type==0){
            List<Goods> allGoods= goodsService.getAllGoods();
            if(allGoods!=null){
                return new Response<>(Response.SUCCESS,"返回所有商品成功",allGoods);
            }
        }
        else if(type ==1){
            return new Response<>(Response.SUCCESS,"",null);
        }
        else{
            return new Response<>(Response.FAIL,"get_type错误",null);
        }
        return new Response<>(Response.FAIL,"返回失败",null);
    }
    @PostMapping("get_seller_products")
    public Response<List<ProductRE>> getSellerProducts(@RequestBody IdVO idVO){
        Integer sellerId= idVO.getUser_id();
        List<ProductRE> sellerGoods=goodsService.getSellerGoods(sellerId);
        return new Response<>(Response.SUCCESS,"返回该商家所有商品成功",sellerGoods);

    }
}
