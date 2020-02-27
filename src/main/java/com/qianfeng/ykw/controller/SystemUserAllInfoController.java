package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.pojo.SystemUser;
import com.qianfeng.ykw.pojo.SystemUserAllInfo;
import com.qianfeng.ykw.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 闫坤炜
 * @version 1.0
 */
@Controller
@RequestMapping("/SystemUserAllInfoController")
public class SystemUserAllInfoController {
    
    @Autowired
    ISystemUserService systemUserService;
    
    @RequestMapping("/querySystemUserAllInfo")
    public String querySystemUserAllInfo(HttpServletRequest request) {
        List<SystemUserAllInfo> systemUserAllInfoList = systemUserService.selectSystemUserAllInfo();
        request.setAttribute("systemUserAllInfoList", systemUserAllInfoList);
        return "/pages/systemuser/query_sysuser";
    }
    
    @RequestMapping("/getSystemUserAllInfoByUid")
    @ResponseBody
    public SystemUserAllInfo getSystemUserAllInfoByUid(HttpServletRequest request) {
        SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
        return systemUserService.selectSystemUserAllInfoByUid(systemUser.getUid());
    }
    
    @RequestMapping("/updateSystemUserInfo")
    public String updateSystemUserInfo(SystemUserAllInfo systemUserAllInfo, HttpServletRequest request) {
        if (systemUserService.updateSystemUserInfo(systemUserAllInfo, request)) {
            request.setAttribute("msg", "修改成功");
        } else {
            request.setAttribute("msg", "修改失败");
        }
        return "/pages/systemuser/change_sysuser";
    }
}
