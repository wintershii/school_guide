package com.winter.pojo;

import java.util.Date;

public class Route {
    private Integer id;

    private Integer startId;

    private String startName;

    private Integer arriveId;

    private String arriveName;

    private Integer distant;

    private Date createTime;

    private Date updateTime;


    public Integer getDistant() {
        return distant;
    }

    public void setDistant(Integer distant) {
        this.distant = distant;
    }

    public Route(Integer id, Integer startId, String startName, Integer arriveId, String arriveName, Integer distant, Date createTime, Date updateTime) {
        this.id = id;
        this.startId = startId;
        this.startName = startName;
        this.arriveId = arriveId;
        this.arriveName = arriveName;
        this.distant = distant;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Route(Integer startId, String startName, Integer arriveId, String arriveName, Integer distant) {
        this.startId = startId;
        this.startName = startName;
        this.arriveId = arriveId;
        this.arriveName = arriveName;
        this.distant = distant;
    }

    public Route() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName == null ? null : startName.trim();
    }

    public Integer getArriveId() {
        return arriveId;
    }

    public void setArriveId(Integer arriveId) {
        this.arriveId = arriveId;
    }

    public String getArriveName() {
        return arriveName;
    }

    public void setArriveName(String arriveName) {
        this.arriveName = arriveName == null ? null : arriveName.trim();
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
        return "Route{" +
                "startId=" + startId +
                ", startName='" + startName + '\'' +
                ", arriveId=" + arriveId +
                ", arriveName='" + arriveName + '\'' +
                ", distant=" + distant +
                '}';
    }
}