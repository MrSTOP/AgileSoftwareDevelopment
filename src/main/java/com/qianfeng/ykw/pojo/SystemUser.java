package com.qianfeng.ykw.pojo;

/**
 * @author 闫坤炜
 * @version 1.0
 * 用户登录用户名信息
 * */
public class SystemUser {
  
  
  /**
   * 用户UID
   */
  private int uid;
  
  
  /**
   * 用户登录名
   */
  private String userName;


  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}
