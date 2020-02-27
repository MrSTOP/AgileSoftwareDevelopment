package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.service.IBusinessLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 闫坤炜
 * @version 1.0
 */
@RestController
@RequestMapping("/BusinessLoginController")
public class BusinessLoginController {
    
    @Autowired
    IBusinessLoginService businessLoginService;
    
    
    /**
     * 关于商户登录
     * @return
     */
    @RequestMapping("/login")
    public Map<String, Object> login(String businessUsername, String businessPassword, HttpServletRequest request) {
        //接收数据
        Map<String, Object> loginParam = new HashMap<>();
        loginParam.put("businessUsername", businessUsername);
        loginParam.put("businessPassword", businessPassword);
        //Service处理数据
        //返回结果
        return businessLoginService.login(loginParam, request);
    }
    
    /**
     * 关于商户注册
     * @return
     */
    @RequestMapping("/register")
    public Map<String, Object> register(String businessUsername, String businessPassword, HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        //接收数据
        Map<String, Object> registerParam = new HashMap<>();
        registerParam.put("businessUsername", businessUsername);
        registerParam.put("businessPassword", businessPassword);
        //Service处理数据
        returnMap = businessLoginService.register(registerParam, request);
        //返回结果
        return returnMap;
    }
    
}
