package com.nttdata.seckill.service;

import com.nttdata.seckill.VO.GoodsVo;
import com.nttdata.seckill.common.CHANNEL;
import com.nttdata.seckill.common.ORDERSTATUS;
import com.nttdata.seckill.dao.GoodsDAO;
import com.nttdata.seckill.dao.OrderDAO;
import com.nttdata.seckill.domain.MiaoshaOrder;
import com.nttdata.seckill.domain.MiaoshaUser;
import com.nttdata.seckill.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: GoodsService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/2 22:01
 */
@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;

    /***
     * 根据用户id和商品id查看秒杀order
     */
    public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(String UserId,String GoodsId){
        return orderDAO.getMiaoshaOrderByUserIdAndGoodsId(UserId,GoodsId);
    }
    /***
     *  创建订单 创建商品订单，创建秒杀订单
     */
    public OrderInfo createMiaoshaOrder(MiaoshaUser miaoshaUser, GoodsVo goodsVo){
        //创建商品订单
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setUserId(miaoshaUser.getId());
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goodsVo.getGoodsPrice());
        orderInfo.setOrderChannel(CHANNEL.PC.getChannelCode());
        orderInfo.setStatus(ORDERSTATUS.NWE.getStatusCode());

        Long orderId=orderDAO.createGoodsOrder(orderInfo);


        //创建秒杀订单
        MiaoshaOrder miaoshaOrder=new MiaoshaOrder();
        miaoshaOrder.setUserId(miaoshaUser.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setGoodsId(goodsVo.getId());
        Long miaoshaOrderId=orderDAO.createMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
