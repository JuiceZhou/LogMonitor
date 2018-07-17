package com.summerzhou.domain;

import java.io.Serializable;

/**
 * 日志报警触发规则
 * CREATE TABLE `log_monitor_rule` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规则编号，自增长',
 *   `name` varchar(100) DEFAULT NULL COMMENT '规则名称',
 *   `desc` varchar(250) DEFAULT NULL COMMENT '规则描述',
 *   `keyword` varchar(100) DEFAULT NULL COMMENT '规则关键词',
 *   `isValid` int(1) DEFAULT NULL COMMENT '规则是否可用',
 *   `appId` int(11) DEFAULT NULL COMMENT '规则所属应用',
 *   `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '规则创建时间',
 *   `updateDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '规则修改时间',
 *   `createUser` varchar(100) DEFAULT NULL COMMENT '创建用户',
 *   `updateUser` varchar(100) DEFAULT NULL COMMENT '修改用户',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
 */
public class Rule implements Serializable {
    private Integer id;//规则编号
    private String name;//规则名称
    private Integer isValid;//是否有效
    private String keyword;//规则关键词
    private Integer appId;//规则所属应用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValid() {
        return isValid;
    }

    public void setValid(Integer valid) {
        isValid = valid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isValid=" + isValid +
                ", keyword='" + keyword + '\'' +
                ", appId=" + appId +
                '}';
    }
}
