package com.liuhw.springmvc.service.impl;

import com.liuhw.springmvc.entity.User;
import com.liuhw.springmvc.mapper.UserMapper;
import com.liuhw.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuhw on 2017/6/4.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(long id) {
        return userMapper.findUserById(id);
    }

    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
