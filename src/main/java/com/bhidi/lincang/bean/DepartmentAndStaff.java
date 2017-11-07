package com.bhidi.lincang.bean;

public class DepartmentAndStaff {
    private String name;
    private String department;

    public DepartmentAndStaff() {
    }

    public DepartmentAndStaff(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "DepartmentAndStaff{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
