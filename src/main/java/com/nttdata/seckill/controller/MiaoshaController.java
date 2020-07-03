package com.nttdata.seckill.controller;

import com.nttdata.seckill.VO.GoodsVo;
import com.nttdata.seckill.common.CodeMsg;
import com.nttdata.seckill.domain.MiaoshaOrder;
import com.nttdata.seckill.domain.MiaoshaUser;
import com.nttdata.seckill.domain.OrderInfo;
import com.nttdata.seckill.service.*;
import com.nttdata.seckill.service.impl.MiaoshaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: HelloController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 9:31
 */
@Controller()
@RequestMapping("/miaosha")
public class MiaoshaController {

    private static Logger logger =Logger.getLogger(MiaoshaController.class);
    @Autowired
    IUserService iUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    IMiaoshaUserService iMiaoshaUserService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String do_miaosha(Model model, MiaoshaUser miaoshaUser, @RequestParam("goodsId")String goodsId) {
        if(miaoshaUser==null){
            return "login";
        }
        model.addAttribute("user",miaoshaUser);

        //判断库存
        GoodsVo good=goodsService.getGoodById(goodsId);
        int miaoshaStock=good.getStockCount();
        if(miaoshaStock<=0){
            model.addAttribute("errMsg", CodeMsg.MIAOSHA_STOCKERROR);
            return "miaosha_fail";
        }
        //判断是否已经有订单
        MiaoshaOrder miaoshaOrder= orderService.getMiaoshaOrderByUserIdAndGoodsId(miaoshaUser.getId().toString(),goodsId);
        if(miaoshaOrder!=null){
            model.addAttribute("errMsg", CodeMsg.MIAOSHA_REPEAT);
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo=miaoshaService.Miaosha(miaoshaUser,goodsId);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", good);
        return "order_detail";
    }

}
