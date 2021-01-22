package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.Message;

import java.util.List;

public interface MessageService extends IService<Message> {

    List<Message> getTitle();

    List<Message> getValuesById(int id);

    void updateMessage(Message message);//更改学校通知
    void batchDelete(List<Integer> list);//批量删除message
    void addMessage(Message message);//新增学校通知
}
