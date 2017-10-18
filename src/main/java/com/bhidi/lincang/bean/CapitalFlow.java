package com.bhidi.lincang.bean;

public class CapitalFlow {
    private int id;
    private String create_time;
    private String report_person;
    private String report_quarter;
    private String report_text;
    private String report_failname;
    private String status;
    private String money_source;
    private String arrival_time;
    private String amount;
    private String finance_time;
    private String toarea_time;
    private String areaname;
    private String text;
    private String initiatorclass;

    public CapitalFlow() {
    }

    public CapitalFlow(int id, String create_time, String report_person,String status) {
        this.id = id;
        this.create_time = create_time;
        this.report_person = report_person;
        this.status = status;
    }

    public CapitalFlow(int id, String create_time, String report_person, String report_quarter, String report_text, String report_failname, String status, String money_source, String arrival_time, String amount, String finance_time, String toarea_time, String areaname, String text, String initiatorclass) {
        this.id = id;
        this.create_time = create_time;
        this.report_person = report_person;
        this.report_quarter = report_quarter;
        this.report_text = report_text;
        this.report_failname = report_failname;
        this.status = status;
        this.money_source = money_source;
        this.arrival_time = arrival_time;
        this.amount = amount;
        this.finance_time = finance_time;
        this.toarea_time = toarea_time;
        this.areaname = areaname;
        this.text = text;
        this.initiatorclass = initiatorclass;
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

    public String getMoney_source() {
        return money_source;
    }

    public void setMoney_source(String money_source) {
        this.money_source = money_source;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFinance_time() {
        return finance_time;
    }

    public void setFinance_time(String finance_time) {
        this.finance_time = finance_time;
    }

    public String getToarea_time() {
        return toarea_time;
    }

    public void setToarea_time(String toarea_time) {
        this.toarea_time = toarea_time;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInitiatorclass() {
        return initiatorclass;
    }

    public void setInitiatorclass(String initiatorclass) {
        this.initiatorclass = initiatorclass;
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
                ", money_source='" + money_source + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", amount='" + amount + '\'' +
                ", finance_time='" + finance_time + '\'' +
                ", toarea_time='" + toarea_time + '\'' +
                ", areaname='" + areaname + '\'' +
                ", text='" + text + '\'' +
                ", initiatorclass='" + initiatorclass + '\'' +
                '}';
    }
}
