package com.bhidi.lincang.bean;

public class People_List_Two {
    private String department1name;
    private String department2name;
    private String department1person;
    private String department1persondelete;
    private String department2person;
    private String department2persondelete;
    private String fenguanname;
    private String fenguannamedelete;
    private String zhuguanname;
    private String zhuguannamedelete;
    private String implementperson;
    private String implementpersondelete;

    public People_List_Two() {
    }

    public People_List_Two(String department1name, String department2name, String department1person, String department1persondelete, String department2person, String department2persondelete, String fenguanname, String fenguannamedelete, String zhuguanname, String zhuguannamedelete, String implementperson, String implementpersondelete) {
        this.department1name = department1name;
        this.department2name = department2name;
        this.department1person = department1person;
        this.department1persondelete = department1persondelete;
        this.department2person = department2person;
        this.department2persondelete = department2persondelete;
        this.fenguanname = fenguanname;
        this.fenguannamedelete = fenguannamedelete;
        this.zhuguanname = zhuguanname;
        this.zhuguannamedelete = zhuguannamedelete;
        this.implementperson = implementperson;
        this.implementpersondelete = implementpersondelete;
    }

    public String getDepartment1name() {
        return department1name;
    }

    public void setDepartment1name(String department1name) {
        this.department1name = department1name;
    }

    public String getDepartment2name() {
        return department2name;
    }

    public void setDepartment2name(String department2name) {
        this.department2name = department2name;
    }

    public String getDepartment1person() {
        return department1person;
    }

    public void setDepartment1person(String department1person) {
        this.department1person = department1person;
    }

    public String getDepartment1persondelete() {
        return department1persondelete;
    }

    public void setDepartment1persondelete(String department1persondelete) {
        this.department1persondelete = department1persondelete;
    }

    public String getDepartment2person() {
        return department2person;
    }

    public void setDepartment2person(String department2person) {
        this.department2person = department2person;
    }

    public String getDepartment2persondelete() {
        return department2persondelete;
    }

    public void setDepartment2persondelete(String department2persondelete) {
        this.department2persondelete = department2persondelete;
    }

    public String getFenguanname() {
        return fenguanname;
    }

    public void setFenguanname(String fenguanname) {
        this.fenguanname = fenguanname;
    }

    public String getFenguannamedelete() {
        return fenguannamedelete;
    }

    public void setFenguannamedelete(String fenguannamedelete) {
        this.fenguannamedelete = fenguannamedelete;
    }

    public String getZhuguanname() {
        return zhuguanname;
    }

    public void setZhuguanname(String zhuguanname) {
        this.zhuguanname = zhuguanname;
    }

    public String getZhuguannamedelete() {
        return zhuguannamedelete;
    }

    public void setZhuguannamedelete(String zhuguannamedelete) {
        this.zhuguannamedelete = zhuguannamedelete;
    }

    public String getImplementperson() {
        return implementperson;
    }

    public void setImplementperson(String implementperson) {
        this.implementperson = implementperson;
    }

    public String getImplementpersondelete() {
        return implementpersondelete;
    }

    public void setImplementpersondelete(String implementpersondelete) {
        this.implementpersondelete = implementpersondelete;
    }

    @Override
    public String toString() {
        return "People_List_Two{" +
                "department1person='" + department1person + '\'' +
                ", department1persondelete='" + department1persondelete + '\'' +
                ", department2person='" + department2person + '\'' +
                ", department2persondelete='" + department2persondelete + '\'' +
                ", fenguanname='" + fenguanname + '\'' +
                ", fenguannamedelete='" + fenguannamedelete + '\'' +
                ", zhuguanname='" + zhuguanname + '\'' +
                ", zhuguannamedelete='" + zhuguannamedelete + '\'' +
                ", implementperson='" + implementperson + '\'' +
                ", implementpersondelete='" + implementpersondelete + '\'' +
                '}';
    }
}
