package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.Message;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageDao extends BaseMapper<Message> {

    @Select("select id,title from message")
    List<Message> getTitle();
    @Select("select tvalues from message where id = #{id}")
    List<Message> getValuesById(int id);
//    @Update("update message set title = #{title},tvalues = #{tvalues} where id = #{id}")
//    void updateMessage(Message message);//更改学校通知

}
