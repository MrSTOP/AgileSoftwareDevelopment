package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.LoginPassword;

import java.util.Map;

/**
 * 操作loginpassword表
 * @author 闫坤炜
 * @version 1.0
 */
public interface ILoginPasswordDAO {
    
    /**
     * 通过uid获取用户密码
     * @param parameter
     * @return
     */
    LoginPassword selectByUID(Map<String, Object> parameter);
    
    /**
     * 修改用户登录密码
     * @param loginPassword
     * @return
     */
    int updateLoginPassword(LoginPassword loginPassword);
}
