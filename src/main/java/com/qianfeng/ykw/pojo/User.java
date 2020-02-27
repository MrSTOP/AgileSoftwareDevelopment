package com.qianfeng.ykw.pojo;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public class User {

  /**
   * 用户id
   * */
  private int userId;
  /**
   * 用户姓名
   * */
  private String userName;
  /**
   * 用户性别
   * */
  private String userSex;


  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserSex() {
    return userSex;
  }

  public void setUserSex(String userSex) {
    this.userSex = userSex;
  }

}
