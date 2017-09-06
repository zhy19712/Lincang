package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/25.
 */
public class Income {
    private String fid;
    private String income_source;
    private String income_cate;
    private int income_quantity;
    private float income_unit;
    private float income_sum;
    private String remark;

    public Income() {

    }

    public Income(String fid, String income_source, String income_cate, int income_quantity, float income_unit, float income_sum, String remark) {
        this.fid = fid;
        this.income_source = income_source;
        this.income_cate = income_cate;
        this.income_quantity = income_quantity;
        this.income_unit = income_unit;
        this.income_sum = income_sum;
        this.remark = remark;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getIncome_source() {
        return income_source;
    }

    public void setIncome_source(String income_source) {
        this.income_source = income_source;
    }

    public String getIncome_cate() {
        return income_cate;
    }

    public void setIncome_cate(String income_cate) {
        this.income_cate = income_cate;
    }

    public int getIncome_quantity() {
        return income_quantity;
    }

    public void setIncome_quantity(int income_quantity) {
        this.income_quantity = income_quantity;
    }

    public float getIncome_unit() {
        return income_unit;
    }

    public void setIncome_unit(float income_unit) {
        this.income_unit = income_unit;
    }

    public float getIncome_sum() {
        return income_sum;
    }

    public void setIncome_sum(float income_sum) {
        this.income_sum = income_sum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
