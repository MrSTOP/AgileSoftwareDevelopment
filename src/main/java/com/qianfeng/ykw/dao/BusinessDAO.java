package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.Business;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public interface BusinessDAO {
    /**
     * 查询商户是否存在
     * @param businessName 商户名
     * @return
     */
    Business selectByBusinessName(String businessName);
    
    /**
     * 创建商户
     * @param businessName
     * @return
     */
    int insertNewBusiness(String businessName);
}
