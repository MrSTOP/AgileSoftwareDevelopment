package com.qianfeng.ykw.dao;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public interface BusinessInfoDAO {
    
    /**
     * 创建对应的基础信息
     * @param businessId 商户Id
     * @return
     */
    int insertBlankBusinessInfo(int businessId);
}
