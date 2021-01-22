package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.MyLogMapper;
import com.Digital_Orientation_System.service.MyLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.AdminLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MyLogService")
public class MyLogServiceImpl extends ServiceImpl<MyLogMapper, AdminLog> implements MyLogService {
    @Autowired
    MyLogMapper myLogMapper;
    @Override
    public List<AdminLog> getMylogAll() {
        return myLogMapper.getMylogAll();
    }

    @Override
    public void batchDelete(List<Integer> list) {
        myLogMapper.deleteBatchIds(list);
    }

    @Override
    public List<AdminLog> getMylogByUserName(String username) {
        return myLogMapper.getMylogByUserName(username);
    }
}
