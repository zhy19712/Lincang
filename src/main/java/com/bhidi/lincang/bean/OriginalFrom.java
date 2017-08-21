package com.bhidi.lincang.bean;

public class OriginalFrom {
    public String OID;
    public String TITLE;
    public String CREATED_AT;


    public OriginalFrom(String OID, String TITLE, String CREATED_AT) {
        this.OID = OID;
        this.TITLE = TITLE;
        this.CREATED_AT = CREATED_AT;
    }

    public String getTITLE() {

        return TITLE;
    }
    public String getOID() {

        return OID;
    }

    public String getCREATED_AT() {
        return CREATED_AT;
    }
}
