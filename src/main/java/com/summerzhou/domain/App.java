package com.summerzhou.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志对应的APP信息，与mysql中的`log_monitor_app`对应
 */
public class App implements Serializable {
    private Integer id;//应用编号
    private String name;//应用名称
    private String desc;//应用描述
    private Integer isOnline;//应用是否在线
    private Integer typeId;//应用类型对应的ID
    private Date createDate;
    private Date updateDate;
    private Integer userId;//应用所属用户

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOnline() {
        return isOnline;
    }

    public void setOnline(Integer online) {
        isOnline = online;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", isOnline=" + isOnline +
                ", typeId=" + typeId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", userId=" + userId +
                '}';
    }
}
