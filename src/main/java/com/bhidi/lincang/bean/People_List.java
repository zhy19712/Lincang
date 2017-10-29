package com.bhidi.lincang.bean;

public class People_List {
    private String department1;
    private String department2;
    private String branch_leader;
    private String main_leader;
    private String transactor;

    public People_List() {
    }

    public People_List(String department1, String department2, String branch_leader, String main_leader, String transactor) {
        this.department1 = department1;
        this.department2 = department2;
        this.branch_leader = branch_leader;
        this.main_leader = main_leader;
        this.transactor = transactor;
    }

    public String getDepartment1() {
        return department1;
    }

    public void setDepartment1(String department1) {
        this.department1 = department1;
    }

    public String getDepartment2() {
        return department2;
    }

    public void setDepartment2(String department2) {
        this.department2 = department2;
    }

    public String getBranch_leader() {
        return branch_leader;
    }

    public void setBranch_leader(String branch_leader) {
        this.branch_leader = branch_leader;
    }

    public String getMain_leader() {
        return main_leader;
    }

    public void setMain_leader(String main_leader) {
        this.main_leader = main_leader;
    }

    public String getTransactor() {
        return transactor;
    }

    public void setTransactor(String transactor) {
        this.transactor = transactor;
    }


}
