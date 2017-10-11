package com.bhidi.lincang.bean;

public class Form_Approver {
    public String SN;
    public int RID;
    public String TITLE;
    public String CREATED_AT;
    public String DEPT;
    public String AUTHOR;


    public Form_Approver(int RID, String CREATED_AT, String DEPT, String AUTHOR, String TITLE) {
        this.RID = RID;
        this.TITLE = TITLE;
        this.CREATED_AT = CREATED_AT;
        this.AUTHOR = AUTHOR;
        this.DEPT = DEPT;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getTITLE() {

        return TITLE;
    }
    public String getSN() {

        return SN;
    }

    public String getCREATED_AT() {
        return CREATED_AT;
    }
    public String getAUTHOR() {
        return AUTHOR;
    }
    public String getDEPT() {
        return DEPT;
    }
}
