package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.dao.BusinessInfoDAO;
import com.qianfeng.ykw.pojo.BusinessInfo;
import com.qianfeng.ykw.service.IBusinessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IBusinessInfoServiceImpl implements IBusinessInfoService {
    @Autowired
    BusinessInfoDAO businessInfoDAO;

    /**
     * 完成查询所有商户信息
     * @return
     */
    @Override
    public List<BusinessInfo> selectAllBusiness() {
        return businessInfoDAO.selectAllBusiness();
    }
}
