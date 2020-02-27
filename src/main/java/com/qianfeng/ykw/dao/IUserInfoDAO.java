package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.UserInfo;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public interface IUserInfoDAO {
    
    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);
}
