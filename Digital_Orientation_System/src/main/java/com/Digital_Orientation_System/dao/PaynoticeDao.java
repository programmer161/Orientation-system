package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.Paynotice;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaynoticeDao extends BaseMapper<Paynotice> {

    @Select("select id,title from paynotice")
    List<Paynotice> getTitle();
    @Select("select tvalues from paynotice where id = #{id}")
    List<Paynotice> getValuesById(int id);

}
