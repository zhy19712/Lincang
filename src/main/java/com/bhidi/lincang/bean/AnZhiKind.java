package com.bhidi.lincang.bean;

public class AnZhiKind {
    private String anZhiName;
    private int numOfAnZhi;

    public AnZhiKind() {
    }

    public AnZhiKind(String anZhiName, int numOfAnZhi) {
        this.anZhiName = anZhiName;
        this.numOfAnZhi = numOfAnZhi;
    }

    public String getAnZhiName() {
        return anZhiName;
    }

    public void setAnZhiName(String anZhiName) {
        this.anZhiName = anZhiName;
    }

    public int getNumOfAnZhi() {
        return numOfAnZhi;
    }

    public void setNumOfAnZhi(int numOfAnZhi) {
        this.numOfAnZhi = numOfAnZhi;
    }

    @Override
    public String toString() {
        return "AnZhiKind{" +
                "anZhiName='" + anZhiName + '\'' +
                ", numOfAnZhi=" + numOfAnZhi +
                '}';
    }
}
