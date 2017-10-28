package com.bhidi.lincang.bean;

public class ReceiveFile {
    private int id;
    private String receivefileid;
    private String year;
    private String savetime;
    private String type;
    private String cometime;
    private String fileid;
    private String registrationnum;
    private String fileallid;
    private String writtentime;
    private String title;
    private String attachmentpath;
    private String keyword;
    private String responsibleperson;
    private String archivecopies;
    private String pagenum;
    private String secret;
    private String issues;
    private String receiveperson;
    private String comedepartment;
    private String attachmentpagenum;
    private String entitynum;
    private String distributionsituation;
    private String oldfond;
    private String archivesituation;
    private String registrationdate;
    private String circulationsituation;
    private String dealsituation;
    private String status;

    private String modeltype;
    private String reveivereregisterpersonname;
    private String modelchoicename;
    private String department1name;
    private String department2name;
    private String fenguanname;
    private String zhuguanname;
    private String implementperson;
    private String department1namedelete;
    private String department2namedelete;
    private String fenguannamedelete;
    private String zhuguannamedelete;
    private String implementpersondelete;

    public ReceiveFile() {
    }

    public ReceiveFile( String year, String type, String cometime,String receivefileid, String title, String status) {
        this.year = year;
        this.type = type;
        this.cometime = cometime;
        this.receivefileid = receivefileid;
        this.title = title;
        this.status = status;
    }

