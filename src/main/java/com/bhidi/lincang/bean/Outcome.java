package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/25.
 */
public class Outcome {
    private String fid;
    private String outcome_source;
    private String outcome_cate;
    private int outcome_quantity;
    private float outcome_unit;
    private float outcome_sum;
    private String remark;

    public Outcome() {

    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getOutcome_source() {
        return outcome_source;
    }

    public void setOutcome_source(String outcome_source) {
        this.outcome_source = outcome_source;
    }

    public String getOutcome_cate() {
        return outcome_cate;
    }

    public void setOutcome_cate(String outcome_cate) {
        this.outcome_cate = outcome_cate;
    }

    public int getOutcome_quantity() {
        return outcome_quantity;
    }

    public void setOutcome_quantity(int outcome_quantity) {
        this.outcome_quantity = outcome_quantity;
    }

    public float getOutcome_unit() {
        return outcome_unit;
    }

    public void setOutcome_unit(float outcome_unit) {
        this.outcome_unit = outcome_unit;
    }

    public float getOutcome_sum() {
        return outcome_sum;
    }

    public void setOutcome_sum(float outcome_sum) {
        this.outcome_sum = outcome_sum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
