package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/28.
 */
public class Bank {
    private String fid;
    private String account_name;
    private String bank_name;
    private String account_number;

    public Bank() {
    }

    public Bank(String fid, String account_name, String bank_name, String account_number) {
        this.fid = fid;
        this.account_name = account_name;
        this.bank_name = bank_name;
        this.account_number = account_number;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank) {
        this.bank_name = bank;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "fid='" + fid + '\'' +
                ", account_name='" + account_name + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", account_number='" + account_number + '\'' +
                '}';
    }
}
