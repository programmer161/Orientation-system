package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.AdminLog;

import java.util.List;

public interface MyLogService extends IService<AdminLog> {
    List<AdminLog> getMylogAll();//获取所有日志信息
    void batchDelete(List<Integer> list);//批量删除日志
    List<AdminLog> getMylogByUserName(String username);//通过用户名查找日志

}
