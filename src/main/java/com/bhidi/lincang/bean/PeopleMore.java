package com.bhidi.lincang.bean;

/**
 * Created by admin on 2017/8/21.
 */
public class PeopleMore {
    //户FID
    private String fid;
    //所属水库
    private String reservoir;
    //姓名
    private String name;
    //身份证号
    private String pid;
    //户人数
    private int home_size;
    //移民人数
    private int imm_num;
    //是否建档立卡
    private int prop;
    //致贫原因
    private String poor_reason;

    //迁出地区
    private String outArea;
    //迁入地区
    private String inArea;
    //主房结构
    private String mainStructure;
    //主房面积
    private double mainSize;
    //附属房结构
    private String subStructure;
    //附属房面积
    private double subSize;
    //开户银行
    private String bank_name;
    //银行账号
    private String account_number;
    //收入来源
    private String income_source;
    //年收入
    private double income_sum;

    public PeopleMore() {
    }

    public PeopleMore( String fid, String reservoir, String name,String pid, int home_size, int imm_num, int prop, String poor_reason, String outArea, String inArea, String mainStructure, double mainSize, String subStructure, double subSize, String bank_name, String account_number, String income_source, double income_sum) {
        this.fid = fid;
        this.reservoir = reservoir;
        this.name = name;
        this.pid = pid;
        this.home_size = home_size;
        this.imm_num = imm_num;
        this.prop = prop;
        this.poor_reason = poor_reason;
        this.outArea = outArea;
        this.inArea = inArea;
        this.mainStructure = mainStructure;
        this.mainSize = mainSize;
        this.subStructure = subStructure;
        this.subSize = subSize;
        this.bank_name = bank_name;
        this.account_number = account_number;
        this.income_source = income_source;
        this.income_sum = income_sum;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getReservoir() {
        return reservoir;
    }

    public void setReservoir(String reservoir) {
        this.reservoir = reservoir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getHome_size() {
        return home_size;
    }

    public void setHome_size(int home_size) {
        this.home_size = home_size;
    }

    public int getImm_num() {
        return imm_num;
    }

    public void setImm_num(int imm_num) {
        this.imm_num = imm_num;
    }

    public int getProp() {
        return prop;
    }

    public void setProp(int prop) {
        this.prop = prop;
    }

    public String getPoor_reason() {
        return poor_reason;
    }

    public void setPoor_reason(String poor_reason) {
        this.poor_reason = poor_reason;
    }

    public String getOutArea() {
        return outArea;
    }

    public void setOutArea(String outArea) {
        this.outArea = outArea;
    }

    public String getInArea() {
        return inArea;
    }

    public void setInArea(String inArea) {
        this.inArea = inArea;
    }

    public String getMainStructure() {
        return mainStructure;
    }

    public void setMainStructure(String mainStructure) {
        this.mainStructure = mainStructure;
    }

    public double getMainSize() {
        return mainSize;
    }

    public void setMainSize(double mainSize) {
        this.mainSize = mainSize;
    }

    public String getSubStructure() {
        return subStructure;
    }

    public void setSubStructure(String subStructure) {
        this.subStructure = subStructure;
    }

    public double getSubSize() {
        return subSize;
    }

    public void setSubSize(double subSize) {
        this.subSize = subSize;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getIncome_source() {
        return income_source;
    }

    public void setIncome_source(String income_source) {
        this.income_source = income_source;
    }

    public double getIncome_sum() {
        return income_sum;
    }

    public void setIncome_sum(double income_sum) {
        this.income_sum = income_sum;
    }
}