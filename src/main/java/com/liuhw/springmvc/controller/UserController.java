package com.liuhw.springmvc.controller;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.liuhw.springmvc.entity.Response;
import com.liuhw.springmvc.entity.User;
import com.liuhw.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(String name, String password) {
        User user = userService.findUserByName(name);
        if (user != null && user.getPassword().equals(password)) {
            return new Response<User> (0, user,"登录成功");
        }
        return new Response<Void> (-1,"登录失败");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Response register(String name, String password, String rePassword) {
        if (name == null || name.equals("")) {
            return new Response<Void>(-1, "用户名不能为空");
        }
        User user = userService.findUserByName(name);
        if (user != null) {
            return new Response<Void>(-1, "用户名已存在");
        }
        if (password == null || password.equals("")) {
            return new Response<Void>(-1,"密码不能为空");
        } else if (!password.equals(rePassword)){
            return new Response<Void>(-1,"两次密码不一致");
        } else {
            user = new User();
            user.setName(name);
            user.setPassword(password);
            userService.addUser(user);
            return new Response<Void>(0,"注册成功");
        }
    }

    @RequestMapping(value = "available", method = RequestMethod.GET)
    @ResponseBody
    public Response isExistUser(String name) {
        if (name == null || name.equals("")) {
            return new Response<Void>(-1, "用户名不能为空");
        }
        User user = userService.findUserByName(name);
        if (user == null) {
            return new Response<Void>(0, "用户名可用");
        } else {
            return new Response<Void>(-1, "用户名已存在");
        }
    }

    @RequestMapping(value = "modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public Response modifyPassword(long id, String oldPassword, String newPassword, String reNewPassword) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new Response<Void>(-1, "用户id不存在");
        }
        if (Strings.isNullOrEmpty(oldPassword)) {
            return new Response<Void>(-1, "当前密码不能为空");
        }
        if (Strings.isNullOrEmpty(newPassword)) {
            return new Response<Void>(-1, "新密码不能为空");
        }
        if (!Objects.equal(newPassword, reNewPassword)) {
            return new Response<Void>(-1, "两次密码不一致");
        }
        user.setPassword(newPassword);
        userService.updateUser(user);
        return new Response<Void>(0, "修改密码成功");
    }
}
