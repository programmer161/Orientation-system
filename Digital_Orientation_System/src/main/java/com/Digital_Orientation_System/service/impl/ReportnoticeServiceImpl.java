package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.ReportnoticeDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.Reportnotice;
import com.Digital_Orientation_System.service.ReportnoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ReportnoticeService")
public class ReportnoticeServiceImpl extends ServiceImpl<ReportnoticeDao, Reportnotice> implements ReportnoticeService {
    @Autowired
    ReportnoticeDao reportnoticeDao;
    @Override
    public List<Reportnotice> getTitle() {
        return reportnoticeDao.getTitle();
    }

    @Override
    public List<Reportnotice> getValuesById(int id) {
        return reportnoticeDao.getValuesById(id);
    }

    @Override
    public void updateReportnotice(Reportnotice reportnotice) {

    }

    @Override
    public void batchDelete(List<Integer> list) {
        reportnoticeDao.deleteBatchIds(list);
    }

    @Override
    public void addReportnotice(Reportnotice reportnotice) {
        reportnoticeDao.insert(reportnotice);
    }
}
