package org.example.controller;

import org.example.model.RE.GetGoodsRE;
import org.example.model.RE.ProductRE;
import org.example.model.VO.IdVO;
import org.example.model.VO.UpdateGoodsVO;
import org.example.model.VO.UserGoodsVO;
import org.example.model.entity.Goods;
import org.example.model.entity.History;
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

    // /get_history_price接口
    @PostMapping("get_history_price")
    public Response<List<History>> getGood(@RequestBody IdVO idVO) {
        List<History> histories = goodsService.getHistoryPrice(idVO.getUser_id());
        if (histories==null)
            return new Response<>(Response.FAIL,"没有历史记录",null);
        else{
            return new Response<>(Response.SUCCESS,"获取历史记录成功",histories);
        }
    }
    @PostMapping("update_goods")
    public Response<Boolean> updateGoods(@RequestBody UpdateGoodsVO updateGoodsVO){
        Boolean succeed = goodsService.updateGoods(updateGoodsVO);
        if(succeed==true){
            return new Response<>(Response.SUCCESS,"修改商品成功",true);
        }
        return new Response<>(Response.FAIL,"修改商品失败",false);
    }
}
