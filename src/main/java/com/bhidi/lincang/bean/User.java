package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/28.
 */
public class User {
    private String uid;
    private String username;
    private String password;
    private String name;
    private String dept;
    private String phone1;
    private String phone2;
    private int level;
    private String created_at;

    public User() {
    }

    public User(String uid, String username, String password, String name, String dept, String phone1, String phone2, int level, String created_at) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.dept = dept;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.level = level;
        this.created_at = created_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
