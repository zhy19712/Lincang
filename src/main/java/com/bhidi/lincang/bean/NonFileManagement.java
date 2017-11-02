package com.bhidi.lincang.bean;

public class NonFileManagement {
    private int id;
    private String nonfileid;
    private String title;
    private String formsubmitperson;
    private String infokind;
    private String content;
    private String attachmentpath;
    private String submitperson;
    private String submittime;
    private String officeperson;
    private String officecontent;
    private String officetime;
    private String result;
    private String status;

    public NonFileManagement() {
    }

    public NonFileManagement(String nonfileid, String title, String infokind, String submitperson, String status) {
        this.nonfileid = nonfileid;
        this.title = title;
        this.infokind = infokind;
        this.submitperson = submitperson;
        this.status = status;
    }

    public NonFileManagement(int id, String nonfileid, String title, String formsubmitperson, String infokind, String content, String attachmentpath, String submitperson, String submittime, String officeperson, String officecontent, String officetime, String result, String status) {
        this.id = id;
        this.nonfileid = nonfileid;
        this.title = title;
        this.formsubmitperson = formsubmitperson;
        this.infokind = infokind;
        this.content = content;
        this.attachmentpath = attachmentpath;
        this.submitperson = submitperson;
        this.submittime = submittime;
        this.officeperson = officeperson;
        this.officecontent = officecontent;
        this.officetime = officetime;
        this.result = result;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNonfileid() {
        return nonfileid;
    }

    public void setNonfileid(String nonfileid) {
        this.nonfileid = nonfileid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormsubmitperson() {
        return formsubmitperson;
    }

    public void setFormsubmitperson(String formsubmitperson) {
        this.formsubmitperson = formsubmitperson;
    }

    public String getInfokind() {
        return infokind;
    }

    public void setInfokind(String infokind) {
        this.infokind = infokind;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentpath() {
        return attachmentpath;
    }

    public void setAttachmentpath(String attachmentpath) {
        this.attachmentpath = attachmentpath;
    }

    public String getSubmitperson() {
        return submitperson;
    }

    public void setSubmitperson(String submitperson) {
        this.submitperson = submitperson;
    }

    public String getSubmittime() {
        return submittime;
    }

    public void setSubmittime(String submittime) {
        this.submittime = submittime;
    }

    public String getOfficeperson() {
        return officeperson;
    }

    public void setOfficeperson(String officeperson) {
        this.officeperson = officeperson;
    }

    public String getOfficecontent() {
        return officecontent;
    }

    public void setOfficecontent(String officecontent) {
        this.officecontent = officecontent;
    }

    public String getOfficetime() {
        return officetime;
    }

    public void setOfficetime(String officetime) {
        this.officetime = officetime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NonFileManagement{" +
                "id=" + id +
                ", nonfileid='" + nonfileid + '\'' +
                ", title='" + title + '\'' +
                ", formsubmitperson='" + formsubmitperson + '\'' +
                ", infokind='" + infokind + '\'' +
                ", content='" + content + '\'' +
                ", attachmentpath='" + attachmentpath + '\'' +
                ", submitperson='" + submitperson + '\'' +
                ", submittime='" + submittime + '\'' +
                ", officeperson='" + officeperson + '\'' +
                ", officecontent='" + officecontent + '\'' +
                ", officetime='" + officetime + '\'' +
                ", result='" + result + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
