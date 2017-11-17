package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/25.
 */
public class Outcome {
    private String fid;
    private String outcome_source;
    private String outcome_cate;
    private String outcome_quantity;
    private String outcome_unit;
    private String outcome_sum;
    private String remark;

    public Outcome() {

    }

    public Outcome(String fid, String outcome_source, String outcome_cate, String outcome_quantity, String outcome_unit, String outcome_sum, String remark) {
        this.fid = fid;
        this.outcome_source = outcome_source;
        this.outcome_cate = outcome_cate;
        this.outcome_quantity = outcome_quantity;
        this.outcome_unit = outcome_unit;
        this.outcome_sum = outcome_sum;
        this.remark = remark;
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

    public String getOutcome_quantity() {
        return outcome_quantity;
    }

    public void setOutcome_quantity(String outcome_quantity) {
        this.outcome_quantity = outcome_quantity;
    }

    public String getOutcome_unit() {
        return outcome_unit;
    }

    public void setOutcome_unit(String outcome_unit) {
        this.outcome_unit = outcome_unit;
    }

    public String getOutcome_sum() {
        return outcome_sum;
    }

    public void setOutcome_sum(String outcome_sum) {
        this.outcome_sum = outcome_sum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Outcome{" +
                "fid='" + fid + '\'' +
                ", outcome_source='" + outcome_source + '\'' +
                ", outcome_cate='" + outcome_cate + '\'' +
                ", outcome_quantity=" + outcome_quantity +
                ", outcome_unit=" + outcome_unit +
                ", outcome_sum=" + outcome_sum +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Outcome outcome = (Outcome) o;

        if (fid != null ? !fid.equals(outcome.fid) : outcome.fid != null) return false;
        if (outcome_source != null ? !outcome_source.equals(outcome.outcome_source) : outcome.outcome_source != null)
            return false;
        if (outcome_cate != null ? !outcome_cate.equals(outcome.outcome_cate) : outcome.outcome_cate != null)
            return false;
        if (outcome_quantity != null ? !outcome_quantity.equals(outcome.outcome_quantity) : outcome.outcome_quantity != null)
            return false;
        if (outcome_unit != null ? !outcome_unit.equals(outcome.outcome_unit) : outcome.outcome_unit != null)
            return false;
        if (outcome_sum != null ? !outcome_sum.equals(outcome.outcome_sum) : outcome.outcome_sum != null) return false;
        return remark != null ? remark.equals(outcome.remark) : outcome.remark == null;
    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (outcome_source != null ? outcome_source.hashCode() : 0);
        result = 31 * result + (outcome_cate != null ? outcome_cate.hashCode() : 0);
        result = 31 * result + (outcome_quantity != null ? outcome_quantity.hashCode() : 0);
        result = 31 * result + (outcome_unit != null ? outcome_unit.hashCode() : 0);
        result = 31 * result + (outcome_sum != null ? outcome_sum.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
