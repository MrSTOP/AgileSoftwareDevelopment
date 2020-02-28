package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.Business;

import java.util.List;

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

    /**
     * 冻结解冻账户
     * @param business
     * @return
     */
    int freezeBuiness(Business business);

    /**
     * 找出数据库汇总已冻结账户
     * @return
     */
    List<Business> selectByBusinessIsfreeze();

    /**
     * 根据ID更新数据库中的Business
     * @param business
     * @return
     */
    int updateBusiness(Business business);
}
