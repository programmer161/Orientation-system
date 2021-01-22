package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.Trategy;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrategyDao extends BaseMapper<Trategy> {

    @Select("select id,title from trategy")
    List<Trategy> getTitle();
    @Select("select tvalues from trategy where id = #{id}")
    List<Trategy> getValuesById(int id);


}
