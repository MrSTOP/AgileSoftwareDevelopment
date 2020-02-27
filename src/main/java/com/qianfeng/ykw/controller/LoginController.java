package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 闫坤炜
 * @version 1.0
 */
@Controller
@RequestMapping("/LoginController")
public class LoginController {
    
    @Autowired
    ILoginService loginService;
    
    /**
     * 登录功能
     * @param userName
     * @param loginUserPassword
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(String userName, String loginUserPassword, HttpServletRequest request) {
        //接收数据
        Map<String, Object> serviceParameter = new HashMap<>();
        serviceParameter.put("userName", userName);
        serviceParameter.put("loginUserPassword", loginUserPassword);
        //处理数据，得到结果
        Map<String, Object> returnMsg = loginService.login(serviceParameter, request);
        //根据结果跳转
        if ((boolean) returnMsg.get("result")) {
            return "pages/main";
        } else {
            request.setAttribute("msg", returnMsg.get("msg"));
            return "../index";
        }
    }
}
