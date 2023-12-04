package org.example.controller;

import org.example.model.entity.User;
import org.example.util.Response;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("user/info/getById")
    public Response<User> getUserInfoById(@RequestParam("id") Integer id){
        User user=userService.getUserById(id);
        if(user==null){
            return new Response<>(Response.FAIL,"不存在该用户",null);
        }
        return new Response<>(Response.SUCCESS,"返回用户信息成功",user);
    }
}
