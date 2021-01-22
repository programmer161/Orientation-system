package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.MessageDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.Message;
import com.Digital_Orientation_System.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MessageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Override
    public List<Message> getTitle() {
        return messageDao.getTitle();
    }

    @Override
    public List<Message> getValuesById(int id) {
        return messageDao.getValuesById(id);
    }

    @Override
    public void updateMessage(Message message) {

    }

    @Override
    public void batchDelete(List<Integer> list) {
        messageDao.deleteBatchIds(list);
    }

    @Override
    public void addMessage(Message message) {
        messageDao.insert(message);
    }
}
