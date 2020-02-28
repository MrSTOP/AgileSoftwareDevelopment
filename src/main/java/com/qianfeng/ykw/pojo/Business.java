package com.qianfeng.ykw.pojo;


/**
 *
 * @author 闫坤炜 严子超
 * @version 1.1
 */
public class Business {

  private int businessId;
  private String businessUsername;
  private boolean businessIsfreeze;

  public int getBusinessId() {
    return businessId;
  }

  public void setBusinessId(int businessId) {
    this.businessId = businessId;
  }


  public String getBusinessUsername() {
    return businessUsername;
  }

  public void setBusinessUsername(String businessUsername) {
    this.businessUsername = businessUsername;
  }

  public boolean isBusinessIsfreeze() {
    return businessIsfreeze;
  }

  public void setBusinessIsfreeze(boolean businessIsfreeze) {
    this.businessIsfreeze = businessIsfreeze;
  }
}
