package com.bhidi.lincang.bean;

public class UnitAndDepartment {
    private int id;
    private String unit;
    private String department;

    public UnitAndDepartment() {
    }

    public UnitAndDepartment(int id, String unit, String department) {
        this.id = id;
        this.unit = unit;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "UnitAndDepartment{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
