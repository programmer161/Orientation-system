package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.Collegemajor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegemajorDao extends BaseMapper<Collegemajor> {
    @Select("select distinct  college from collegemajor")
    List<Collegemajor> getCollege();//获取所有学院
    @Select("select distinct  major from collegemajor")
    List<Collegemajor> getMajor();//获取所有专业
    @Select("select distinct  major from collegemajor where college= #{college}")
    List<Collegemajor> getMajorByCollege(String college);//查找学院对应的专业
    @Select("select distinct classes from collegemajor where major = #{major} ")
    List<Collegemajor> getClassesByMajor(String major);//查找专业对应的班级
    @Select("select * from collegemajor where college=#{college}")
    List<Collegemajor>  getByCollege(String college);//判断学院是否存在
    @Select("select * from collegemajor where major=#{major}")
    List<Collegemajor> getByMajor(String major);//判断专业是否存在
}
