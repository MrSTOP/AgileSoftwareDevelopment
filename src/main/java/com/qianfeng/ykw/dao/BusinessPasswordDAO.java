package com.qianfeng.ykw.dao;

import com.qianfeng.ykw.pojo.BusinessPassword;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public interface BusinessPasswordDAO {
    
    /**
     * 查询商户密码是否正确
     * @param businessPassword 商户密码信息
     * @return
     */
    BusinessPassword selectByPasswordAndId(BusinessPassword businessPassword);
    
    /**
     * 创建商户密码
     * @param businessPassword 商户密码信息
     * @return
     */
    int insertNewPassword(BusinessPassword businessPassword);
}
