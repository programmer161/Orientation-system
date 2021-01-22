package com.Digital_Orientation_System.entity;

import com.Digital_Orientation_System.components.BCryptPasswordEncoderUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@TableName("sys_user_table")
public class SysUserEntity {
//    @Autowired
//    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
    @TableId(type = IdType.UUID)
    String userId;
    String userName;
    String passWord;
    String name;//姓名
    Integer state=1;
    String description;
    String phonenumber;
    String idnumber;
    String sex;
    String college;
    String nation;
    String birth;
    String politics;
    String age;
    String major;
    String grade;
    String classes;
    String drom;
    String xuezhi;
    String studenttype;
    String studystate;
    String instructname;
    String instructphone;
    String photo;
    String email;
    String info;
    String studytype;
    String reportnow;
    String reportinfo;
    String affair;
    String pay;
    String arrivetime;
    String traffic;
    String entourage;
    String other;
    String paytype;
    String payimg;
    String code;


    @Override
    public String toString() {
        return "SysUserEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", state=" + state +
                ", description='" + description + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", nation='" + nation + '\'' +
                ", birth='" + birth + '\'' +
                ", politics='" + politics + '\'' +
                ", age='" + age + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", classes='" + classes + '\'' +
                ", drom='" + drom + '\'' +
                ", xuezhi='" + xuezhi + '\'' +
                ", studenttype='" + studenttype + '\'' +
                ", studystate='" + studystate + '\'' +
                ", instructname='" + instructname + '\'' +
                ", instructphone='" + instructphone + '\'' +
                ", photo='" + photo + '\'' +
                ", email='" + email + '\'' +
                ", info='" + info + '\'' +
                ", reportnow='" + reportnow + '\'' +
                ", reportinfo='" + reportinfo + '\'' +
                ", affair='" + affair + '\'' +
                ", pay='" + pay + '\'' +
                ", arrivetime='" + arrivetime + '\'' +
                ", traffic='" + traffic + '\'' +
                ", entourage='" + entourage + '\'' +
                ", other='" + other + '\'' +
                ", paytype='" + paytype + '\'' +
                ", payimg='" + payimg + '\'' +
                '}';
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReportnow() {
        return reportnow;
    }

    public void setReportnow(String reportnow) {
        this.reportnow = reportnow;
    }

    public String getReportinfo() {
        return reportinfo;
    }

    public void setReportinfo(String reportinfo) {
        this.reportinfo = reportinfo;
    }

    public String getAffair() {
        return affair;
    }

    public void setAffair(String affair) {
        this.affair = affair;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getEntourage() {
        return entourage;
    }

    public void setEntourage(String entourage) {
        this.entourage = entourage;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPayimg() {
        return payimg;
    }

    public void setPayimg(String payimg) {
        this.payimg = payimg;
    }

    public SysUserEntity(String userId, String userName, String passWord, Integer state, String description, String phonenumber, String idnumber, String sex, String college, String nation, String birth, String politics, String age, String major, String grade, String classes, String drom, String xuezhi, String studenttype, String studystate, String instructname, String instructphone, String photo, String email, String info, String reportnow, String reportinfo, String affair, String pay, String arrivetime, String traffic, String entourage, String other, String paytype, String payimg) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.state = state;
        this.description = description;
        this.phonenumber = phonenumber;
        this.idnumber = idnumber;
        this.sex = sex;
        this.college = college;
        this.nation = nation;
        this.birth = birth;
        this.politics = politics;
        this.age = age;
        this.major = major;
        this.grade = grade;
        this.classes = classes;
        this.drom = drom;
        this.xuezhi = xuezhi;
        this.studenttype = studenttype;
        this.studystate = studystate;
        this.instructname = instructname;
        this.instructphone = instructphone;
        this.photo = photo;
        this.email = email;
        this.info = info;
        this.reportnow = reportnow;
        this.reportinfo = reportinfo;
        this.affair = affair;
        this.pay = pay;
        this.arrivetime = arrivetime;
        this.traffic = traffic;
        this.entourage = entourage;
        this.other = other;
        this.paytype = paytype;
        this.payimg = payimg;
    }

    public SysUserEntity() {
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //    public SysUserEntity(String userId, String userName, String passWord, Integer state, String description) {
//        this.userId = userId;
//        this.userName = userName;
//        this.passWord = passWord;
//        this.state = state;
//        this.description = description;
//    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getDrom() {
        return drom;
    }

    public void setDrom(String drom) {
        this.drom = drom;
    }

    public String getXuezhi() {
        return xuezhi;
    }

    public void setXuezhi(String xuezhi) {
        this.xuezhi = xuezhi;
    }

    public String getStudenttype() {
        return studenttype;
    }

    public void setStudenttype(String studenttype) {
        this.studenttype = studenttype;
    }

    public String getStudystate() {
        return studystate;
    }

    public void setStudystate(String studystate) {
        this.studystate = studystate;
    }

    public String getInstructname() {
        return instructname;
    }

    public void setInstructname(String instructname) {
        this.instructname = instructname;
    }

    public String getInstructphone() {
        return instructphone;
    }

    public void setInstructphone(String instructphone) {
        this.instructphone = instructphone;
    }
}
