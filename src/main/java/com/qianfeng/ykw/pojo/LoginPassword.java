package com.qianfeng.ykw.pojo;

/**
 * @author 闫坤炜
 * @version 1.0
 * 用户登录密码信息
 * */
public class LoginPassword {
  
  
  /**
   * 用户登录ID
   */
  private int loginId;
  
  /**
   * 用户登录密码
   */
  private String loginUserPassword;
  
  /**
   * 外键：用户UID
   */
  private long uid;


  public int getLoginId() {
    return loginId;
  }

  public void setLoginId(int loginId) {
    this.loginId = loginId;
  }


  public String getLoginUserPassword() {
    return loginUserPassword;
  }

  public void setLoginUserPassword(String loginUserPassword) {
    this.loginUserPassword = loginUserPassword;
  }
  
  
  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

}
