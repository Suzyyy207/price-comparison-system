package org.example.controller;

import io.swagger.models.auth.In;
import org.example.model.RE.InfoRE;
import org.example.model.VO.IdVO;
import org.example.model.VO.LoginVO;
import org.example.model.entity.Admin;
import org.example.model.entity.Seller;
import org.example.model.entity.User;
import org.example.service.AdminService;
import org.example.service.SellerService;
import org.example.util.Response;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private SellerService sellerService;
    @Resource
    private AdminService adminService;
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
}
