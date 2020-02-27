package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.pojo.User;
import com.qianfeng.ykw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    
    @RequestMapping("/addUser")
    public String addUser(User user) {
        if (userService.addUser(user)) {
            return "pages/success";
        } else {
            return "pages/error";
        }
    }
}
