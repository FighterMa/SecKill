package com.nttdata.seckill.dao;

import com.nttdata.seckill.VO.GoodsVo;
import com.nttdata.seckill.domain.MiaoshaOrder;
import com.nttdata.seckill.domain.MiaoshaUser;
import com.nttdata.seckill.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: GoodsDAO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/2 19:35
 */
@Mapper
public interface OrderDAO {
    @Select(" ")
    public List<GoodsVo> getAllOrders();

    @Select("SELECT *from miaosha_order where user_id=${UserId} and goods_id=${GoodsId}")
    public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(@Param("UserId") String UserId,@Param("GoodsId") String GoodsId);

    @Insert("insert into order_info(user_id,goods_id,goods_name,goods_count,goods_price,order_channel,status,create_date) values(#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},sysdate())")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    public long createGoodsOrder(OrderInfo orderInfo);

    @Insert("insert into miaosha_order(user_id,order_id,goods_id) values(${userId},${orderId},${goodsId})")
    public long createMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
