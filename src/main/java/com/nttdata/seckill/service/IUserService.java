package com.nttdata.seckill.service;

import com.nttdata.seckill.domain.User;
import org.springframework.stereotype.Service;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: IUserService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 17:42
 */
@Service
public interface IUserService {
    public User getUserById(int id);
    public boolean insertUser();
}
