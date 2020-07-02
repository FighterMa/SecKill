package com.nttdata.seckill.dao;

import com.nttdata.seckill.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: UserDAO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 16:09
 */
@Mapper
public interface UserDAO {
    @Select("select *from user where id=${id}")
    public User getuser(@Param("id") int id);

    @Insert("insert into user(id,name) values(${id},${name})")
    public boolean insertUser(User user);
}
