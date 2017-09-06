package com.bhidi.lincang.bean;

public class F_Stuff {
    private String oid;
    private String created_at;
    private String sent_at;
    private String author;
    private String dept;
    private String reviewer;
    private String keywords;
    private String title;
    private String content;
    private String print;
    private String revision;
    private int copy;
    private String status;

    public F_Stuff() {
    }

    public F_Stuff(String oid, String created_at, String sent_at, String author, String dept, String reviewer, String keywords, String title, String content, String print, String revision, int copy, String status) {
        this.oid = oid;
        this.created_at = created_at;
        this.sent_at = sent_at;
        this.author = author;
        this.dept = dept;
        this.reviewer = reviewer;
        this.keywords = keywords;
        this.title = title;
        this.content = content;
        this.print = print;
        this.revision = revision;
        this.copy = copy;
        this.status = status;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getSent_at() {
        return sent_at;
    }

    public void setSent_at(String sent_at) {
        this.sent_at = sent_at;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrint() {
        return print;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
