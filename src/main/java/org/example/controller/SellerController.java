package org.example.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.model.RE.ProductRE;
import org.example.model.VO.*;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin
public class SellerController {
    @Resource
    private UserService userService;
    @Resource
    private SellerService sellerService;
    @Resource
    private AdminService adminService;
    @Resource
    private GoodsService goodsService;
    @PostMapping("get_seller_profile")
    public Response<Seller> getSellerInfoById(@RequestBody IdVO idVO){
        Seller seller=sellerService.getSellerById(idVO.getUser_id());
        if(seller==null){
            return new Response<>(Response.FAIL,"不存在该商户",null);
        }
        return new Response<>(Response.SUCCESS,"返回商户信息成功",seller);
    }
    @PostMapping("get_seller_products")
    public Response<List<ProductRE>> getSellerProducts(@RequestBody IdVO idVO){
        Integer sellerId= idVO.getUser_id();
        List<ProductRE> sellerGoods=goodsService.getSellerGoods(sellerId);
        return new Response<>(Response.SUCCESS,"返回该商家所有商品成功",sellerGoods);
    }
    @PostMapping("insert_goods")
    public Response<Boolean> insertGoods(@RequestBody InsertGoodsVO insertGoodsVO){
        Boolean succeed=goodsService.insertGoods(insertGoodsVO);
        if(succeed==true){
            return new Response<>(Response.SUCCESS,"插入商品成功",true);
        }
        return new Response<>(Response.FAIL,"插入商品失败",false);
    }
    @PostMapping("get_avg_targetprice")
    public Response<Double> getAvgExpectPrice(@RequestBody IdVO idVO){
        Double avg= sellerService.getAvgExpectPrice(idVO.getUser_id());
        if(avg!=-1){
            return new Response<>(Response.SUCCESS,"收藏该商品的平均期望价格返回成功",avg);
        }
        return new Response<>(Response.FAIL,"收藏该商品的平均期望价格返回失败",-1.0);
    }
    @PostMapping("insert_goods_file")
    public Response<Boolean> insertGoodsFile(@RequestBody FilePathVO filePathVO){
        Boolean succeed=goodsService.insertGoodsFile(filePathVO.getFilepath());
        if(succeed==true){
            return new Response<>(Response.SUCCESS,"批量导入商品列表成功",true);
        }
        return new Response<>(Response.FAIL,"批量导入商品列表失败",false);
    }
}
