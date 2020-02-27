package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.UserRoleType;
import com.qianfeng.ykw.dao.ILoginPasswordDAO;
import com.qianfeng.ykw.dao.ISystemUserDAO;
import com.qianfeng.ykw.pojo.LoginPassword;
import com.qianfeng.ykw.pojo.SystemUser;
import com.qianfeng.ykw.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录相关业务实现类
 *
 * @author 闫坤炜
 * @version 1.0
 */
@Service
public class ILoginServiceImpl implements ILoginService {
    
    @Autowired
    ISystemUserDAO systemUserDAO;
    
    
    @Autowired
    ILoginPasswordDAO loginPasswordDAO;
    
    /**
     * 完成登录功能
     * 接受用户名，如果存在，则继续查找密码，若密码存在则登录成功，否则登录失败
     *
     * @param parameter 登录所需参数
     * @param request
     * @return 登录结果
     */
    @Override
    public Map<String, Object> login(Map<String, Object> parameter, HttpServletRequest request) {
        Map<String, Object> returnMsg = new HashMap<>();
        String userName = (String) parameter.get("userName");
        String loginUserPassword = (String) parameter.get("loginUserPassword");
        List<SystemUser> systemUserList = systemUserDAO.selectByUserName(userName);
        if (systemUserList.size() > 0) {
            SystemUser systemUser = systemUserList.get(0);
            Map<String, Object> SQLParameter = new HashMap<>();
            SQLParameter.put("UID", systemUser.getUid());
            SQLParameter.put("loginUserPassword", loginUserPassword);
            //获取数据库对应密码
            LoginPassword loginPassword = loginPasswordDAO.selectByUID(SQLParameter);
            //比较密码
            //返回结果
            if (loginPassword != null) {
                //登录成功
                returnMsg.put("result", true);
                HttpSession session = request.getSession();
                //在session中存放用户角色(管理员)
                session.setAttribute("UserRoleType", UserRoleType.ROLE_ADMINISTRATOR);
                //保存用户登录信息(userId, userName)
                session.setAttribute("systemUser", systemUser);
            } else {//登录失败
                returnMsg.put("result", false);
                returnMsg.put("msg", "密码错误");
            }
        } else {//登录失败
            returnMsg.put("result", false);
            returnMsg.put("msg", "用户名错误");
        }
        return returnMsg;
    }
}
