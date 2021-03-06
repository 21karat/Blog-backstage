package com.karat.cn.blog_backstage.dao;

import com.karat.cn.blog_backstage.bean.User;

import java.util.List;

public interface UserDao {

    //添加用户信息
    void addUser(User user);
    //根据用户openId查看用户信息
    User selectById(String openId);
    //查看所有用户信息
    List<User> selectAll();
    //删除用户信息
    void delUser(String openId);
    //修改用户信息
    void updateUser(User user);

}
