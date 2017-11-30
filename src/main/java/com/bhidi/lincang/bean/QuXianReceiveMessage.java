package com.bhidi.lincang.bean;

public class QuXianReceiveMessage {
    private String capitalflowid;
    private String guihuachuliren;
    private String guihuakechulitime;
    private String quxianpeople;
    private String status;

    public QuXianReceiveMessage() {
    }

    public QuXianReceiveMessage(String capitalflowid, String guihuachuliren, String guihuakechulitime, String quxianpeople, String status) {
        this.capitalflowid = capitalflowid;
        this.guihuachuliren = guihuachuliren;
        this.guihuakechulitime = guihuakechulitime;
        this.quxianpeople = quxianpeople;
        this.status = status;
    }

    public QuXianReceiveMessage(String capitalflowid, String guihuachuliren, String guihuakechulitime, String status) {
        this.capitalflowid = capitalflowid;
        this.guihuachuliren = guihuachuliren;
        this.guihuakechulitime = guihuakechulitime;
        this.status = status;
    }

    public String getCapitalflowid() {
        return capitalflowid;
    }

    public void setCapitalflowid(String capitalflowid) {
        this.capitalflowid = capitalflowid;
    }

    public String getGuihuachuliren() {
        return guihuachuliren;
    }

    public void setGuihuachuliren(String guihuachuliren) {
        this.guihuachuliren = guihuachuliren;
    }

    public String getGuihuakechulitime() {
        return guihuakechulitime;
    }

    public void setGuihuakechulitime(String guihuakechulitime) {
        this.guihuakechulitime = guihuakechulitime;
    }

    public String getQuxianpeople() {
        return quxianpeople;
    }

    public void setQuxianpeople(String quxianpeople) {
        this.quxianpeople = quxianpeople;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QuXianReceiveMessage{" +
                "capitalflowid='" + capitalflowid + '\'' +
                ", guihuachuliren='" + guihuachuliren + '\'' +
                ", guihuakechulitime='" + guihuakechulitime + '\'' +
                ", quxianpeople='" + quxianpeople + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
