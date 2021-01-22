package com.Digital_Orientation_System.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.dao.ExcepMyLogMapper;
import com.Digital_Orientation_System.entity.ExcepMyLog;
import com.Digital_Orientation_System.service.ExcepMyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ExcepMyLogService")
public class ExcepMyLogServiceImpl extends ServiceImpl<ExcepMyLogMapper, ExcepMyLog> implements ExcepMyLogService {
    @Autowired
    ExcepMyLogMapper excepMyLogMapper;

    @Override
    public List<ExcepMyLog> getExcepMylogAll() {
        return excepMyLogMapper.getExcepMylogAll();
    }

    @Override
    public void batchDelete(List<Integer> list) {
        excepMyLogMapper.deleteBatchIds(list);
    }

    @Override
    public List<ExcepMyLog> getExcepMylogByUserName(String username) {
        return excepMyLogMapper.getExcepMylogByUserName(username);
    }
}
