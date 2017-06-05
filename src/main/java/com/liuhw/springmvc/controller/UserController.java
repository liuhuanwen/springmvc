package com.liuhw.springmvc.controller;

import com.liuhw.springmvc.entity.Response;
import com.liuhw.springmvc.entity.User;
import com.liuhw.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuhw on 2017/6/4.
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Response login(String name, String password) {
        User user = userService.findUserByName(name);
        if (user != null && user.getPassword().equals(password)) {
            return new Response<Void> (0,"登录成功");
        }
        return new Response<Void> (-1,"登录失败");
    }

    @RequestMapping("/register")
    @ResponseBody
    public Response register(String name, String password, String rePassword) {
        if (password == null || password.equals("")) {
            return new Response<Void>(-1,"密码不能为空");
        } else if (!password.equals(rePassword)){
            return new Response<Void>(-1,"两次密码不一致");
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            userService.addUser(user);
            return new Response<Void>(0,"注册成功");
        }
    }
}
