package com.bhidi.lincang.bean;

public class Table_info {
    //户ID
    private String FID;
    //表格类别
    private String TABLE_TYPE;
    //姓名
    private String NAME;
    //所属水库
    private String RESERVOIR;

    private String FROM_DISTRICT;

    //调查人
    private String INTERVIEWER;

    //创建时间
    private String CREATED_AT;


    public Table_info(String FID, String TABLE_TYPE, String NAME, String RESERVOIR, String FROM_DISTRICT, String INTERVIEWER, String CREATED_AT) {
        this.FID = FID;
        this.TABLE_TYPE = TABLE_TYPE;
        this.NAME = NAME;
        this.RESERVOIR = RESERVOIR;
        this.FROM_DISTRICT = FROM_DISTRICT;
        this.INTERVIEWER = INTERVIEWER;
        this.CREATED_AT = CREATED_AT;
    }

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public String getTABLE_TYPE() {
        return TABLE_TYPE;
    }

    public void setTABLE_TYPE(String TABLE_TYPE) {
        this.TABLE_TYPE = TABLE_TYPE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getRESERVOIR() {
        return RESERVOIR;
    }

    public void setRESERVOIR(String RESERVOIR) {
        this.RESERVOIR = RESERVOIR;
    }

    public String getFROM_DISTRICT() {
        return FROM_DISTRICT;
    }

    public void setFROM_DISTRICT(String FROM_DISTRICT) {
        this.FROM_DISTRICT = FROM_DISTRICT;
    }

    public String getINTERVIEWER() {
        return INTERVIEWER;
    }

    public void setINTERVIEWER(String INTERVIEWER) {
        this.INTERVIEWER = INTERVIEWER;
    }

    public String getCREATED_AT() {
        return CREATED_AT;
    }

    public void setCREATED_AT(String CREATED_AT) {
        this.CREATED_AT = CREATED_AT;
    }

    @Override
    public String toString() {
        return "Table_info{" +
                "FID='" + FID + '\'' +
                ", TABLE_TYPE='" + TABLE_TYPE + '\'' +
                ", NAME='" + NAME + '\'' +
                ", RESERVOIR='" + RESERVOIR + '\'' +
                ", FROM_DISTRICT='" + FROM_DISTRICT + '\'' +
                ", INTERVIEWER='" + INTERVIEWER + '\'' +
                ", CREATED_AT='" + CREATED_AT + '\'' +
                '}';
    }
}
