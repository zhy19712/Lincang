package com.bhidi.lincang.bean;

import java.util.List;

public class DepartmentAndStaff {
    private String departmentName;
    private List<String> staffList;

    public DepartmentAndStaff() {
    }

    public DepartmentAndStaff(String departmentName, List<String> staffList) {
        this.departmentName = departmentName;
        this.staffList = staffList;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<String> staffList) {
        this.staffList = staffList;
    }

    @Override
    public String toString() {
        return "DepartmentAndStaff{" +
                "departmentName='" + departmentName + '\'' +
                ", staffList=" + staffList +
                '}';
    }
}
