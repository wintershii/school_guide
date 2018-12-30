package com.winter.pojo;

import java.util.Date;

public class Place {
    private Integer id;

    private String name;

    private String intro;

    private Date createTime;

    private Date updateTime;

    public Place(Integer id, String name, String intro, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Place() {
        super();
    }

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
        this.name = name == null ? null : name.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}