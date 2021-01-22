package com.Digital_Orientation_System.entity.Vo;

import lombok.Data;

@Data
public class StudentVo {
    private int finishedcount;//报到完成人数
    //    private int unfinished;//未完成报到人数
    private int sum;//总用户数
    private int infocount;//确认信息人数
    private int reportnowcount;//完成现场报到人数
    private int reportinfocount;//完成报到信息人数
    private int affaircount;//完成事务处理人事
    private int paycount;//完成学费支付人数
    private int agecount;//每个年龄阶段的人数
    private int age;
    private int Mc;//男生人数
    private int FMc;//女生人数

//    public int getSage() {
//        return age;
//    }
//
//    public void setSage(int sage) {
//        this.age = sage;
//    }
//
//    public int getAgecount() {
//        return agecount;
//    }
//
//    public void setAgecount(int agecount) {
//        this.agecount = agecount;
//    }
//
//    public int getFinishedcount() {
//        return finishedcount;
//    }
//
//    public void setFinishedcount(int finishedcount) {
//        this.finishedcount = finishedcount;
//    }
//
//    public int getSum() {
//        return sum;
//    }
//
//    public void setSum(int sum) {
//        this.sum = sum;
//    }
//
//    public int getInfocount() {
//        return infocount;
//    }
//
//    public void setInfocount(int infocount) {
//        this.infocount = infocount;
//    }
//
//    public int getReportnowcount() {
//        return reportnowcount;
//    }
//
//    public void setReportnowcount(int reportnowcount) {
//        this.reportnowcount = reportnowcount;
//    }
//
//    public int getReportinfocount() {
//        return reportinfocount;
//    }
//
//    public void setReportinfocount(int reportinfocount) {
//        this.reportinfocount = reportinfocount;
//    }
//
//    public int getAffaircount() {
//        return affaircount;
//    }
//
//    public void setAffaircount(int affaircount) {
//        this.affaircount = affaircount;
//    }
//
//    public int getPaycount() {
//        return paycount;
//    }
//
//    public void setPaycount(int paycount) {
//        this.paycount = paycount;
//    }
}