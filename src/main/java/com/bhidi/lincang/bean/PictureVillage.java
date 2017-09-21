package com.bhidi.lincang.bean;

public class PictureVillage {
    private String countyName;
    private String townName;
    private String villageName;
    private String level;
    private int num;

    public PictureVillage() {
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PictureVillage{" +
                "countyName='" + countyName + '\'' +
                ", townName='" + townName + '\'' +
                ", villageName='" + villageName + '\'' +
                ", level='" + level + '\'' +
                ", num=" + num +
                '}';
    }
}
