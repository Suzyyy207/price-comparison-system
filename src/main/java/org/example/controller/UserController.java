package org.example.controller;

import org.example.model.RE.InfoRE;
import org.example.model.RE.ProductRE;
import org.example.model.VO.*;
import org.example.model.entity.*;
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
    @PostMapping("get_user_products")
    public Response<List<ProductRE>> getProductOrCollect(@RequestBody GetProductOrCollectVO getProductOrCollectVO){
        Integer type= getProductOrCollectVO.getGet_type();
        if(type==0){
            List<ProductRE> allGoods= goodsService.getAllGoods();
            if(allGoods!=null){
                return new Response<>(Response.SUCCESS,"返回所有商品成功",allGoods);
            }
        }
        else if(type ==1){
            List<ProductRE> allCollects = userService.getGoodsByUserId(getProductOrCollectVO.getUser_id());
            if(allCollects!=null){
                return new Response<>(Response.SUCCESS,"返回用户收藏成功",allCollects);
            }
        }
        else{
            return new Response<>(Response.FAIL,"get_type错误",null);
        }
        return new Response<>(Response.FAIL,"返回失败",null);
    }
    @PostMapping("get_all_messages")
    public Response<List<Message>> getAllMessages(@RequestBody IdVO idVO){//这里返回之后把所有的信息都改为已读
        List<Message> messages=userService.getAllMessages(idVO.getUser_id());
        if(messages!=null){
            return new Response<>(Response.SUCCESS,"返回消息列表成功",messages);
        }
        else{
            return new Response<>(Response.FAIL,"返回消息列表失败",null);
        }
    }

    @PostMapping("set_collect")
    public Response<Boolean> setCollect(@RequestBody CollectVO collectVO){
        Boolean succeed = userService.setCollect(collectVO);
        if(succeed==true){
            return new Response<>(Response.SUCCESS,"收藏设置成功",true);
        }
        return new Response<>(Response.FAIL,"收藏设置失败",false);

    }

    @PostMapping("set_target_price")
    public Response<Boolean> updateCollectPrice(@RequestBody CollectVO collectVO){
        Boolean succeed = userService.updateCollectPrice(collectVO);
        if(succeed==true){
            return new Response<>(Response.SUCCESS,"修改目标价格成功",true);
        }
        return new Response<>(Response.FAIL,"修改目标价格失败",false);

    }
}
