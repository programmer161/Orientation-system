package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.Paynotice;

import java.util.List;

public interface PaynoticeService extends IService<Paynotice> {
    List<Paynotice> getTitle();

    List<Paynotice> getValuesById(int id);

    void updatePaynotice(Paynotice paynotice);//更改学校通知
    void batchDelete(List<Integer> list);//批量删除message
    void addPaynotice(Paynotice paynotice);//新增学校通知
}
