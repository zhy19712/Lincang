package com.bhidi.lincang.bean;

public class PictureCounty {
    private String countyName;
    private String level;
    private int num;
    private int households;

    public PictureCounty() {
    }

    public PictureCounty(String countyName, String level, int num, int households) {
        this.countyName = countyName;
        this.level = level;
        this.num = num;
        this.households = households;
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

    public int getHouseholds() {
        return households;
    }

    public void setHouseholds(int households) {
        this.households = households;
    }

    @Override
    public String toString() {
        return "PictureCounty{" +
                "countyName='" + countyName + '\'' +
                ", level='" + level + '\'' +
                ", num=" + num +
                ", households=" + households +
                '}';
    }
}
