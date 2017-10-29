package com.bhidi.lincang.bean;

public class Model_Zhijiechuli {
    private String filename;
    private String receivefileid;
    private String receivefilenum;
    private String comefiledepartment;
    private String comefilenum;
    private String urgency;
    private String secret;
    private String copys;
    private String filetitle;
    private String suggestion;
    private String mainleader;
    private String branchleader;
    private String mainleaderinstruction;
    private String branchleaderinstruction;
    private String result;
    private String implementperson;

    public Model_Zhijiechuli() {
    }

    public Model_Zhijiechuli(String filename, String receivefileid, String receivefilenum, String comefiledepartment, String comefilenum, String urgency, String secret, String copys, String filetitle, String suggestion, String mainleader, String branchleader, String mainleaderinstruction, String branchleaderinstruction, String result, String implementperson) {
        this.filename = filename;
        this.receivefileid = receivefileid;
        this.receivefilenum = receivefilenum;
        this.comefiledepartment = comefiledepartment;
        this.comefilenum = comefilenum;
        this.urgency = urgency;
        this.secret = secret;
        this.copys = copys;
        this.filetitle = filetitle;
        this.suggestion = suggestion;
        this.mainleader = mainleader;
        this.branchleader = branchleader;
        this.mainleaderinstruction = mainleaderinstruction;
        this.branchleaderinstruction = branchleaderinstruction;
        this.result = result;
        this.implementperson = implementperson;
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

    public String getReceivefilenum() {
        return receivefilenum;
    }

    public void setReceivefilenum(String receivefilenum) {
        this.receivefilenum = receivefilenum;
    }

    public String getComefiledepartment() {
        return comefiledepartment;
    }

    public void setComefiledepartment(String comefiledepartment) {
        this.comefiledepartment = comefiledepartment;
    }

    public String getComefilenum() {
        return comefilenum;
    }

    public void setComefilenum(String comefilenum) {
        this.comefilenum = comefilenum;
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

    public String getCopys() {
        return copys;
    }

    public void setCopys(String copys) {
        this.copys = copys;
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

    @Override
    public String toString() {
        return "Model_Zhijiechuli{" +
                "filename='" + filename + '\'' +
                ", receivefileid='" + receivefileid + '\'' +
                ", receivefilenum='" + receivefilenum + '\'' +
                ", comefiledepartment='" + comefiledepartment + '\'' +
                ", comefilenum='" + comefilenum + '\'' +
                ", urgency='" + urgency + '\'' +
                ", secret='" + secret + '\'' +
                ", copys='" + copys + '\'' +
                ", filetitle='" + filetitle + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", mainleader='" + mainleader + '\'' +
                ", branchleader='" + branchleader + '\'' +
                ", mainleaderinstruction='" + mainleaderinstruction + '\'' +
                ", branchleaderinstruction='" + branchleaderinstruction + '\'' +
                ", result='" + result + '\'' +
                ", implementperson='" + implementperson + '\'' +
                '}';
    }
}
