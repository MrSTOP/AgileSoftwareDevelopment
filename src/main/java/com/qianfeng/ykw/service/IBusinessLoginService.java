package com.qianfeng.ykw.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 商家登录注册一体化相关服务
 * @author 闫坤炜
 * @version 1.0
 */
public interface IBusinessLoginService {
    
    /**
     * 登录相关
     * @param parameter
     * @param request
     * @return
     */
    Map<String, Object> login(Map<String, Object> parameter, HttpServletRequest request);
    
    /**
     * 注册相关
     * @param parameter
     * @param request
     * @return
     */
    Map<String, Object> register(Map<String, Object> parameter, HttpServletRequest request);
}
