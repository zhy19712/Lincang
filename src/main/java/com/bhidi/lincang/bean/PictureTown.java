package com.bhidi.lincang.bean;

public class PictureTown {
    private String countyName;
    private String townName;
    private String level;
    private int num;

    public PictureTown() {
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PictureTown{" +
                "countyName='" + countyName + '\'' +
                ", level='" + level + '\'' +
                ", townName='" + townName + '\'' +
                ", num=" + num +
                '}';
    }
}
