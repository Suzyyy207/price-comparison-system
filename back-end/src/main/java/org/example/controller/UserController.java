package org.example.controller;

import io.swagger.models.auth.In;
import org.example.model.VO.LoginVO;
import org.example.model.entity.UserBase;
import org.example.util.Response;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("login")
    public Response<UserBase> login(@RequestBody LoginVO loginVO){
        Integer id = loginVO.getId();
        Integer type = loginVO.getType();
        System.out.println(id+type);
        UserBase user=userService.getUserById(id);
        if(user==null){
            return new Response<>(Response.FAIL,"不存在该用户",null);
        }
        else{
            if(type!=user.getType()){
                return new Response<>(Response.FAIL,"用户类型不符",null);
            }
            else{
                return new Response<>(Response.SUCCESS,"返回用户信息成功",user);
            }
        }
    }
    @PostMapping("get_user_profile")
    public Response<UserBase> getUserInfoById(@RequestParam("id") Integer id){
        UserBase user=userService.getUserById(id);
        if(user==null){
            return new Response<>(Response.FAIL,"不存在该用户",null);
        }
        return new Response<>(Response.SUCCESS,"返回用户信息成功",user);
    }
    @PostMapping("get_seller_profile")
    public Response<UserBase> getSellerInfoById(@RequestParam("id") Integer id){
        UserBase user=userService.getUserById(id);
        if(user==null){
            return new Response<>(Response.FAIL,"不存在该商户",null);
        }
        return new Response<>(Response.SUCCESS,"返回商户信息成功",user);
    }
}
