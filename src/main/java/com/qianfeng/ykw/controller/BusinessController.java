package com.qianfeng.ykw.controller;

import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.pojo.BusinessInfo;
import com.qianfeng.ykw.pojo.SystemUserAllInfo;
import com.qianfeng.ykw.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/BusinessController")
public class BusinessController {

    @Autowired
    IBusinessService iBusinessService;

    /**
     * 得到页面中的Id 返回冻结或者解冻账户结果
     * @param business
     * @return
     */
    @RequestMapping("/freezeBusiness")
    @ResponseBody
    public Map<String, Object> freezeBusiness(Business business){
        HashMap<String, Object> returnMap = new HashMap<>();
        if(iBusinessService.freezeBuiness(business)){
            if(business.isBusinessIsfreeze()){
                returnMap.put("msg", "冻结成功");
            }else {
                returnMap.put("msg", "商户已解冻");
            }
            returnMap.put("result", true);
        }else {
            returnMap.put("msg", "操作失败");
            returnMap.put("result", false);
        }
        return returnMap;
    }

    /**
     * 找到冻结账户并且传至页面中
     * @param request
     * @return
     */
    @RequestMapping("/selectBusinessIsfreeze")
    public String selectBusinessIsfreeze(HttpServletRequest request){
        List<Business> businessList = iBusinessService.selectByBusinessIsfreeze();
        request.setAttribute("businessList", businessList);
        return "/pages/systemuser/freeze_business";
    }




}
