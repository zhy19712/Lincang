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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Income income = (Income) o;

        if (income_quantity != income.income_quantity) return false;
        if (Float.compare(income.income_unit, income_unit) != 0) return false;
        if (Float.compare(income.income_sum, income_sum) != 0) return false;
        if (fid != null ? !fid.equals(income.fid) : income.fid != null) return false;
        if (income_source != null ? !income_source.equals(income.income_source) : income.income_source != null)
            return false;
        if (income_cate != null ? !income_cate.equals(income.income_cate) : income.income_cate != null) return false;
        return remark != null ? remark.equals(income.remark) : income.remark == null;
    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (income_source != null ? income_source.hashCode() : 0);
        result = 31 * result + (income_cate != null ? income_cate.hashCode() : 0);
        result = 31 * result + income_quantity;
        result = 31 * result + (income_unit != +0.0f ? Float.floatToIntBits(income_unit) : 0);
        result = 31 * result + (income_sum != +0.0f ? Float.floatToIntBits(income_sum) : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
