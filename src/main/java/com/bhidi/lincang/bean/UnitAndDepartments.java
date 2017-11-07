package com.bhidi.lincang.bean;

import java.util.List;

public class UnitAndDepartments {
    private String unit;
    private List<String> departmentList;

    public UnitAndDepartments() {
    }

    public UnitAndDepartments(String unit, List<String> departmentList) {
        this.unit = unit;
        this.departmentList = departmentList;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<String> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public String toString() {
        return "UnitAndDepartments{" +
                "unit='" + unit + '\'' +
                ", departmentList=" + departmentList +
                '}';
    }
}
