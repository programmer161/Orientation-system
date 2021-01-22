package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.ExcepMyLog;

import java.util.List;

public interface ExcepMyLogService extends IService<ExcepMyLog> {
    List<ExcepMyLog> getExcepMylogAll();//获取所有日志信息
    void batchDelete(List<Integer> list);//批量删除日志
    List<ExcepMyLog> getExcepMylogByUserName(String username);//通过用户名查找异常日志

}
