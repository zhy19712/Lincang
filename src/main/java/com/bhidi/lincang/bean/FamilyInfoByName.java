package com.bhidi.lincang.bean;

public class FamilyInfoByName {

    private String FID;
    private String NAME;
    private int PROP;
    private int HOME_SIZE;
    private int IMM_NUM;
    private String RESERVOIR;

    public FamilyInfoByName() {
    }

    public FamilyInfoByName(String FID, String NAME, int PROP, int HOME_SIZE, int IMM_NUM, String RESERVOIR) {
        this.FID = FID;
        this.NAME = NAME;
        this.PROP = PROP;
        this.HOME_SIZE = HOME_SIZE;
        this.IMM_NUM = IMM_NUM;
        this.RESERVOIR = RESERVOIR;
    }

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getPROP() {
        return PROP;
    }

    public void setPROP(int PROP) {
        this.PROP = PROP;
    }

    public int getHOME_SIZE() {
        return HOME_SIZE;
    }

    public void setHOME_SIZE(int HOME_SIZE) {
        this.HOME_SIZE = HOME_SIZE;
    }

    public int getIMM_NUM() {
        return IMM_NUM;
    }

    public void setIMM_NUM(int IMM_NUM) {
        this.IMM_NUM = IMM_NUM;
    }

    public String getRESERVOIR() {
        return RESERVOIR;
    }

    public void setRESERVOIR(String RESERVOIR) {
        this.RESERVOIR = RESERVOIR;
    }
}
