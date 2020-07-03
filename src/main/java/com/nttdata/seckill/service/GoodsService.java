package com.nttdata.seckill.service;

import com.nttdata.seckill.VO.GoodsVo;
import com.nttdata.seckill.dao.GoodsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class GoodsService {
    @Autowired
    GoodsDAO goodsDAO;
    /***
     * 取得所有商品
     */
    public List<GoodsVo> getAllGoods(){
        return goodsDAO.getAllGoods();
    }

    /***
     * 根据商品id取得商品
     */
    public GoodsVo getGoodById(String id){
        return goodsDAO.getGoodById(id);
    }

    public int reduceGoodsStock(String goodsId){
        return  goodsDAO.reduceMiaoshaGoodsStock(goodsId)+goodsDAO.reduceGoodsStock(goodsId);
    }
}
