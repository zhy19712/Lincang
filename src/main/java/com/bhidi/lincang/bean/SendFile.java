package com.bhidi.lincang.bean;

public class SendFile {
    private int id;
    private String sendfileid;
    private String createdtime;
    private String applicant;
    private String dept;
    private String author;
    private String reviewer;
    private String print;
    private String revision;
    private String copy;
    private String attachmentpath;
    private String keyword;
    private String title;
    private String content;
    private String officeprocesstime;
    private String officeprocessperson;
    private String status;
    private String sn;
    private String date;
    private String urgency;
    private String secret;
    private String qianfa;
    private String shengao;
    private String huiqian;
    private String chaobao;
    private String chaosong;
    private String fa;
    private String approver;
    private String approverdelete;
    private String approvertime;
    private String implementperson;
    private String implementpersondelete;
    private String implementpersontime;
    private String result;
    private String confirmperson;
    private String confirmtime;

    public SendFile() {
    }

    public SendFile(String sendfileid, String title, String createdtime, String dept, String status) {
        this.sendfileid = sendfileid;
        this.title = title;
        this.createdtime = createdtime;
        this.dept = dept;
        this.status = status;
    }

    public SendFile(int id, String sendfileid, String createdtime, String applicant, String dept, String author, String reviewer, String print, String revision, String copy, String attachmentpath, String keyword, String title, String content, String officeprocesstime, String officeprocessperson, String status, String sn, String date, String urgency, String secret, String qianfa, String shengao, String huiqian, String chaobao, String chaosong, String fa, String approver, String approverdelete, String approvertime, String implementperson, String implementpersondelete, String implementpersontime, String result, String confirmperson, String confirmtime) {
        this.id = id;
        this.sendfileid = sendfileid;
        this.createdtime = createdtime;
        this.applicant = applicant;
        this.dept = dept;
        this.author = author;
        this.reviewer = reviewer;
        this.print = print;
        this.revision = revision;
        this.copy = copy;
        this.attachmentpath = attachmentpath;
        this.keyword = keyword;
        this.title = title;
        this.content = content;
        this.officeprocesstime = officeprocesstime;
        this.officeprocessperson = officeprocessperson;
        this.status = status;
        this.sn = sn;
        this.date = date;
        this.urgency = urgency;
        this.secret = secret;
        this.qianfa = qianfa;
        this.shengao = shengao;
        this.huiqian = huiqian;
        this.chaobao = chaobao;
        this.chaosong = chaosong;
        this.fa = fa;
        this.approver = approver;
        this.approverdelete = approverdelete;
        this.approvertime = approvertime;
        this.implementperson = implementperson;
        this.implementpersondelete = implementpersondelete;
        this.implementpersontime = implementpersontime;
        this.result = result;
        this.confirmperson = confirmperson;
        this.confirmtime = confirmtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendfileid() {
        return sendfileid;
    }

    public void setSendfileid(String sendfileid) {
        this.sendfileid = sendfileid;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
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

    public String getAttachmentpath() {
        return attachmentpath;
    }

    public void setAttachmentpath(String attachmentpath) {
        this.attachmentpath = attachmentpath;
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

    public String getOfficeprocesstime() {
        return officeprocesstime;
    }

    public void setOfficeprocesstime(String officeprocesstime) {
        this.officeprocesstime = officeprocesstime;
    }

    public String getOfficeprocessperson() {
        return officeprocessperson;
    }

    public void setOfficeprocessperson(String officeprocessperson) {
        this.officeprocessperson = officeprocessperson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getQianfa() {
        return qianfa;
    }

    public void setQianfa(String qianfa) {
        this.qianfa = qianfa;
    }

    public String getShengao() {
        return shengao;
    }

    public void setShengao(String shengao) {
        this.shengao = shengao;
    }

    public String getHuiqian() {
        return huiqian;
    }

    public void setHuiqian(String huiqian) {
        this.huiqian = huiqian;
    }

    public String getChaobao() {
        return chaobao;
    }

    public void setChaobao(String chaobao) {
        this.chaobao = chaobao;
    }

    public String getChaosong() {
        return chaosong;
    }

    public void setChaosong(String chaosong) {
        this.chaosong = chaosong;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproverdelete() {
        return approverdelete;
    }

    public void setApproverdelete(String approverdelete) {
        this.approverdelete = approverdelete;
    }

    public String getApprovertime() {
        return approvertime;
    }

    public void setApprovertime(String approvertime) {
        this.approvertime = approvertime;
    }

    public String getImplementperson() {
        return implementperson;
    }

    public void setImplementperson(String implementperson) {
        this.implementperson = implementperson;
    }

    public String getImplementpersondelete() {
        return implementpersondelete;
    }

    public void setImplementpersondelete(String implementpersondelete) {
        this.implementpersondelete = implementpersondelete;
    }

    public String getImplementpersontime() {
        return implementpersontime;
    }

    public void setImplementpersontime(String implementpersontime) {
        this.implementpersontime = implementpersontime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getConfirmperson() {
        return confirmperson;
    }

    public void setConfirmperson(String confirmperson) {
        this.confirmperson = confirmperson;
    }

    public String getConfirmtime() {
        return confirmtime;
    }

    public void setConfirmtime(String confirmtime) {
        this.confirmtime = confirmtime;
    }

    @Override
    public String toString() {
        return "SendFile{" +
                "id=" + id +
                ", sendfileid='" + sendfileid + '\'' +
                ", createdtime='" + createdtime + '\'' +
                ", applicant='" + applicant + '\'' +
                ", dept='" + dept + '\'' +
                ", author='" + author + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", print='" + print + '\'' +
                ", revision='" + revision + '\'' +
                ", copy='" + copy + '\'' +
                ", attachmentpath='" + attachmentpath + '\'' +
                ", keyword='" + keyword + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", officeprocesstime='" + officeprocesstime + '\'' +
                ", officeprocessperson='" + officeprocessperson + '\'' +
                ", status='" + status + '\'' +
                ", sn='" + sn + '\'' +
                ", date='" + date + '\'' +
                ", urgency='" + urgency + '\'' +
                ", secret='" + secret + '\'' +
                ", qianfa='" + qianfa + '\'' +
                ", shengao='" + shengao + '\'' +
                ", huiqian='" + huiqian + '\'' +
                ", chaobao='" + chaobao + '\'' +
                ", chaosong='" + chaosong + '\'' +
                ", fa='" + fa + '\'' +
                ", approver='" + approver + '\'' +
                ", approverdelete='" + approverdelete + '\'' +
                ", approvertime='" + approvertime + '\'' +
                ", implementperson='" + implementperson + '\'' +
                ", implementpersondelete='" + implementpersondelete + '\'' +
                ", implementpersontime='" + implementpersontime + '\'' +
                ", result='" + result + '\'' +
                ", confirmperson='" + confirmperson + '\'' +
                ", confirmtime='" + confirmtime + '\'' +
                '}';
    }
}
