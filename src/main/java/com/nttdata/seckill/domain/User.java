package com.nttdata.seckill.domain;

/**
 * @version v1.0
 * @ProjectName: SecKill
 * @ClassName: User
 * @Description: TODO(一句话描述该类的功能)
 * @Author: ${战血石}
 * @Date: 2020/6/29 16:09
 */
public class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
