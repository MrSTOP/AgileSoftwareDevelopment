package com.qianfeng.ykw.service.impl;

import com.qianfeng.ykw.dao.BusinessDAO;
import com.qianfeng.ykw.dao.BusinessInfoDAO;
import com.qianfeng.ykw.dao.BusinessPasswordDAO;
import com.qianfeng.ykw.pojo.*;
import com.qianfeng.ykw.service.IBusinessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class IBusinessInfoServiceImpl implements IBusinessInfoService {
    @Autowired
    BusinessInfoDAO businessInfoDAO;

    @Autowired
    BusinessDAO businessDAO;

    @Autowired
    BusinessPasswordDAO businessPasswordDAO;
    /**
     * 完成查询所有商户信息
     * @return
     */
    @Override
    public List<BusinessInfo> selectAllBusiness() {
        return businessInfoDAO.selectAllBusiness();
    }

    /**
     * 完成更新商户信息
     * @param businessInfo
     * @param request
     * @return
     */
    @Override
    public String updateBusinessInfo(BusinessInfo businessInfo, HttpServletRequest request) {
        Business judgeBusiness = businessDAO.selectByBusinessName(businessInfo.getBusinessUsername());
        Business nowBusiness = (Business) request.getSession().getAttribute("business");
        String msg;
        boolean repeatName = false;
        if(judgeBusiness != null) {
            if (!judgeBusiness.getBusinessUsername().equals(nowBusiness.getBusinessUsername()) ) {
                repeatName = true;
            }
        }
        if(!repeatName)
        {
            int uid = nowBusiness.getBusinessId();
            businessInfo.setBusinessId(uid);

            Business business = new Business();
            business.setBusinessId(businessInfo.getBusinessId());
            business.setBusinessUsername(businessInfo.getBusinessUsername());
            int resBusiness = businessDAO.updateBusiness(business);
            BusinessPassword businessPassword = new BusinessPassword();
            businessPassword.setBusinessId(businessInfo.getBusinessId());
            businessPassword.setBusinessPassword(businessInfo.getBusinessPassword());
            int resbusinessPassword = businessPasswordDAO.updateBusinessPasswordByID(businessPassword);

            //   g(5).hashCode();

            BusinessInfo businessInfo1 = new BusinessInfo();
            businessInfo1.setBusinessId(businessInfo.getBusinessId());
            businessInfo1.setBusinessInfoLegalPerson(businessInfo.getBusinessInfoLegalPerson());
            businessInfo1.setBusinessInfoName(businessInfo.getBusinessInfoName());
            businessInfo1.setBusinessInfoLegalPersonTel(businessInfo.getBusinessInfoLegalPersonTel());
            int resBusinessInfo = businessInfoDAO.updateBusinessInfo(businessInfo1);
            if (resBusiness > 0 && resbusinessPassword > 0 && resBusinessInfo > 0) {
                msg = "修改成功";
                return msg;
            }else {
                msg = "修改失败";
                return msg;
            }
        }else {
            msg = "登录名重复";
            return msg;
        }
    }

    /**
     * 根据Id查询该商户所有信息
     * @param businessId
     * @return
     */
    @Override
    public BusinessInfo selectAllBusinessInfoWithPass(int businessId) {
        return businessInfoDAO.selectAllBusinessInfoWithPass(businessId);
    }


}
