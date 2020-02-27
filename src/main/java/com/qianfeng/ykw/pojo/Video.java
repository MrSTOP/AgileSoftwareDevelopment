package com.qianfeng.ykw.pojo;


import java.util.Date;

public class Video {

  private long videoId;
  private String videoTitle;
  private String videoSrc;
  private Date videoDate;
  private long businessId;
  private String businessInfoLegalPerson;


  public long getVideoId() {
    return videoId;
  }

  public void setVideoId(long videoId) {
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


  public Date getVideoDate() {
    return videoDate;
  }

  public void setVideoDate(Date videoDate) {
    this.videoDate = videoDate;
  }


  public long getBusinessId() {
    return businessId;
  }

  public void setBusinessId(long businessId) {
    this.businessId = businessId;
  }
  
  
  public String getBusinessInfoLegalPerson() {
    return businessInfoLegalPerson;
  }
  
  public void setBusinessInfoLegalPerson(String businessInfoLegalPerson) {
    this.businessInfoLegalPerson = businessInfoLegalPerson;
  }
}
