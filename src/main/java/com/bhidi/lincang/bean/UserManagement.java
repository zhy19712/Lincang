package com.bhidi.lincang.bean;

public class UserManagement {
    //账号
    private String username;
    //密码
    private String pass;
    //姓名
    private String name;
    //部门
    private String rolename;
    //联系电话
    private String phone1;

    public UserManagement() {
    }

    public UserManagement(String username, String pass, String name, String rolename, String phone1) {
        this.username = username;
        this.pass = pass;
        this.name = name;
        this.rolename = rolename;
        this.phone1 = phone1;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    @Override
    public String toString() {
        return "UserManagement{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", rolename='" + rolename + '\'' +
                ", phone1='" + phone1 + '\'' +
                '}';
    }
}
