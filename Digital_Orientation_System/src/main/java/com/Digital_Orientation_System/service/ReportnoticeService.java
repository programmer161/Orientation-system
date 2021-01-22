package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.Reportnotice;

import java.util.List;

public interface ReportnoticeService extends IService<Reportnotice> {
    List<Reportnotice> getTitle();
    List<Reportnotice> getValuesById(int id);
    void updateReportnotice(Reportnotice reportnotice);//更改学校通知
    void batchDelete(List<Integer> list);//批量删除message
    void addReportnotice(Reportnotice reportnotice);//新增学校通知
}
