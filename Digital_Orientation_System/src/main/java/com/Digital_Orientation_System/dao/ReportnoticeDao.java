package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.Reportnotice;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportnoticeDao extends BaseMapper<Reportnotice> {
    @Select("select id,title from reportnotice")
    List<Reportnotice> getTitle();
    @Select("select tvalues from reportnotice where id = #{id}")
    List<Reportnotice> getValuesById(int id);


}
