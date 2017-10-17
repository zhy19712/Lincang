package com.bhidi.lincang.bean;

public class CapitalFlow {
    private int id;
    private String create_time;
    private String report_person;
    private String report_quarter;
    private String report_text;
    private String report_failname;
    private String status;

    public CapitalFlow() {
    }

    public CapitalFlow(int id, String create_time, String report_person, String report_quarter, String report_text, String report_failname, String status) {
        this.id = id;
        this.create_time = create_time;
        this.report_person = report_person;
        this.report_quarter = report_quarter;
        this.report_text = report_text;
        this.report_failname = report_failname;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getReport_person() {
        return report_person;
    }

    public void setReport_person(String report_person) {
        this.report_person = report_person;
    }

    public String getReport_quarter() {
        return report_quarter;
    }

    public void setReport_quarter(String report_quarter) {
        this.report_quarter = report_quarter;
    }

    public String getReport_text() {
        return report_text;
    }

    public void setReport_text(String report_text) {
        this.report_text = report_text;
    }

    public String getReport_failname() {
        return report_failname;
    }

    public void setReport_failname(String report_failname) {
        this.report_failname = report_failname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CapitalFlow{" +
                "id=" + id +
                ", create_time='" + create_time + '\'' +
                ", report_person='" + report_person + '\'' +
                ", report_quarter='" + report_quarter + '\'' +
                ", report_text='" + report_text + '\'' +
                ", report_failname='" + report_failname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
