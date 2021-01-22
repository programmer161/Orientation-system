package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.TrategyDao;
import com.Digital_Orientation_System.service.TrategyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.Trategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TrategyService")
public class TrategyServiceImpl extends ServiceImpl<TrategyDao, Trategy> implements TrategyService {
    @Autowired
    TrategyDao trategyDao;

    @Override
    public List<Trategy> getTitle() {
        return trategyDao.getTitle();
    }

    @Override
    public List<Trategy> getValuesById(int id) {
        return trategyDao.getValuesById(id);
    }

    @Override
    public void updateTrategy(Trategy trategy) {

    }

    @Override
    public void batchDelete(List<Integer> list) {
        trategyDao.deleteBatchIds(list);
    }

    @Override
    public void addTrategy(Trategy trategy) {
        trategyDao.insert(trategy);
    }
}
