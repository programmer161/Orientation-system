package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.ExcepMyLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExcepMyLogMapper extends BaseMapper<ExcepMyLog> {
    @Insert("insert into excepmylog (user_id,name,user_name,loginip,type,url,operation,createtime,remark,method_name,params,emessage,ename) values (#{userId},#{name},#{userName},#{loginip},#{type},#{url},#{operation},now(),#{remark},#{methodName},#{params},#{emessage},#{ename})")
    int insert(ExcepMyLog excepMyLog);
    @Select("select * from excepmylog")
    List<ExcepMyLog> getExcepMylogAll();//获取所有日志信息
    @Select("select * from excepmylog where user_name=#{username}")
    List<ExcepMyLog> getExcepMylogByUserName(String username);//通过用户名查找异常日志
}
