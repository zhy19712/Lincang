package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/23.
 */
public class Move {
    private String fid;
    private String from_city;
    private String from_district;
    private String from_town;
    private String from_village;
    private String from_group;
    private String from_remark;

    private String to_city;
    private String to_district;
    private String to_town;
    private String to_village;
    private String to_group;
    private String to_remark;

    public Move() {

    }

    public Move(String fid, String from_city, String from_district, String from_town, String from_village, String from_group, String from_remark, String to_city, String to_district, String to_town, String to_village, String to_group, String to_remark) {
        this.fid = fid;
        this.from_city = from_city;
        this.from_district = from_district;
        this.from_town = from_town;
        this.from_village = from_village;
        this.from_group = from_group;
        this.from_remark = from_remark;
        this.to_city = to_city;
        this.to_district = to_district;
        this.to_town = to_town;
        this.to_village = to_village;
        this.to_group = to_group;
        this.to_remark = to_remark;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getFrom_district() {
        return from_district;
    }

    public void setFrom_district(String from_district) {
        this.from_district = from_district;
    }

    public String getFrom_town() {
        return from_town;
    }

    public void setFrom_town(String from_town) {
        this.from_town = from_town;
    }

    public String getFrom_village() {
        return from_village;
    }

    public void setFrom_village(String from_village) {
        this.from_village = from_village;
    }

    public String getFrom_group() {
        return from_group;
    }

    public void setFrom_group(String from_group) {
        this.from_group = from_group;
    }

    public String getFrom_remark() {
        return from_remark;
    }

    public void setFrom_remark(String from_remark) {
        this.from_remark = from_remark;
    }

    public String getTo_city() {
        return to_city;
    }

    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    public String getTo_district() {
        return to_district;
    }

    public void setTo_district(String to_district) {
        this.to_district = to_district;
    }

    public String getTo_town() {
        return to_town;
    }

    public void setTo_town(String to_town) {
        this.to_town = to_town;
    }

    public String getTo_village() {
        return to_village;
    }

    public void setTo_village(String to_village) {
        this.to_village = to_village;
    }

    public String getTo_group() {
        return to_group;
    }

    public void setTo_group(String to_group) {
        this.to_group = to_group;
    }

    public String getTo_remark() {
        return to_remark;
    }

    public void setTo_remark(String to_remark) {
        this.to_remark = to_remark;
    }

    @Override
    public String toString() {
        return "Move{" +
                "fid='" + fid + '\'' +
                ", from_city='" + from_city + '\'' +
                ", from_district='" + from_district + '\'' +
                ", from_town='" + from_town + '\'' +
                ", from_village='" + from_village + '\'' +
                ", from_group='" + from_group + '\'' +
                ", from_remark='" + from_remark + '\'' +
                ", to_city='" + to_city + '\'' +
                ", to_district='" + to_district + '\'' +
                ", to_town='" + to_town + '\'' +
                ", to_village='" + to_village + '\'' +
                ", to_group='" + to_group + '\'' +
                ", to_remark='" + to_remark + '\'' +
                '}';
    }
}
