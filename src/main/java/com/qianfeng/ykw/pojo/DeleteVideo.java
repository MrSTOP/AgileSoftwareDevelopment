package com.qianfeng.ykw.pojo;


public class DeleteVideo {

  private int videoId;
  private String videoTitle;
  private String videoSrc;
  private java.sql.Timestamp videoDate;
  private int businessId;
  private java.sql.Timestamp deleteVideoDate;
  private int deleteType;
  private int uid;
  private String businessInfoLegalPerson;


  public int getVideoId() {
    return videoId;
  }

  public void setVideoId(int videoId) {
    this.videoId = videoId;
  }


  public String getVideoTitle() {
    return videoTitle;
  }

  public void setVideoTitle(String videoTitle) {
    this.videoTitle = videoTitle;
  }


  public String getVideoSrc() {
    return videoSrc;
  }

  public void setVideoSrc(String videoSrc) {
    this.videoSrc = videoSrc;
  }


  public java.sql.Timestamp getVideoDate() {
    return videoDate;
  }

  public void setVideoDate(java.sql.Timestamp videoDate) {
    this.videoDate = videoDate;
  }


  public int getBusinessId() {
    return businessId;
  }

  public void setBusinessId(int businessId) {
    this.businessId = businessId;
  }


  public java.sql.Timestamp getDeleteVideoDate() {
    return deleteVideoDate;
  }

  public void setDeleteVideoDate(java.sql.Timestamp deleteVideoDate) {
    this.deleteVideoDate = deleteVideoDate;
  }


  public long getDeleteType() {
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
}
