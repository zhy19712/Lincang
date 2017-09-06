package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/24.
 */
public class House {
    private String fid;
    private float main_size;
    private String main_structure1;
    private String main_structure2;
    private String main_structure3;
    private String main_structure4;
    private String main_structure5;
    private String main_remark;
    private float sub_size;
    private String sub_structure1;
    private String sub_structure2;
    private String sub_structure3;
    private String sub_structure4;
    private String sub_structure5;
    private String sub_remark;

    public House(){
    }

    public House(String fid, float main_size, String main_structure1, String main_structure2, String main_structure3, String main_structure4, String main_structure5, String main_remark, float sub_size, String sub_structure1, String sub_structure2, String sub_structure3, String sub_structure4, String sub_structure5, String sub_remark) {
        this.fid = fid;
        this.main_size = main_size;
        this.main_structure1 = main_structure1;
        this.main_structure2 = main_structure2;
        this.main_structure3 = main_structure3;
        this.main_structure4 = main_structure4;
        this.main_structure5 = main_structure5;
        this.main_remark = main_remark;
        this.sub_size = sub_size;
        this.sub_structure1 = sub_structure1;
        this.sub_structure2 = sub_structure2;
        this.sub_structure3 = sub_structure3;
        this.sub_structure4 = sub_structure4;
        this.sub_structure5 = sub_structure5;
        this.sub_remark = sub_remark;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public float getMain_size() {
        return main_size;
    }

    public void setMain_size(float main_size) {
        this.main_size = main_size;
    }

    public String getMain_structure1() {
        return main_structure1;
    }

    public void setMain_structure1(String main_structure1) {
        this.main_structure1 = main_structure1;
    }

    public String getMain_structure2() {
        return main_structure2;
    }

    public void setMain_structure2(String main_structure2) {
        this.main_structure2 = main_structure2;
    }

    public String getMain_structure3() {
        return main_structure3;
    }

    public void setMain_structure3(String main_structure3) {
        this.main_structure3 = main_structure3;
    }

    public String getMain_structure4() {
        return main_structure4;
    }

    public void setMain_structure4(String main_structure4) {
        this.main_structure4 = main_structure4;
    }

    public String getMain_structure5() {
        return main_structure5;
    }

    public void setMain_structure5(String main_structure5) {
        this.main_structure5 = main_structure5;
    }

    public String getMain_remark() {
        return main_remark;
    }

    public void setMain_remark(String main_remark) {
        this.main_remark = main_remark;
    }

    public float getSub_size() {
        return sub_size;
    }

    public void setSub_size(float sub_size) {
        this.sub_size = sub_size;
    }

    public String getSub_structure1() {
        return sub_structure1;
    }

    public void setSub_structure1(String sub_structure1) {
        this.sub_structure1 = sub_structure1;
    }

    public String getSub_structure2() {
        return sub_structure2;
    }

    public void setSub_structure2(String sub_structure2) {
        this.sub_structure2 = sub_structure2;
    }

    public String getSub_structure3() {
        return sub_structure3;
    }

    public void setSub_structure3(String sub_structure3) {
        this.sub_structure3 = sub_structure3;
    }

    public String getSub_structure4() {
        return sub_structure4;
    }

    public void setSub_structure4(String sub_structure4) {
        this.sub_structure4 = sub_structure4;
    }

    public String getSub_structure5() {
        return sub_structure5;
    }

    public void setSub_structure5(String sub_structure5) {
        this.sub_structure5 = sub_structure5;
    }

    public String getSub_remark() {
        return sub_remark;
    }

    public void setSub_remark(String sub_remark) {
        this.sub_remark = sub_remark;
    }
}
