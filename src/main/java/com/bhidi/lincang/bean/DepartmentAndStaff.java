package com.bhidi.lincang.bean;

public class DepartmentAndStaff {
    private String name;
    private String unit;
    private String department;

    public DepartmentAndStaff() {
    }

    public DepartmentAndStaff(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public DepartmentAndStaff(String name, String unit, String department) {
        this.name = name;
        this.unit = unit;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "DepartmentAndStaff{" +
                "name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
