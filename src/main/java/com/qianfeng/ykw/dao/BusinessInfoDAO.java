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


    List<BusinessInfo> selectAllBusinessByisFreeze(boolean isFreeze);

    /**
     * 更新数据库中的商户信息
     * @param businessInfo
     * @return
     */
    int updateBusinessInfo(BusinessInfo businessInfo);

    /**
     * 多表连接查询商户信息登录名等
     * @param businessId
     * @return
     */
    BusinessInfo selectAllBusinessInfoWithPass(int businessId);
}
