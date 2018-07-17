package com.summerzhou.domain;

import java.io.Serializable;

public class Message implements Serializable {
    private String appId;//message所属的appID
    private String messageInfo;//日志具体内容
    private String ruleId;//触发的规则ID
    private Integer isEmail;
    private Integer isPhone;
    private String appName;//app名
    private String keyWord;//触发的关键词

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(Integer isEmail) {
        this.isEmail = isEmail;
    }

    public Integer getIsPhone() {
        return isPhone;
    }

    public void setIsPhone(Integer isPhone) {
        this.isPhone = isPhone;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "Message{" +
                "appId='" + appId + '\'' +
                ", messageInfo='" + messageInfo + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", isEmail=" + isEmail +
                ", isPhone=" + isPhone +
                ", appName='" + appName + '\'' +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
