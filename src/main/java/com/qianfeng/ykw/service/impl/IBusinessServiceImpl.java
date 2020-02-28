package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.dao.BusinessDAO;
import com.qianfeng.ykw.pojo.Business;
import com.qianfeng.ykw.service.IBusinessInfoService;
import com.qianfeng.ykw.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBusinessServiceImpl implements IBusinessService {

    @Autowired
    BusinessDAO businessDAO;

    /**
     * 冻结或者解冻商户
     * @param business
     * @return
     */
    @Override
    public Boolean freezeBuiness(Business business) {
       int i =  businessDAO.freezeBuiness(business);
       if(i>0){
           return true;
       }else {
           return false;
       }

    }

    /**
     * 完成查找所有被冻结账户
     * @return
     */
    @Override
    public List<Business> selectByBusinessIsfreeze() {
        return  businessDAO.selectByBusinessIsfreeze();
    }


}
