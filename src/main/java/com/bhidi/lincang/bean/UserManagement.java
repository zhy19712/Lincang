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
