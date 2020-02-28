package com.qianfeng.ykw.service;

import com.qianfeng.ykw.pojo.Business;

import java.util.List;

public interface IBusinessService {

    /**
     * 冻结商户
     * @param business
     * @return
     */
    Boolean freezeBuiness(Business business);

    /**
     * 查询已冻结商户
     * @return
     */
    List<Business> selectByBusinessIsfreeze();
}
