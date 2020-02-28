package com.qianfeng.ykw.pojo;

/**
 *
 * @author 闫坤炜 严子超
 * @version 1.1
 */
public class BusinessInfo {

  private int businessInfoId;
  private String businessInfoName;
  private String businessInfoLegalPerson;
  private String businessInfoLegalPersonTel;
  private boolean businessIsfreeze;
  private String businessPassword;
  private String businessUsername;
  private int businessId;


  public int getBusinessInfoId() {
    return businessInfoId;
  }

  public void setBusinessInfoId(int businessInfoId) {
    this.businessInfoId = businessInfoId;
  }


  public String getBusinessInfoName() {
    return businessInfoName;
  }

  public void setBusinessInfoName(String businessInfoName) {
    this.businessInfoName = businessInfoName;
  }


  public String getBusinessInfoLegalPerson() {
    return businessInfoLegalPerson;
  }

  public void setBusinessInfoLegalPerson(String businessInfoLegalPerson) {
    this.businessInfoLegalPerson = businessInfoLegalPerson;
  }


  public String getBusinessInfoLegalPersonTel() {
    return businessInfoLegalPersonTel;
  }

  public void setBusinessInfoLegalPersonTel(String businessInfoLegalPersonTel) {
    this.businessInfoLegalPersonTel = businessInfoLegalPersonTel;
  }


  public int getBusinessId() {
    return businessId;
  }

  public void setBusinessId(int businessId) {
    this.businessId = businessId;
  }

  public boolean isBusinessIsfreeze() {
    return businessIsfreeze;
  }

  public void setBusinessIsfreeze(boolean businessIsfreeze) {
    this.businessIsfreeze = businessIsfreeze;
  }

  public String getBusinessPassword() {
    return businessPassword;
  }

  public void setBusinessPassword(String businessPassword) {
    this.businessPassword = businessPassword;
  }

  public String getBusinessUsername() {
    return businessUsername;
  }

  public void setBusinessUsername(String businessUsername) {
    this.businessUsername = businessUsername;
  }
}
