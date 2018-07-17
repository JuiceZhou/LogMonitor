package com.summerzhou.domain;

import java.io.Serializable;

/**
 * 负责维护的用户信息
 * CREATE TABLE `log_monitor_user` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号，自增长',
 *   `name` varchar(20) DEFAULT NULL COMMENT '用户名称',
 *   `mobile` varchar(11) DEFAULT NULL COMMENT '用户手机号码',
 *   `email` varchar(50) DEFAULT NULL COMMENT '用户的邮箱地址，默认为公司邮箱',
 *   `isValid` int(1) DEFAULT NULL COMMENT '用户是否有效',
 *   `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户录入时间',
 *   `updateDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '用户信息修改时间',
 *   `createUser` varchar(100) DEFAULT NULL COMMENT '创建用户',
 *   `updateUser` varchar(100) DEFAULT NULL COMMENT '修改用户',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
 */
public class User implements Serializable {
    private Integer id;
    private String name;
    private String mobile;
    private String email;
    private Integer isValid;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
