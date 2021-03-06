package com.bhidi.lincang.bean;

public class RegisterInfo {
    private int id;
    private String username;
    private String pass;
    private String role;
    private String unit;
    private String department;
    private String name;
    private String phone;
    private String created_at;

    public RegisterInfo() {
    }

    public RegisterInfo(int id,String username, String role, String name, String unit, String department) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.name = name;
        this.unit = unit;
        this.department = department;
    }

    public RegisterInfo(int id, String username, String pass, String role, String unit, String department, String name, String phone, String created_at) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.role = role;
        this.unit = unit;
        this.department = department;
        this.name = name;
        this.phone = phone;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", role='" + role + '\'' +
                ", unit='" + unit + '\'' +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
