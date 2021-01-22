package com.Digital_Orientation_System.dao;

import com.Digital_Orientation_System.entity.Dorm;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DormDao {
    @Select("select distinct number_apartments from dorm")
    List<String> getApartMents();//获取公寓楼号
    @Select("select distinct number_dorm from dorm where number_apartments = #{apartments}")
    List<String> getDormByApartMents(String apartments);//通过公寓楼号获取对应的宿舍
    @Select("select distinct number_apartments from dorm where number_apartments=#{apartments}")
    String getByApartMents(String apartments);//判断公寓是否存在
}
