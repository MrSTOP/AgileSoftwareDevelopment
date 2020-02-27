package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.BusinessInfo;

import java.util.List;

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

    /**
     * 查询得到所有数据库中商户
     * @return
     */
    List<BusinessInfo> selectAllBusiness();
}
