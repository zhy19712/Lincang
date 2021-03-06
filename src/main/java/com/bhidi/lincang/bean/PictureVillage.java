package com.bhidi.lincang.bean;

public class PictureVillage {
    private String countyName;
    private String townName;
    private String villageName;
    private String level;
    private int num;
    private int households;

    public PictureVillage() {
    }

    public PictureVillage(String countyName, String townName, String villageName, String level, int num, int households) {
        this.countyName = countyName;
        this.townName = townName;
        this.villageName = villageName;
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

    public int getHouseholds() {
        return households;
    }

    public void setHouseholds(int households) {
        this.households = households;
    }

    @Override
    public String toString() {
        return "PictureVillage{" +
                "countyName='" + countyName + '\'' +
                ", townName='" + townName + '\'' +
                ", villageName='" + villageName + '\'' +
                ", level='" + level + '\'' +
                ", num=" + num +
                ", households=" + households +
                '}';
    }
}
