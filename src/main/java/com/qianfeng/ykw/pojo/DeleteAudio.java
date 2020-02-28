package com.qianfeng.ykw.pojo;


public class DeleteAudio {
  
  public static final int DELETE_BY_ADMINISTRATOR = 0;
  public static final int DELETE_BY_BUSINESS = 1;

  private int audioId;
  private String audioTitle;
  private String audioSrc;
  private java.sql.Timestamp audioDate;
  private int businessId;
  private java.sql.Timestamp deleteAudioDate;
  private int deleteType;
  private int uid;
  private String businessInfoLegalPerson;
  private boolean isRecoverable;


  public int getAudioId() {
    return audioId;
  }

  public void setAudioId(int audioId) {
    this.audioId = audioId;
  }


  public String getAudioTitle() {
    return audioTitle;
  }

  public void setAudioTitle(String audioTitle) {
    this.audioTitle = audioTitle;
  }


  public String getAudioSrc() {
    return audioSrc;
  }

  public void setAudioSrc(String audioSrc) {
    this.audioSrc = audioSrc;
  }


  public java.sql.Timestamp getAudioDate() {
    return audioDate;
  }

  public void setAudioDate(java.sql.Timestamp audioDate) {
    this.audioDate = audioDate;
  }


  public int getBusinessId() {
    return businessId;
  }

  public void setBusinessId(int businessId) {
    this.businessId = businessId;
  }


  public java.sql.Timestamp getDeleteAudioDate() {
    return deleteAudioDate;
  }

  public void setDeleteAudioDate(java.sql.Timestamp deleteAudioDate) {
    this.deleteAudioDate = deleteAudioDate;
  }


  public int getDeleteType() {
    return deleteType;
  }

  public void setDeleteType(int deleteType) {
    this.deleteType = deleteType;
  }


  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }
  
  public String getBusinessInfoLegalPerson() {
    return businessInfoLegalPerson;
  }
  
  public void setBusinessInfoLegalPerson(String businessInfoLegalPerson) {
    this.businessInfoLegalPerson = businessInfoLegalPerson;
  }
  
  public boolean isRecoverable() {
    return isRecoverable;
  }
  
  public void setRecoverable(boolean recoverable) {
    isRecoverable = recoverable;
  }
}
