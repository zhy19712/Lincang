package com.bhidi.lincang.bean;

public class FamilyAllInfo {


    private String NAME;
    private String GENDER;
    private String RACE;
    private String PHONE;

    public FamilyAllInfo() {
    }

    public FamilyAllInfo(String NAME, String GENDER, String RACE, String PHONE) {
        this.NAME = NAME;
        this.GENDER = GENDER;
        this.RACE = RACE;
        this.PHONE = PHONE;
    }


    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getRACE() {
        return RACE;
    }

    public void setRACE(String RACE) {
        this.RACE = RACE;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    @Override
    public String toString() {
        return "FamilyAllInfo{" +
                ", NAME='" + NAME + '\'' +
                ", GENDER='" + GENDER + '\'' +
                ", RACE='" + RACE + '\'' +
                ", PHONE='" + PHONE + '\'' +
                '}';
    }
}
