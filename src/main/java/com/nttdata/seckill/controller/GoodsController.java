package com.nttdata.seckill.controller;

import com.nttdata.seckill.VO.GoodsVo;
import com.nttdata.seckill.VO.LoginVO;
import com.nttdata.seckill.common.CodeMsg;
import com.nttdata.seckill.common.Constant;
import com.nttdata.seckill.common.ResponseResult;
import com.nttdata.seckill.domain.Goods;
import com.nttdata.seckill.domain.MiaoshaUser;
import com.nttdata.seckill.service.GoodsService;
import com.nttdata.seckill.service.IMiaoshaUserService;
import com.nttdata.seckill.service.IUserService;
import com.nttdata.seckill.service.RedisService;
import com.nttdata.seckill.util.ValidateUtil;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/goods")
public class GoodsController {

    private static Logger logger =Logger.getLogger(GoodsController.class);
    @Autowired
    IUserService iUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    IMiaoshaUserService iMiaoshaUserService;

    @Autowired
    GoodsService goodsService;


    @RequestMapping("/to_list")
    public String to_list(Model model,MiaoshaUser miaoshaUser) {
        model.addAttribute("user",miaoshaUser);
        //查询商品
        List<GoodsVo> goodsVoList=goodsService.getAllGoods();
        model.addAttribute("goodsList",goodsVoList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String goodsDetail(Model model,MiaoshaUser miaoshaUser,@PathVariable("goodsId") String goodsId) {
        model.addAttribute("user",miaoshaUser);
        //根据商品id查询商品详情
        GoodsVo goods=goodsService.getGoodById(goodsId);
        model.addAttribute("goods",goods);

        long startAt=goods.getStartDate().getTime();
        long endAt=goods.getEndDate().getTime();
        long now=System.currentTimeMillis();
        int remainSeconds=0;
        int miaoshaStatus=0;
        if(now<startAt){
            miaoshaStatus=0;//秒杀未开始
            remainSeconds=(int)(startAt-now)/1000;
        }else if(now>=endAt){
            miaoshaStatus=2;//秒杀已经结束
            remainSeconds=-1;
        }else{
            miaoshaStatus=1;//秒杀正进行
            remainSeconds=0;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        return "goods_detail";
    }
}
