package com.qianfeng.ykw.pojo;

/**
 * @author 闫坤炜
 * @version 1.0
 */
public class SystemUserAllInfo {
    private int uid;
    private String userInfoTrueName;
    private String userInfoTel;
    private String userInfoSex;
    private String userName;
    private String loginUserPassword;
    
    public int getUid() {
        return uid;
    }
    
    public void setUid(int uid) {
        this.uid = uid;
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
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getLoginUserPassword() {
        return loginUserPassword;
    }
    
    public void setLoginUserPassword(String loginUserPassword) {
        this.loginUserPassword = loginUserPassword;
    }
}
