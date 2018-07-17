package com.summerzhou.domain;

import java.io.Serializable;

/**
 * 触发警告的日志记录
 * CREATE TABLE `log_monitor_rule_record` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '告警信息编号',
 *   `appId` int(11) DEFAULT NULL COMMENT '告警信息所属应用编号',
 *   `ruleId` int(11) DEFAULT NULL COMMENT '告警信息所属规则编号',
 *   `isEmail` int(11) DEFAULT '0' COMMENT '是否邮件告知，0：未告知  1：告知',
 *   `isPhone` int(11) DEFAULT '0' COMMENT '是否短信告知，0：未告知  1：告知',
 *   `isColse` int(11) DEFAULT '0' COMMENT '是否处理完毕，0：未处理  1：已处理',
 *   `noticeInfo` varchar(500) DEFAULT NULL COMMENT '告警信息明细',
 *   `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '告警信息入库时间',
 *   `updataDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '告警信息修改时间',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;
 */
public class Record implements Serializable {
    private Integer appId;
    private Integer ruleId;
    private String messageInfo;
    private Integer isEmail;//告警信息是否通过邮件告警
    private Integer isPhone;//告警信息是否通过短信告警
    private Boolean appName;//app名
    private String keyWord;//触发的关键词

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
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

    public Boolean getAppName() {
        return appName;
    }

    public void setAppName(Boolean appName) {
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
        return "Record{" +
                "appId=" + appId +
                ", ruleId=" + ruleId +
                ", messageInfo='" + messageInfo + '\'' +
                ", isEmail=" + isEmail +
                ", isPhone=" + isPhone +
                ", appName=" + appName +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
