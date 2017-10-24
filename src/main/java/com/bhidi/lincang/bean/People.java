package com.bhidi.lincang.bean;

import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/8/21.
 */
public class People {
    private int id;
    //户ID
    private String fid;
    //所属水库
    private String reservoir;
    //安置点
    private String location;
    //姓名
    private String name;
    //是否是户主的标志
    private int master;
    //身份证号
    private String pid;
    //性别
    private String gender;
    //民族
    private String race;
    //联系电话
    private String phone;
    //与户主关系
    private String relation;
    //文化程度
    private String education;
    //现从事职业
    private String profession;
    //户人数
    private int home_size;
    //移民人数
    private int imm_num;

    //是否建档立卡
    private int prop;
    //致贫原因
    private String poor_reason;
    //调查人
    private String interviewer;
    //被调查人
    private String interviewee;
    //创建时间
    private String created_at;
    //表格类别
    private String table_type;

    public People() {
    }

    public People(String fid,String table_type, String reservoir, String location, String name, String interviewer, String created_at) {
        this.fid = fid;
        this.table_type = table_type;
        this.reservoir = reservoir;
        this.location = location;
        this.name = name;
        this.interviewer = interviewer;
        this.created_at = created_at;
    }

    public People(int id, String fid, String reservoir, String location, String name, int master, String pid, String gender, String race, String phone, String relation, String education, String profession, int home_size, int imm_num, int prop, String poor_reason, String interviewer, String interviewee, String created_at, String table_type) {
        this.id = id;
        this.fid = fid;
        this.reservoir = reservoir;
        this.location = location;
        this.name = name;
        this.master = master;
        this.pid = pid;
        this.gender = gender;
        this.race = race;
        this.phone = phone;
        this.relation = relation;
        this.education = education;
        this.profession = profession;
        this.home_size = home_size;
        this.imm_num = imm_num;
        this.prop = prop;
        this.poor_reason = poor_reason;
        this.interviewer = interviewer;
        this.interviewee = interviewee;
        this.created_at = created_at;
        this.table_type = table_type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setMaster(int master) {
        this.master = master;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHome_size(int home_size) {
        this.home_size = home_size;
    }

    public void setImm_num(int imm_num) {
        this.imm_num = imm_num;
    }

    public void setProp(int prop) {
        this.prop = prop;
    }

    public void setPoor_reason(String poor_reason) {
        this.poor_reason = poor_reason;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setReservoir(String reservoir) {
        this.reservoir = reservoir;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setInterviewer(String interviewer) { this.interviewer = interviewer;}

    public void setInterviewee(String interviewee) { this.interviewee = interviewee;}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() { return id;}

    public String getFid() {
        return fid;
    }

    public String getPid() {
        return pid;
    }

    public int getMaster() {
        return master;
    }

    public String getName() {
        return name;
    }

    public int getHome_size() {
        return home_size;
    }

    public int getImm_num() {
        return imm_num;
    }

    public int getProp() {
        return prop;
    }

    public String getPoor_reason() {
        return poor_reason;
    }

    public String getLocation() {
        return location;
    }

    public String getReservoir() {
        return reservoir;
    }

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public String getRelation() {
        return relation;
    }

    public String getEducation() {
        return education;
    }

    public String getProfession() {
        return profession;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public String getInterviewee() {
        return interviewee;
    }

    public  String getCreated_at() {
        return created_at;
    }public void   setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getTable_type() {
        return table_type;
    }

    public void setTable_type(String table_type) {
        this.table_type = table_type;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", fid='" + fid + '\'' +
                ", reservoir='" + reservoir + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", master=" + master +
                ", pid='" + pid + '\'' +
                ", gender='" + gender + '\'' +
                ", race='" + race + '\'' +
                ", phone='" + phone + '\'' +
                ", relation='" + relation + '\'' +
                ", education='" + education + '\'' +
                ", profession='" + profession + '\'' +
                ", home_size=" + home_size +
                ", imm_num=" + imm_num +
                ", prop=" + prop +
                ", poor_reason='" + poor_reason + '\'' +
                ", interviewer='" + interviewer + '\'' +
                ", interviewee='" + interviewee + '\'' +
                ", created_at='" + created_at + '\'' +
                ", table_type='" + table_type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        People people = (People) o;

        if (id != people.id) return false;
        if (master != people.master) return false;
        if (home_size != people.home_size) return false;
        if (imm_num != people.imm_num) return false;
        if (prop != people.prop) return false;
        if (fid != null ? !fid.equals(people.fid) : people.fid != null) return false;
        if (reservoir != null ? !reservoir.equals(people.reservoir) : people.reservoir != null) return false;
        if (location != null ? !location.equals(people.location) : people.location != null) return false;
        if (name != null ? !name.equals(people.name) : people.name != null) return false;
        if (pid != null ? !pid.equals(people.pid) : people.pid != null) return false;
        if (gender != null ? !gender.equals(people.gender) : people.gender != null) return false;
        if (race != null ? !race.equals(people.race) : people.race != null) return false;
        if (phone != null ? !phone.equals(people.phone) : people.phone != null) return false;
        if (relation != null ? !relation.equals(people.relation) : people.relation != null) return false;
        if (education != null ? !education.equals(people.education) : people.education != null) return false;
        if (profession != null ? !profession.equals(people.profession) : people.profession != null) return false;
        if (poor_reason != null ? !poor_reason.equals(people.poor_reason) : people.poor_reason != null) return false;
        if (interviewer != null ? !interviewer.equals(people.interviewer) : people.interviewer != null) return false;
        if (interviewee != null ? !interviewee.equals(people.interviewee) : people.interviewee != null) return false;
        if (created_at != null ? !created_at.equals(people.created_at) : people.created_at != null) return false;
        return table_type != null ? table_type.equals(people.table_type) : people.table_type == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (reservoir != null ? reservoir.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + master;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        result = 31 * result + home_size;
        result = 31 * result + imm_num;
        result = 31 * result + prop;
        result = 31 * result + (poor_reason != null ? poor_reason.hashCode() : 0);
        result = 31 * result + (interviewer != null ? interviewer.hashCode() : 0);
        result = 31 * result + (interviewee != null ? interviewee.hashCode() : 0);
        result = 31 * result + (created_at != null ? created_at.hashCode() : 0);
        result = 31 * result + (table_type != null ? table_type.hashCode() : 0);
        return result;
    }
}
