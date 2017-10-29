package com.bhidi.lincang.bean;

public class ModelText {
    private String departmentadvice;
    private String department1advice;
    private String department2advice;
    private String branchleaderinstruction;
    private String mainleaderinstruction;
    private String result;

    public ModelText() {
    }

    public ModelText(String departmentadvice, String department1advice, String department2advice, String branchleaderinstruction, String mainleaderinstruction, String result) {
        this.departmentadvice = departmentadvice;
        this.department1advice = department1advice;
        this.department2advice = department2advice;
        this.branchleaderinstruction = branchleaderinstruction;
        this.mainleaderinstruction = mainleaderinstruction;
        this.result = result;
    }

    public String getDepartmentadvice() {
        return departmentadvice;
    }

    public void setDepartmentadvice(String departmentadvice) {
        this.departmentadvice = departmentadvice;
    }

    public String getDepartment1advice() {
        return department1advice;
    }

    public void setDepartment1advice(String department1advice) {
        this.department1advice = department1advice;
    }

    public String getDepartment2advice() {
        return department2advice;
    }

    public void setDepartment2advice(String department2advice) {
        this.department2advice = department2advice;
    }

    public String getBranchleaderinstruction() {
        return branchleaderinstruction;
    }

    public void setBranchleaderinstruction(String branchleaderinstruction) {
        this.branchleaderinstruction = branchleaderinstruction;
    }

    public String getMainleaderinstruction() {
        return mainleaderinstruction;
    }

    public void setMainleaderinstruction(String mainleaderinstruction) {
        this.mainleaderinstruction = mainleaderinstruction;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ModelText{" +
                "departmentadvice='" + departmentadvice + '\'' +
                ", department1advice='" + department1advice + '\'' +
                ", department2advice='" + department2advice + '\'' +
                ", branchleaderinstruction='" + branchleaderinstruction + '\'' +
                ", mainleaderinstruction='" + mainleaderinstruction + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
