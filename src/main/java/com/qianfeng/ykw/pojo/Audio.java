package com.qianfeng.ykw.pojo;

import java.util.Date;

public class Audio {
    private long audioId;
    private String audioTitle;
    private String audioSrc;
    private Date audioDate;
    private long businessId;
    private String businessInfoLegalPerson;

    public long getAudioId() {
        return audioId;
    }

    public void setAudioId(long audioId) {
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

    public Date getAudioDate() {
        return audioDate;
    }

    public void setAudioDate(Date audioDate) {
        this.audioDate = audioDate;
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
