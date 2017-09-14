package com.bhidi.lincang.bean;

public class Form_Office {
    public String SN;
    public int OID;
    public String TITLE;
    public String CREATED_AT;
    public String DEPT;
    public String AUTHOR;


    public Form_Office(int OID,String CREATED_AT,  String DEPT, String AUTHOR, String TITLE) {
        this.OID = OID;
        this.TITLE = TITLE;
        this.CREATED_AT = CREATED_AT;
        this.AUTHOR = AUTHOR;
        this.DEPT = DEPT;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
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
