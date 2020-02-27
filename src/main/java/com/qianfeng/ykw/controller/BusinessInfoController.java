package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.pojo.BusinessInfo;
import com.qianfeng.ykw.service.IBusinessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
