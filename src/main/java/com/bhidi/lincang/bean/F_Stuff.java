package com.bhidi.lincang.bean;

public class F_Stuff {
    private int id;
    private String oid;
    private String created_at;
    private String sent_at;
    private String author;
    private String dept;
    private String reviewer;
    private String keyword;
    private String title;
    private String content;
    private String print;
    private String revision;
    private String copy;
    private String status;

    public F_Stuff() {
    }

    public F_Stuff(int id, String oid, String created_at, String sent_at, String author, String dept, String reviewer, String keyword, String title, String content, String print, String revision, String copy, String status) {

        this.id = id;
        this.oid = oid;
        this.created_at = created_at;
        this.sent_at = sent_at;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
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
        return "F_Stuff{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", created_at='" + created_at + '\'' +
                ", sent_at='" + sent_at + '\'' +
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
