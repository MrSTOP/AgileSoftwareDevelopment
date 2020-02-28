package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.dao.ILoginPasswordDAO;
import com.qianfeng.ykw.dao.ISystemUserDAO;
import com.qianfeng.ykw.dao.IUserInfoDAO;
import com.qianfeng.ykw.pojo.LoginPassword;
import com.qianfeng.ykw.pojo.SystemUser;
import com.qianfeng.ykw.pojo.SystemUserAllInfo;
import com.qianfeng.ykw.pojo.UserInfo;
import com.qianfeng.ykw.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Service
public class ISystemUserServiceImpl implements ISystemUserService {
    
    @Autowired
    ISystemUserDAO systemUserDAO;
    @Autowired
    IUserInfoDAO userInfoDAO;
    @Autowired
    ILoginPasswordDAO loginPasswordDAO;
    
    
    @Override
    public List<SystemUserAllInfo> selectSystemUserAllInfo() {
        return systemUserDAO.selectSystemUserAllInfo();
    }
    
    @Override
    public SystemUserAllInfo selectSystemUserAllInfoByUid(int uid) {
        return systemUserDAO.selectSystemUserAllInfoByUid(uid);
    }
    @Override
    @Transactional
    public String updateSystemUserInfo(SystemUserAllInfo systemUserAllInfo, HttpServletRequest request) {
        SystemUser judgeUserName = systemUserDAO.selectByUserName(systemUserAllInfo.getUserName());
        SystemUser nowSys = (SystemUser) request.getSession().getAttribute("systemUser");
        String msg;
        boolean repeatName = false;
        if(judgeUserName != null) {
            if (!judgeUserName.getUserName().equals(nowSys.getUserName()) ) {
                repeatName = true;
            }
        }
        if(!repeatName)
        {
            int uid = nowSys.getUid();
            systemUserAllInfo.setUid(uid);

            SystemUser systemUser = new SystemUser();
            systemUser.setUid(systemUserAllInfo.getUid());
            systemUser.setUserName(systemUserAllInfo.getUserName());
            int resSystemUser = systemUserDAO.updateSystemUser(systemUser);

            LoginPassword loginPassword = new LoginPassword();
            loginPassword.setUid(systemUserAllInfo.getUid());
            loginPassword.setLoginUserPassword(systemUserAllInfo.getLoginUserPassword());
            int resLoginPassword = loginPasswordDAO.updateLoginPassword(loginPassword);

         //   g(5).hashCode();

            UserInfo userInfo = new UserInfo();
            userInfo.setUid(systemUserAllInfo.getUid());
            userInfo.setUserInfoTrueName(systemUserAllInfo.getUserInfoTrueName());
            userInfo.setUserInfoTel(systemUserAllInfo.getUserInfoTel());
            userInfo.setUserInfoSex(systemUserAllInfo.getUserInfoSex());
            int resUserInfo = userInfoDAO.updateUserInfo(userInfo);
            if (resSystemUser > 0 && resLoginPassword > 0 && resUserInfo > 0) {
                msg = "修改成功";
                return msg;
            }else {
                msg = "修改失败";
                return msg;
            }
        }else {
            msg = "登录名重复";
            return msg;
        }

    }
    
    String g(int i){
        if (i == 0) {
            return "ff";
        }else {
            return null;
        }
    }
}
