package com.nttdata.seckill.dao;

import com.nttdata.seckill.VO.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
public interface GoodsDAO {
    @Select("SELECT goods.*, miaosha_goods.miaosha_price,miaosha_goods.stock_count,miaosha_goods.start_date,miaosha_goods.end_date FROM miaosha_goods left join goods on goods.id=miaosha_goods.goods_id ")
    public List<GoodsVo> getAllGoods();

    @Select("SELECT goods.*, miaosha_goods.miaosha_price,miaosha_goods.stock_count,miaosha_goods.start_date,miaosha_goods.end_date FROM miaosha_goods left join goods on goods.id=miaosha_goods.goods_id where goods.id=${id}")
    public GoodsVo getGoodById(@Param("id") String id);
}
