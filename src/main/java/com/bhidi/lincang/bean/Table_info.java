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

    private String TO_DISTRICT;

    //调查人
    private String INTERVIEWER;

    //创建时间
    private String CREATED_AT;


    public Table_info(String FID, String TABLE_TYPE, String NAME, String RESERVOIR, String TO_DISTRICT, String INTERVIEWER, String CREATED_AT) {
        this.FID = FID;
        this.TABLE_TYPE = TABLE_TYPE;
        this.NAME = NAME;
        this.RESERVOIR = RESERVOIR;
        this.TO_DISTRICT = TO_DISTRICT;
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

    public String getTO_DISTRICT() {
        return TO_DISTRICT;
    }

    public void setTO_DISTRICT(String TO_DISTRICT) {
        this.TO_DISTRICT = TO_DISTRICT;
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
                ", TO_DISTRICT='" + TO_DISTRICT + '\'' +
                ", INTERVIEWER='" + INTERVIEWER + '\'' +
                ", CREATED_AT='" + CREATED_AT + '\'' +
                '}';
    }
}
