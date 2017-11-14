package com.bhidi.lincang.bean;

public class PictureTown {
    private String countyName;
    private String townName;
    private String level;
    private int num;
    private int households;

    public PictureTown() {
    }

    public PictureTown(String countyName, String townName, String level, int num, int households) {
        this.countyName = countyName;
        this.townName = townName;
        this.level = level;
        this.num = num;
        this.households = households;
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

    public int getHouseholds() {
        return households;
    }

    public void setHouseholds(int households) {
        this.households = households;
    }

    @Override
    public String toString() {
        return "PictureTown{" +
                "countyName='" + countyName + '\'' +
                ", townName='" + townName + '\'' +
                ", level='" + level + '\'' +
                ", num=" + num +
                ", households=" + households +
                '}';
    }
}
