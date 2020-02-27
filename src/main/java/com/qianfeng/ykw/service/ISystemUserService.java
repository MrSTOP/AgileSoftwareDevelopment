package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.SystemUserAllInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public interface ISystemUserService {
    
    /**
     * 查询当前系统管理员
     * @return
     */
    List<SystemUserAllInfo> selectSystemUserAllInfo();
    
    /**
     * 通过uid获取用户所有信息
     * @param uid
     * @return
     */
    SystemUserAllInfo selectSystemUserAllInfoByUid(int uid);
    
    /**
     * 修改用户信息
     * @param systemUserAllInfo
     * @param request
     * @return
     */
    String updateSystemUserInfo(SystemUserAllInfo systemUserAllInfo, HttpServletRequest request);
}
