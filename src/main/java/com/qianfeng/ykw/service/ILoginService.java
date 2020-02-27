package com.qianfeng.ykw.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户登录相关业务
 * @author 闫坤炜
 * @version 1.0
 */
public interface ILoginService {
    /**
     * 登录业务
     * @param parameter 登录所需参数
     * @param request
     * @return 登录结果
     */
    Map<String, Object> login(Map<String, Object> parameter, HttpServletRequest request);
}
