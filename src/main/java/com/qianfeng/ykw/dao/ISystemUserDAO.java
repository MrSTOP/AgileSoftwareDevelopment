package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.SystemUser;
import com.qianfeng.ykw.pojo.SystemUserAllInfo;

import java.util.List;

/**
 * 操作systemuser表
 * @author 闫坤炜
 * @version 1.0
 */
public interface ISystemUserDAO {
    
    /**
     * 按用户名查询用户UID
     * @see com.qianfeng.ykw.pojo.SystemUser
     * @param userName 用户登录名
     * @return 所有符合用户登录名SystemUser
     */
    List<SystemUser> selectByUserName(String userName);
    
    /**
     * 查询当前系统管理员
     * @return
     */
    List<SystemUserAllInfo> selectSystemUserAllInfo();
    
    /**
     * 通过Uid查询管理员数据
     * @param uid
     * @return
     */
    SystemUserAllInfo selectSystemUserAllInfoByUid(int uid);
    
    /**
     * 修改用户登录名
     * @param systemUser
     * @return
     */
    int updateSystemUser(SystemUser systemUser);
}
