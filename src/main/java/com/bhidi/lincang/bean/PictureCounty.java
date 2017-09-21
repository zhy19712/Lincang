package com.bhidi.lincang.bean;

public class PictureCounty {
    private String countyName;
    private String level;
    private int num;

    public PictureCounty() {
    }

    public PictureCounty(String level, String countyName, int num) {
        this.level = level;
        this.countyName = countyName;
        this.num = num;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PictureCounty{" +
                "level='" + level + '\'' +
                ", countyName='" + countyName + '\'' +
                ", num=" + num +
                '}';
    }
}
