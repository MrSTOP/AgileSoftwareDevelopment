package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.BusinessInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IBusinessInfoService {
    /**
     * 得到所有商户信息
     * @return
     */
    List<BusinessInfo> selectAllBusiness();

    /**
     * 更新商户信息
     * @param businessInfo
     * @param request
     * @return
     */
     String updateBusinessInfo(BusinessInfo businessInfo, HttpServletRequest request);

    /**
     * 得到商户信息密码等
     * @param businessId
     * @return
     */
    BusinessInfo selectAllBusinessInfoWithPass(int businessId);
}
