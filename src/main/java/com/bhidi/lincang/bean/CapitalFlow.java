package com.bhidi.lincang.bean;

public class CapitalFlow {
    private int id;
    private String capitalflowid;
    private String title;
    private String report_person;
    private String report_quarter;
    private String report_text;
    private String report_attachment;
    private String guihuakeshenqingperson;
    private String create_time;
    private String initiatorclass;
    private String status;
    private String money_source;
    private String arrival_time;
    private String amount;
    private String caiwuattachment;
    private String caiwuchuliren;
    private String finance_time;
    private String areaname;
    private String areanamedelete;
    private String text;
    private String guihuachuliren;
    private String guihuakechulitime;

    private String toarea_time;

    private String titlequxian;
    private String shenqingren;
    private String report_reason;
    private String quxianattachment;
    private String quxianshenqingren;
    private String quxianshenqingtime;


    private String replytext;
    private String dealtext;
    private String capitalflowinstruction;
    private String guihuapifuren;
    private String caiwuzhuanzhangren;
    private String quxianbaocunren;
    private String quxiantijiaoren;


    public CapitalFlow() {
    }

    public CapitalFlow(String capitalflowid, String title,String create_time,String report_person,String initiatorclass,String status) {
        this.capitalflowid = capitalflowid;
        this.title = title;
        this.create_time = create_time;
        this.report_person = report_person;
        this.initiatorclass = initiatorclass;
        this.status = status;
    }

    public CapitalFlow(int id, String capitalflowid, String title, String report_person, String report_quarter, String report_text, String report_attachment, String guihuakeshenqingperson, String create_time, String initiatorclass, String status, String money_source, String arrival_time, String amount, String caiwuattachment, String caiwuchuliren, String finance_time, String areaname, String areanamedelete, String text, String guihuachuliren, String guihuakechulitime, String toarea_time, String titlequxian, String shenqingren, String report_reason, String quxianattachment, String quxianshenqingren, String quxianshenqingtime, String replytext, String dealtext, String capitalflowinstruction, String guihuapifuren, String caiwuzhuanzhangren, String quxianbaocunren, String quxiantijiaoren) {
        this.id = id;
        this.capitalflowid = capitalflowid;
        this.title = title;
        this.report_person = report_person;
        this.report_quarter = report_quarter;
        this.report_text = report_text;
        this.report_attachment = report_attachment;
        this.guihuakeshenqingperson = guihuakeshenqingperson;
        this.create_time = create_time;
        this.initiatorclass = initiatorclass;
        this.status = status;
        this.money_source = money_source;
        this.arrival_time = arrival_time;
        this.amount = amount;
        this.caiwuattachment = caiwuattachment;
        this.caiwuchuliren = caiwuchuliren;
        this.finance_time = finance_time;
        this.areaname = areaname;
        this.areanamedelete = areanamedelete;
        this.text = text;
        this.guihuachuliren = guihuachuliren;
        this.guihuakechulitime = guihuakechulitime;
        this.toarea_time = toarea_time;
        this.titlequxian = titlequxian;
        this.shenqingren = shenqingren;
        this.report_reason = report_reason;
        this.quxianattachment = quxianattachment;
        this.quxianshenqingren = quxianshenqingren;
        this.quxianshenqingtime = quxianshenqingtime;
        this.replytext = replytext;
        this.dealtext = dealtext;
        this.capitalflowinstruction = capitalflowinstruction;
        this.guihuapifuren = guihuapifuren;
        this.caiwuzhuanzhangren = caiwuzhuanzhangren;
        this.quxianbaocunren = quxianbaocunren;
        this.quxiantijiaoren = quxiantijiaoren;
    }

    public String getGuihuakechulitime() {
        return guihuakechulitime;
    }

