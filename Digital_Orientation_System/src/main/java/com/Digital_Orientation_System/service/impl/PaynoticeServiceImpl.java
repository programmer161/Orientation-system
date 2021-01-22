package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.PaynoticeDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.Paynotice;
import com.Digital_Orientation_System.service.PaynoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PaynoticeService")
public class PaynoticeServiceImpl extends ServiceImpl<PaynoticeDao, Paynotice> implements PaynoticeService {

    @Autowired
    PaynoticeDao paynoticeDao;
    @Override
    public List<Paynotice> getTitle() {
        return paynoticeDao.getTitle();
    }

    @Override
    public List<Paynotice> getValuesById(int id) {
        return paynoticeDao.getValuesById(id);
    }

    @Override
    public void updatePaynotice(Paynotice paynotice) {

    }

    @Override
    public void batchDelete(List<Integer> list) {
        paynoticeDao.deleteBatchIds(list);
    }

    @Override
    public void addPaynotice(Paynotice paynotice) {
        paynoticeDao.insert(paynotice);
    }
}
