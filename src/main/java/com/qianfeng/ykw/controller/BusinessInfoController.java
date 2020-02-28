package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.BusinessInfo;
import com.qianfeng.ykw.service.IBusinessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/BusinessInfoController")
public class BusinessInfoController {
    @Autowired
    IBusinessInfoService businessInfoService;

    /**
     * Controller 得到商户所有信息 传出
     * @param request
     * @return
     */
    @RequestMapping("/selectAllBusinessInfo")
    public String selectAllBusinessInfo(HttpServletRequest request)
    {
        List<BusinessInfo> businessInfoList = businessInfoService.selectAllBusiness();
        request.setAttribute("businessInfoList", businessInfoList);
        return "/pages/systemuser/query_all_business";
    }


    /**
     * 通过ID得到账户的密码登录名等所有信息
     * @param request
     * @return
     */
    @RequestMapping("/selectAllBusinessInfoWithPass")
    @ResponseBody
    public BusinessInfo selectAllBusinessInfoWithPass(HttpServletRequest request)
    {
        Business business = (Business) request.getSession().getAttribute("business");

        return businessInfoService.selectAllBusinessInfoWithPass(business.getBusinessId());
    }

    /**
     * 更新商户信息密码等传出结果
     * @param businessInfo
     * @param request
     * @return
     */
    @RequestMapping("/updateBusinessInfo")
    public String updateBusinessInfo(BusinessInfo businessInfo, HttpServletRequest request) {
        String msg = businessInfoService.updateBusinessInfo(businessInfo, request);

        request.setAttribute("msg", msg);

        return "/pages/business/change_business";
    }
}
