package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.UserRoleType;
import com.qianfeng.ykw.dao.BusinessDAO;
import com.qianfeng.ykw.dao.BusinessInfoDAO;
import com.qianfeng.ykw.dao.BusinessPasswordDAO;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.BusinessPassword;
import com.qianfeng.ykw.service.IBusinessLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Service
public class IBusinessLoginServiceImpl implements IBusinessLoginService {
    
    @Autowired
    BusinessDAO businessDAO;
    
    @Autowired
    BusinessPasswordDAO businessPasswordDAO;
    
    @Autowired
    BusinessInfoDAO businessInfoDAO;
    
    /**
     * 登录相关
     *
     * @param parameter
     * @return
     */
    @Override
    public Map<String, Object> login(Map<String, Object> parameter, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        //接受数据
        String businessUsername = (String) parameter.get("businessUsername");
        String businessPassword = (String) parameter.get("businessPassword");
        //检测用户名是否存在
        Business business = businessDAO.selectByBusinessName(businessUsername);
        if (business != null) {//用户名存在
            if(!business.isBusinessIsfreeze())
            {
                //检测用户密码是否存在
                BusinessPassword password = new BusinessPassword();
                password.setBusinessId(business.getBusinessId());
                password.setBusinessPassword(businessPassword);
                BusinessPassword passwordResult = businessPasswordDAO.selectByPasswordAndId(password);
                if (passwordResult != null) {
                    //登录成功
                    resultMap.put("result", true);
                    HttpSession session = request.getSession();
                    //在session中存放用户角色(商户)
                    session.setAttribute("UserRoleType", UserRoleType.ROLE_BUSINESS);
                    //保存用户登录信息(businessId, businessUsername)
                    session.setAttribute("business", business);
                } else {
                    resultMap.put("result", false);
                    resultMap.put("msg", "请检查您的密码");
                    resultMap.put("resultType", 1);
                }
            }else{
                resultMap.put("result", false);
                resultMap.put("msg", "账户已被冻结，请联系管理员");
                resultMap.put("resultType", 1);
            }

        } else {
            //用户名不存在
            resultMap.put("result", false);
            resultMap.put("msg", "请检查密码，您是否还没有账户，如果没有，点击确定，我们将会为您创建账户");
            resultMap.put("resultType", 2);
        }
        //结果反馈
        return resultMap;
    }
    
    /**
     * 创建商户密码
     * @param parameter
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> register(Map<String, Object> parameter, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        //接收数据
        String businessUsername = (String) parameter.get("businessUsername");
        String businessPassword = (String) parameter.get("businessPassword");
        //创建账号
        //创建用户名
        if (businessDAO.insertNewBusiness(businessUsername) > 0) {
            //创建当前用户对应密码
            Business business = businessDAO.selectByBusinessName(businessUsername);
            BusinessPassword password = new BusinessPassword();
            password.setBusinessId(business.getBusinessId());
            password.setBusinessPassword(businessPassword);
            if (businessPasswordDAO.insertNewPassword(password) > 0) {
                //创建当前用户对应商户信息
                if (businessInfoDAO.insertBlankBusinessInfo(business.getBusinessId()) > 0) {
                    resultMap.put("result", true);
                    HttpSession session = request.getSession();
                    //在session中存放用户角色(商户)
                    session.setAttribute("UserRoleType", UserRoleType.ROLE_BUSINESS);
                    //保存用户登录信息(businessId, businessUsername)
                    session.setAttribute("business", business);
                } else {
                    resultMap.put("result", false);
                    resultMap.put("msg", "商户信息创建失败");
                }
            } else {
                resultMap.put("result", false);
                resultMap.put("msg", "商户密码创建失败");
            }
        } else {
            resultMap.put("result", false);
            resultMap.put("msg", "商户账户创建失败");
        }
        return resultMap;
    }
}