    public void setGuihuakechulitime(String guihuakechulitime) {
        this.guihuakechulitime = guihuakechulitime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAreanamedelete() {
        return areanamedelete;
    }

    public void setAreanamedelete(String areanamedelete) {
        this.areanamedelete = areanamedelete;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReport_reason() {
        return report_reason;
    }

    public void setReport_reason(String report_reason) {
        this.report_reason = report_reason;
    }

    public String getInitiatorclass() {
        return initiatorclass;
    }

    public void setInitiatorclass(String initiatorclass) {
        this.initiatorclass = initiatorclass;
    }

    public String getReplytext() {
        return replytext;
    }

    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }

    public String getDealtext() {
        return dealtext;
    }

    public void setDealtext(String dealtext) {
        this.dealtext = dealtext;
    }

    public String getCapitalflowinstruction() {
        return capitalflowinstruction;
    }

    public void setCapitalflowinstruction(String capitalflowinstruction) {
        this.capitalflowinstruction = capitalflowinstruction;
    }

    public String getCapitalflowid() {
        return capitalflowid;
    }

    public void setCapitalflowid(String capitalflowid) {
        this.capitalflowid = capitalflowid;
    }

    public String getReport_attachment() {
        return report_attachment;
    }

    public void setReport_attachment(String report_attachment) {
        this.report_attachment = report_attachment;
    }

    public String getGuihuakeshenqingperson() {
        return guihuakeshenqingperson;
    }

    public void setGuihuakeshenqingperson(String guihuakeshenqingperson) {
        this.guihuakeshenqingperson = guihuakeshenqingperson;
    }

    public String getQuxianshenqingren() {
        return quxianshenqingren;
    }

    public void setQuxianshenqingren(String quxianshenqingren) {
        this.quxianshenqingren = quxianshenqingren;
    }

    public String getCaiwuchuliren() {
        return caiwuchuliren;
    }

    public void setCaiwuchuliren(String caiwuchuliren) {
        this.caiwuchuliren = caiwuchuliren;
    }

    public String getGuihuachuliren() {
        return guihuachuliren;
    }

    public void setGuihuachuliren(String guihuachuliren) {
        this.guihuachuliren = guihuachuliren;
    }

    public String getGuihuapifuren() {
        return guihuapifuren;
    }

    public void setGuihuapifuren(String guihuapifuren) {
        this.guihuapifuren = guihuapifuren;
    }

    public String getCaiwuzhuanzhangren() {
        return caiwuzhuanzhangren;
    }

    public void setCaiwuzhuanzhangren(String caiwuzhuanzhangren) {
        this.caiwuzhuanzhangren = caiwuzhuanzhangren;
    }

    public String getQuxianbaocunren() {
        return quxianbaocunren;
    }

    public void setQuxianbaocunren(String quxianbaocunren) {
        this.quxianbaocunren = quxianbaocunren;
    }

    public String getQuxiantijiaoren() {
        return quxiantijiaoren;
    }

    public void setQuxiantijiaoren(String quxiantijiaoren) {
        this.quxiantijiaoren = quxiantijiaoren;
    }

    public String getCaiwuattachment() {
        return caiwuattachment;
    }

    public void setCaiwuattachment(String caiwuattachment) {
        this.caiwuattachment = caiwuattachment;
    }

    public String getTitlequxian() {
        return titlequxian;
    }

    public void setTitlequxian(String titlequxian) {
        this.titlequxian = titlequxian;
    }

    public String getShenqingren() {
        return shenqingren;
    }

    public void setShenqingren(String shenqingren) {
        this.shenqingren = shenqingren;
    }

    public String getQuxianattachment() {
        return quxianattachment;
    }

    public void setQuxianattachment(String quxianattachment) {
        this.quxianattachment = quxianattachment;
    }

    public String getQuxianshenqingtime() {
        return quxianshenqingtime;
    }

    public void setQuxianshenqingtime(String quxianshenqingtime) {
        this.quxianshenqingtime = quxianshenqingtime;
    }

    @Override
    public String toString() {
        return "CapitalFlow{" +
                "id=" + id +
                ", capitalflowid='" + capitalflowid + '\'' +
                ", title='" + title + '\'' +
                ", report_person='" + report_person + '\'' +
                ", report_quarter='" + report_quarter + '\'' +
                ", report_text='" + report_text + '\'' +
                ", report_attachment='" + report_attachment + '\'' +
                ", guihuakeshenqingperson='" + guihuakeshenqingperson + '\'' +
                ", create_time='" + create_time + '\'' +
                ", initiatorclass='" + initiatorclass + '\'' +
                ", status='" + status + '\'' +
                ", money_source='" + money_source + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", amount='" + amount + '\'' +
                ", caiwuattachment='" + caiwuattachment + '\'' +
                ", caiwuchuliren='" + caiwuchuliren + '\'' +
                ", finance_time='" + finance_time + '\'' +
                ", areaname='" + areaname + '\'' +
                ", areanamedelete='" + areanamedelete + '\'' +
                ", text='" + text + '\'' +
                ", guihuachuliren='" + guihuachuliren + '\'' +
                ", guihuakechulitime='" + guihuakechulitime + '\'' +
                ", toarea_time='" + toarea_time + '\'' +
                ", titlequxian='" + titlequxian + '\'' +
                ", shenqingren='" + shenqingren + '\'' +
                ", report_reason='" + report_reason + '\'' +
                ", quxianattachment='" + quxianattachment + '\'' +
                ", quxianshenqingren='" + quxianshenqingren + '\'' +
                ", quxianshenqingtime='" + quxianshenqingtime + '\'' +
                ", replytext='" + replytext + '\'' +
                ", dealtext='" + dealtext + '\'' +
                ", capitalflowinstruction='" + capitalflowinstruction + '\'' +
                ", guihuapifuren='" + guihuapifuren + '\'' +
                ", caiwuzhuanzhangren='" + caiwuzhuanzhangren + '\'' +
                ", quxianbaocunren='" + quxianbaocunren + '\'' +
                ", quxiantijiaoren='" + quxiantijiaoren + '\'' +
                '}';
    }
}