    public ReceiveFile(int id, String receivefileid, String year, String savetime, String type, String cometime, String fileid, String registrationnum, String fileallid, String writtentime, String title, String attachmentpath, String keyword, String responsibleperson, String archivecopies, String pagenum, String secret, String issues, String receiveperson, String comedepartment, String attachmentpagenum, String entitynum, String distributionsituation, String oldfond, String archivesituation, String registrationdate, String circulationsituation, String dealsituation, String status, String modeltype, String reveivereregisterpersonname, String modelchoicename, String department1name, String department2name, String fenguanname, String zhuguanname, String implementperson, String department1namedelete, String department2namedelete, String fenguannamedelete, String zhuguannamedelete, String implementpersondelete) {
        this.id = id;
        this.receivefileid = receivefileid;
        this.year = year;
        this.savetime = savetime;
        this.type = type;
        this.cometime = cometime;
        this.fileid = fileid;
        this.registrationnum = registrationnum;
        this.fileallid = fileallid;
        this.writtentime = writtentime;
        this.title = title;
        this.attachmentpath = attachmentpath;
        this.keyword = keyword;
        this.responsibleperson = responsibleperson;
        this.archivecopies = archivecopies;
        this.pagenum = pagenum;
        this.secret = secret;
        this.issues = issues;
        this.receiveperson = receiveperson;
        this.comedepartment = comedepartment;
        this.attachmentpagenum = attachmentpagenum;
        this.entitynum = entitynum;
        this.distributionsituation = distributionsituation;
        this.oldfond = oldfond;
        this.archivesituation = archivesituation;
        this.registrationdate = registrationdate;
        this.circulationsituation = circulationsituation;
        this.dealsituation = dealsituation;
        this.status = status;
        this.modeltype = modeltype;
        this.reveivereregisterpersonname = reveivereregisterpersonname;
        this.modelchoicename = modelchoicename;
        this.department1name = department1name;
        this.department2name = department2name;
        this.fenguanname = fenguanname;
        this.zhuguanname = zhuguanname;
        this.implementperson = implementperson;
        this.department1namedelete = department1namedelete;
        this.department2namedelete = department2namedelete;
        this.fenguannamedelete = fenguannamedelete;
        this.zhuguannamedelete = zhuguannamedelete;
        this.implementpersondelete = implementpersondelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceivefileid() {
        return receivefileid;
    }

    public void setReceivefileid(String receivefileid) {
        this.receivefileid = receivefileid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSavetime() {
        return savetime;
    }

    public void setSavetime(String savetime) {
        this.savetime = savetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCometime() {
        return cometime;
    }

    public void setCometime(String cometime) {
        this.cometime = cometime;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getRegistrationnum() {
        return registrationnum;
    }

    public void setRegistrationnum(String registrationnum) {
        this.registrationnum = registrationnum;
    }

    public String getFileallid() {
        return fileallid;
    }

    public void setFileallid(String fileallid) {
        this.fileallid = fileallid;
    }

    public String getWrittentime() {
        return writtentime;
    }

    public void setWrittentime(String writtentime) {
        this.writtentime = writtentime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getResponsibleperson() {
        return responsibleperson;
    }

    public void setResponsibleperson(String responsibleperson) {
        this.responsibleperson = responsibleperson;
    }

    public String getArchivecopies() {
        return archivecopies;
    }

    public void setArchivecopies(String archivecopies) {
        this.archivecopies = archivecopies;
    }

    public String getPagenum() {
        return pagenum;
    }

    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public String getReceiveperson() {
        return receiveperson;
    }

    public void setReceiveperson(String receiveperson) {
        this.receiveperson = receiveperson;
    }

    public String getComedepartment() {
        return comedepartment;
    }

    public void setComedepartment(String comedepartment) {
        this.comedepartment = comedepartment;
    }

    public String getAttachmentpagenum() {
        return attachmentpagenum;
    }

    public void setAttachmentpagenum(String attachmentpagenum) {
        this.attachmentpagenum = attachmentpagenum;
    }

    public String getEntitynum() {
        return entitynum;
    }

    public void setEntitynum(String entitynum) {
        this.entitynum = entitynum;
    }

    public String getDistributionsituation() {
        return distributionsituation;
    }

    public void setDistributionsituation(String distributionsituation) {
        this.distributionsituation = distributionsituation;
    }

    public String getOldfond() {
        return oldfond;
    }

    public void setOldfond(String oldfond) {
        this.oldfond = oldfond;
    }

    public String getArchivesituation() {
        return archivesituation;
    }

    public void setArchivesituation(String archivesituation) {
        this.archivesituation = archivesituation;
    }

    public String getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(String registrationdate) {
        this.registrationdate = registrationdate;
    }

    public String getCirculationsituation() {
        return circulationsituation;
    }

    public void setCirculationsituation(String circulationsituation) {
        this.circulationsituation = circulationsituation;
    }

    public String getDealsituation() {
        return dealsituation;
    }

    public void setDealsituation(String dealsituation) {
        this.dealsituation = dealsituation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModeltype() {
        return modeltype;
    }

    public void setModeltype(String modeltype) {
        this.modeltype = modeltype;
    }

    public String getReveivereregisterpersonname() {
        return reveivereregisterpersonname;
    }

    public void setReveivereregisterpersonname(String reveivereregisterpersonname) {
        this.reveivereregisterpersonname = reveivereregisterpersonname;
    }

    public String getModelchoicename() {
        return modelchoicename;
    }

    public void setModelchoicename(String modelchoicename) {
        this.modelchoicename = modelchoicename;
    }

    public String getDepartment1name() {
        return department1name;
    }

    public void setDepartment1name(String department1name) {
        this.department1name = department1name;
    }

    public String getDepartment2name() {
        return department2name;
    }

    public void setDepartment2name(String department2name) {
        this.department2name = department2name;
    }

    public String getFenguanname() {
        return fenguanname;
    }

    public void setFenguanname(String fenguanname) {
        this.fenguanname = fenguanname;
    }

    public String getZhuguanname() {
        return zhuguanname;
    }

    public void setZhuguanname(String zhuguanname) {
        this.zhuguanname = zhuguanname;
    }

    public String getImplementperson() {
        return implementperson;
    }

    public void setImplementperson(String implementperson) {
        this.implementperson = implementperson;
    }

    public String getDepartment1namedelete() {
        return department1namedelete;
    }

    public void setDepartment1namedelete(String department1namedelete) {
        this.department1namedelete = department1namedelete;
    }

    public String getDepartment2namedelete() {
        return department2namedelete;
    }

    public void setDepartment2namedelete(String department2namedelete) {
        this.department2namedelete = department2namedelete;
    }

    public String getFenguannamedelete() {
        return fenguannamedelete;
    }

    public void setFenguannamedelete(String fenguannamedelete) {
        this.fenguannamedelete = fenguannamedelete;
    }

    public String getZhuguannamedelete() {
        return zhuguannamedelete;
    }

    public void setZhuguannamedelete(String zhuguannamedelete) {
        this.zhuguannamedelete = zhuguannamedelete;
    }

    public String getImplementpersondelete() {
        return implementpersondelete;
    }

    public void setImplementpersondelete(String implementpersondelete) {
        this.implementpersondelete = implementpersondelete;
    }

    @Override
    public String toString() {
        return "ReceiveFile{" +
                "id=" + id +
                ", receivefileid='" + receivefileid + '\'' +
                ", year='" + year + '\'' +
                ", savetime='" + savetime + '\'' +
                ", type='" + type + '\'' +
                ", cometime='" + cometime + '\'' +
                ", fileid='" + fileid + '\'' +
                ", registrationnum='" + registrationnum + '\'' +
                ", fileallid='" + fileallid + '\'' +
                ", writtentime='" + writtentime + '\'' +
                ", title='" + title + '\'' +
                ", attachmentpath='" + attachmentpath + '\'' +
                ", keyword='" + keyword + '\'' +
                ", responsibleperson='" + responsibleperson + '\'' +
                ", archivecopies='" + archivecopies + '\'' +
                ", pagenum='" + pagenum + '\'' +
                ", secret='" + secret + '\'' +
                ", issues='" + issues + '\'' +
                ", receiveperson='" + receiveperson + '\'' +
                ", comedepartment='" + comedepartment + '\'' +
                ", attachmentpagenum='" + attachmentpagenum + '\'' +
                ", entitynum='" + entitynum + '\'' +
                ", distributionsituation='" + distributionsituation + '\'' +
                ", oldfond='" + oldfond + '\'' +
                ", archivesituation='" + archivesituation + '\'' +
                ", registrationdate='" + registrationdate + '\'' +
                ", circulationsituation='" + circulationsituation + '\'' +
                ", dealsituation='" + dealsituation + '\'' +
                ", status='" + status + '\'' +
                ", modeltype='" + modeltype + '\'' +
                ", reveivereregisterpersonname='" + reveivereregisterpersonname + '\'' +
                ", modelchoicename='" + modelchoicename + '\'' +
                ", department1name='" + department1name + '\'' +
                ", department2name='" + department2name + '\'' +
                ", fenguanname='" + fenguanname + '\'' +
                ", zhuguanname='" + zhuguanname + '\'' +
                ", implementperson='" + implementperson + '\'' +
                ", department1namedelete='" + department1namedelete + '\'' +
                ", department2namedelete='" + department2namedelete + '\'' +
                ", fenguannamedelete='" + fenguannamedelete + '\'' +
                ", zhuguannamedelete='" + zhuguannamedelete + '\'' +
                ", implementpersondelete='" + implementpersondelete + '\'' +
                '}';
    }
}
