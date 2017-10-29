package com.bhidi.lincang.bean;

public class Model_Wenjianniban {
    private String filename;
    private String receivefileid;
    private String dispatchfiledepartment;
    private String filenum;
    private String receivefileregisterid;
    private String receivefiledate;
    private String filetitle;
    private String suggestion;
    private String mainleader;
    private String branchleader;
    private String mainleaderinstruction;
    private String branchleaderinstruction;
    private String result;
    private String implementperson;
    private String officeperson;

    public Model_Wenjianniban() {
    }

    public Model_Wenjianniban(String filename, String receivefileid, String dispatchfiledepartment, String filenum, String receivefileregisterid, String receivefiledate, String filetitle, String suggestion, String mainleader, String branchleader, String mainleaderinstruction, String branchleaderinstruction, String result, String implementperson, String officeperson) {
        this.filename = filename;
        this.receivefileid = receivefileid;
        this.dispatchfiledepartment = dispatchfiledepartment;
        this.filenum = filenum;
        this.receivefileregisterid = receivefileregisterid;
        this.receivefiledate = receivefiledate;
        this.filetitle = filetitle;
        this.suggestion = suggestion;
        this.mainleader = mainleader;
        this.branchleader = branchleader;
        this.mainleaderinstruction = mainleaderinstruction;
        this.branchleaderinstruction = branchleaderinstruction;
        this.result = result;
        this.implementperson = implementperson;
        this.officeperson = officeperson;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getReceivefileid() {
        return receivefileid;
    }

    public void setReceivefileid(String receivefileid) {
        this.receivefileid = receivefileid;
    }

    public String getDispatchfiledepartment() {
        return dispatchfiledepartment;
    }

    public void setDispatchfiledepartment(String dispatchfiledepartment) {
        this.dispatchfiledepartment = dispatchfiledepartment;
    }

    public String getFilenum() {
        return filenum;
    }

    public void setFilenum(String filenum) {
        this.filenum = filenum;
    }

    public String getReceivefileregisterid() {
        return receivefileregisterid;
    }

    public void setReceivefileregisterid(String receivefileregisterid) {
        this.receivefileregisterid = receivefileregisterid;
    }

    public String getReceivefiledate() {
        return receivefiledate;
    }

    public void setReceivefiledate(String receivefiledate) {
        this.receivefiledate = receivefiledate;
    }

    public String getFiletitle() {
        return filetitle;
    }

    public void setFiletitle(String filetitle) {
        this.filetitle = filetitle;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getMainleader() {
        return mainleader;
    }

    public void setMainleader(String mainleader) {
        this.mainleader = mainleader;
    }

    public String getBranchleader() {
        return branchleader;
    }

    public void setBranchleader(String branchleader) {
        this.branchleader = branchleader;
    }

    public String getMainleaderinstruction() {
        return mainleaderinstruction;
    }

    public void setMainleaderinstruction(String mainleaderinstruction) {
        this.mainleaderinstruction = mainleaderinstruction;
    }

    public String getBranchleaderinstruction() {
        return branchleaderinstruction;
    }

    public void setBranchleaderinstruction(String branchleaderinstruction) {
        this.branchleaderinstruction = branchleaderinstruction;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImplementperson() {
        return implementperson;
    }

    public void setImplementperson(String implementperson) {
        this.implementperson = implementperson;
    }

    public String getOfficeperson() {
        return officeperson;
    }

    public void setOfficeperson(String officeperson) {
        this.officeperson = officeperson;
    }

    @Override
    public String toString() {
        return "Model_Wenjianniban{" +
                "filename='" + filename + '\'' +
                ", receivefileid='" + receivefileid + '\'' +
                ", dispatchfiledepartment='" + dispatchfiledepartment + '\'' +
                ", filenum='" + filenum + '\'' +
                ", receivefileregisterid='" + receivefileregisterid + '\'' +
                ", receivefiledate='" + receivefiledate + '\'' +
                ", filetitle='" + filetitle + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", mainleader='" + mainleader + '\'' +
                ", branchleader='" + branchleader + '\'' +
                ", mainleaderinstruction='" + mainleaderinstruction + '\'' +
                ", branchleaderinstruction='" + branchleaderinstruction + '\'' +
                ", result='" + result + '\'' +
                ", implementperson='" + implementperson + '\'' +
                ", officeperson='" + officeperson + '\'' +
                '}';
    }
}
