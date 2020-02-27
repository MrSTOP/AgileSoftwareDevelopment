package com.qianfeng.ykw.pojo;


/**
 * @author 闫坤炜
 * @version 1.0
 * 用户详细息
 */
public class UserInfo {
  
  
  /**
   * 用户详细信息ID
   */
  private int userInfoID;
  /**
   * 用户真实姓名
   */
  private String userInfoTrueName;
  /**
   * 用户电话
   */
  private String userInfoTel;
  /**
   * 用户性别
   */
  private String userInfoSex;
  /**
   * 外键：用户UID
   */
  private int uid;


  public int getUserInfoID() {
    return userInfoID;
  }

  public void setUserInfoID(int userInfoID) {
    this.userInfoID = userInfoID;
  }


  public String getUserInfoTrueName() {
    return userInfoTrueName;
  }

  public void setUserInfoTrueName(String userInfoTrueName) {
    this.userInfoTrueName = userInfoTrueName;
  }


  public String getUserInfoTel() {
    return userInfoTel;
  }

  public void setUserInfoTel(String userInfoTel) {
    this.userInfoTel = userInfoTel;
  }


  public String getUserInfoSex() {
    return userInfoSex;
  }

  public void setUserInfoSex(String userInfoSex) {
    this.userInfoSex = userInfoSex;
  }


  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

}
