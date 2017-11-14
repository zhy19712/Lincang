package com.bhidi.lincang.bean;

import java.util.List;

public class PictureFull {
    //名字
    private String name;
    //级别
    private String level;
    //数量
    private int num;
    //户数
    private int households;
    //下一级的集合
    private List<PictureFull> listChild;

    public PictureFull() {
    }

    public PictureFull(String name, String level, int num, int households, List<PictureFull> listChild) {
        this.name = name;
        this.level = level;
        this.num = num;
        this.households = households;
        this.listChild = listChild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PictureFull> getListChild() {
        return listChild;
    }

    public void setListChild(List<PictureFull> listChild) {
        this.listChild = listChild;
    }

    public int getHouseholds() {
        return households;
    }

    public void setHouseholds(int households) {
        this.households = households;
    }

    @Override
    public String toString() {
        return "PictureFull{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", num=" + num +
                ", households=" + households +
                ", listChild=" + listChild +
                '}';
    }
}
