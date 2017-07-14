package com.liuhw.springmvc.mapper;


import com.liuhw.springmvc.entity.User;

/**
 * Created by liuhw on 2017/6/4.
 */
public interface UserMapper {

    User findUserById(long id);

    User findUserByName(String name);

    int addUser(User user);

    void updateUser(User user);
}
