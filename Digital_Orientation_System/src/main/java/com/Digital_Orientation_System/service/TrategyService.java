package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.Trategy;

import java.util.List;

public interface TrategyService extends IService<Trategy> {
    List<Trategy> getTitle();
    List<Trategy> getValuesById(int id);
    void updateTrategy(Trategy trategy);//更改学校通知
    void batchDelete(List<Integer> list);//批量删除message
    void addTrategy(Trategy trategy);//新增学校通知
}
