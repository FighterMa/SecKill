package com.nttdata.seckill.service.impl;

import com.nttdata.seckill.dao.UserDAO;
import com.nttdata.seckill.domain.User;
import com.nttdata.seckill.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: UserService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 16:55
 */
@Service
public class UserService implements IUserService {
    @Autowired
    UserDAO userDAO;
    @Override
    public User getUserById(int id){
        return userDAO.getuser(id);
    }

    @Override
    @Transactional
    public boolean insertUser() {
        User user1=new User(5,"eason");
        userDAO.insertUser(user1);
        User user2=new User(1,"boby");
        userDAO.insertUser(user2);
        return true;
    }

}
