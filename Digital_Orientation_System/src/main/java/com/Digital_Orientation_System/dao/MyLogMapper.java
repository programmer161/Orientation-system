package com.Digital_Orientation_System.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.AdminLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by Administrator on 2019/8/6.
 */
@Repository
public interface MyLogMapper extends BaseMapper<AdminLog> {
    @Insert("insert into mylog (user_id,name,user_name,loginip,type,url,operation,createtime,remark,method_name,params,result) values (#{userId},#{name},#{userName},#{loginip},#{type},#{url},#{operation},now(),#{remark},#{methodName},#{params},#{result})")
    int insert(AdminLog adminLog);
    @Select("select * from mylog")
    List<AdminLog> getMylogAll();//获取所有日志信息
    @Select("select * from mylog where user_name=#{username}")
    List<AdminLog> getMylogByUserName(String username);//通过用户名查找日志

}
