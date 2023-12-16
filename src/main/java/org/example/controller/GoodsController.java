package org.example.controller;

import org.example.model.RE.GetGoodsRE;
import org.example.model.RE.ProductRE;
import org.example.model.VO.IdVO;
import org.example.model.VO.UserGoodsVO;
import org.example.model.entity.Goods;
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

@RestController
@CrossOrigin
public class GoodsController {
    @Resource
    private UserService userService;
    @Resource
    private SellerService sellerService;
    @Resource
    private AdminService adminService;
    @Resource
    private GoodsService goodsService;
    @PostMapping("get_good")
    public Response<GetGoodsRE> getGood(@RequestBody UserGoodsVO userGoodsVO){
        GetGoodsRE goods=goodsService.getGoodsByIdAndUserId(userGoodsVO.getGoodsId(), userGoodsVO.getUserId());
        if(goods==null)return new Response<>(Response.FAIL,"获取商品信息失败",null);
        else{
            return new Response<>(Response.SUCCESS,"获取商品信息成功",goods);
        }
    }
}
