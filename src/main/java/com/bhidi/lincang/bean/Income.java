package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/25.
 */
public class Income {
    private String fid;
    private String income_source;
    private String income_cate;
    private String income_quantity;
    private String income_unit;
    private String income_sum;
    private String remark;

    public Income() {

    }

    public Income(String fid, String income_source, String income_cate, String income_quantity, String income_unit, String income_sum, String remark) {
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

    public String getIncome_quantity() {
        return income_quantity;
    }

    public void setIncome_quantity(String income_quantity) {
        this.income_quantity = income_quantity;
    }

    public String getIncome_unit() {
        return income_unit;
    }

    public void setIncome_unit(String income_unit) {
        this.income_unit = income_unit;
    }

    public String getIncome_sum() {
        return income_sum;
    }

    public void setIncome_sum(String income_sum) {
        this.income_sum = income_sum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Income{" +
                "fid='" + fid + '\'' +
                ", income_source='" + income_source + '\'' +
                ", income_cate='" + income_cate + '\'' +
                ", income_quantity=" + income_quantity +
                ", income_unit=" + income_unit +
                ", income_sum=" + income_sum +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Income income = (Income) o;

        if (fid != null ? !fid.equals(income.fid) : income.fid != null) return false;
        if (income_source != null ? !income_source.equals(income.income_source) : income.income_source != null)
            return false;
        if (income_cate != null ? !income_cate.equals(income.income_cate) : income.income_cate != null) return false;
        if (income_quantity != null ? !income_quantity.equals(income.income_quantity) : income.income_quantity != null)
            return false;
        if (income_unit != null ? !income_unit.equals(income.income_unit) : income.income_unit != null) return false;
        if (income_sum != null ? !income_sum.equals(income.income_sum) : income.income_sum != null) return false;
        return remark != null ? remark.equals(income.remark) : income.remark == null;
    }

    @Override
    public int hashCode() {
        int result = fid != null ? fid.hashCode() : 0;
        result = 31 * result + (income_source != null ? income_source.hashCode() : 0);
        result = 31 * result + (income_cate != null ? income_cate.hashCode() : 0);
        result = 31 * result + (income_quantity != null ? income_quantity.hashCode() : 0);
        result = 31 * result + (income_unit != null ? income_unit.hashCode() : 0);
        result = 31 * result + (income_sum != null ? income_sum.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
