package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.BusinessInfo;

import java.util.List;

public interface IBusinessInfoService {
    /**
     * 得到所有商户信息
     * @return
     */
    List<BusinessInfo> selectAllBusiness();
}
