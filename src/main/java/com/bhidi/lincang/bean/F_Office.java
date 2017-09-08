package com.bhidi.lincang.bean;

public class F_Office {
    private int id;
    private String mid;
    private String sn;
    private String issuer;
    private String created_at;
    //提交时间
    private String modified_at;
    //缓急
    private int urgency;
    //密级
    private int secret_level;
    private String author;
    private String dept;
    private String reviewer;
    private String keyword;
    private String title;
    private String content;
    private String print;
    private String revision;
    private int copy;
    private String status;

    public F_Office() {
    }

    public F_Office(int id, String mid, String sn, String issuer, String created_at, String modified_at, int urgency, int secret_level, String author, String dept, String reviewer, String keyword, String title, String content, String print, String revision, int copy, String status) {
        this.id = id;
        this.mid = mid;
        this.sn = sn;
        this.issuer = issuer;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.urgency = urgency;
        this.secret_level = secret_level;
        this.author = author;
        this.dept = dept;
        this.reviewer = reviewer;
        this.keyword = keyword;
        this.title = title;
        this.content = content;
        this.print = print;
        this.revision = revision;
        this.copy = copy;
        this.status = status;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public int getSecret_level() {
        return secret_level;
    }

    public void setSecret_level(int secret_level) {
        this.secret_level = secret_level;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    @Override
    public String toString() {
        return "F_Office{" +
                "id=" + id +
                ", mid='" + mid + '\'' +
                ", sn='" + sn + '\'' +
                ", issuer='" + issuer + '\'' +
                ", created_at='" + created_at + '\'' +
                ", modified_at='" + modified_at + '\'' +
                ", urgency=" + urgency +
                ", secret_level=" + secret_level +
                ", author='" + author + '\'' +
                ", dept='" + dept + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", keyword='" + keyword + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", print='" + print + '\'' +
                ", revision='" + revision + '\'' +
                ", copy=" + copy +
                ", status='" + status + '\'' +
                '}';
    }
}
