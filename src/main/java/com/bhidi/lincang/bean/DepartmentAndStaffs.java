package com.bhidi.lincang.bean;

import java.util.List;

public class DepartmentAndStaffs {
    private String department;
    private List<String> staffList;

    public DepartmentAndStaffs() {
    }

    public DepartmentAndStaffs(String department, List<String> staffList) {
        this.department = department;
        this.staffList = staffList;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<String> staffList) {
        this.staffList = staffList;
    }

    @Override
    public String toString() {
        return "DepartmentAndStaffs{" +
                "department='" + department + '\'' +
                ", staffList=" + staffList +
                '}';
    }
}
