package com.bhidi.lincang.bean;

public class Form_Stuff {
    public int ID;
    public String TITLE;
    public String CREATED_AT;


    public Form_Stuff(int ID, String TITLE, String CREATED_AT) {
        this.ID = ID;
        this.TITLE = TITLE;
        this.CREATED_AT = CREATED_AT;
    }

    public String getTITLE() {

        return TITLE;
    }
    public int getID() {

        return ID;
    }

    public String getCREATED_AT() {
        return CREATED_AT;
    }
}
