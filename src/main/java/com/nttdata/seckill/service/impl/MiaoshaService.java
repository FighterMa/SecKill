package com.nttdata.seckill.service.impl;

import com.nttdata.seckill.VO.GoodsVo;
import com.nttdata.seckill.domain.MiaoshaUser;
import com.nttdata.seckill.domain.OrderInfo;
import com.nttdata.seckill.service.GoodsService;
import com.nttdata.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: MiaoshaService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/3 20:37
 */
@Service
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    GoodsVo goodsVo;
    @Transactional
    public  OrderInfo Miaosha(MiaoshaUser miaoshaUser, String goodsId){
        goodsVo=goodsService.getGoodById(goodsId);
        //减商品库存 减秒杀商品库存
        int reduceCount=ReduceMiaoshaStock(goodsId);
        //下订单 写入秒杀订单
        OrderInfo orderinfo=CreateMiaoshaOrder(miaoshaUser,goodsVo);
        return orderinfo;
    }

    /**
     * 减少秒杀库存，同时也要减少商品库存
     */
    private int ReduceMiaoshaStock(String goodsId){
        return goodsService.reduceGoodsStock(goodsId);
    }

    /**
     * 创建订单，创建商品订单，创建秒杀订单
     */
    private OrderInfo CreateMiaoshaOrder(MiaoshaUser miaoshaUser,GoodsVo goodsVo){
        return orderService.createMiaoshaOrder(miaoshaUser,goodsVo);
    }
}
