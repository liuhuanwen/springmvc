package com.liuhw.springmvc.service;

import com.liuhw.springmvc.entity.User;
import org.springframework.stereotype.Service;

/**
 * Created by liuhw on 2017/6/4.
 */
@Service
public interface UserService {

    User findUserByName(String name);

    int addUser(User user);
}
