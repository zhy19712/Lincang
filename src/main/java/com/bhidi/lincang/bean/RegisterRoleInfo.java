package com.bhidi.lincang.bean;

import java.util.List;

public class RegisterRoleInfo {
    private int roleid;
    private String role;
    private List<String> functionList;

    public RegisterRoleInfo() {
    }

    public RegisterRoleInfo(int roleid, String role, List<String> functionList) {
        this.roleid = roleid;
        this.role = role;
        this.functionList = functionList;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<String> functionList) {
        this.functionList = functionList;
    }

    @Override
    public String toString() {
        return "RegisterRoleInfo{" +
                "roleid=" + roleid +
                ", role='" + role + '\'' +
                ", functionList=" + functionList +
                '}';
    }
}
