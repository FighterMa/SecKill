package com.nttdata.seckill.dao;

import com.nttdata.seckill.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: MiaoshaUserDAO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/7/1 14:17
 */
@Mapper
public interface MiaoshaUserDAO {
    @Select("select *from miaosha_user where id= ${id}")
    public MiaoshaUser getMiaoshaUserById(@Param("id") String id);
 }
