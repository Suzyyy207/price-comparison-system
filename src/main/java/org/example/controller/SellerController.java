package org.example.controller;

import org.example.model.entity.Seller;
import org.example.util.Response;
import org.example.service.SellerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class SellerController {
    @Resource
    private SellerService sellerService;

    @GetMapping("seller/info/getById")
    public Response<Seller> getSellerInfoById(@RequestParam("id") Integer id){
        Seller seller=sellerService.getSellerById(id);
        if(seller==null){
            return new Response<>(Response.FAIL,"不存在该用户",null);
        }
        return new Response<>(Response.SUCCESS,"返回用户信息成功",seller);
    }
}
