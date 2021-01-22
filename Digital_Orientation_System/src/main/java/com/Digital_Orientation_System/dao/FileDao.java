package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.SysUserEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao extends BaseMapper<SysUserEntity> {
    @Update("update sys_user_table set payimg = #{url} where user_name=#{username}")
    void uploadPay(String username,String url);//保存文件路径到数据库
    @Update("update sys_user_table set photo = #{url} where user_name=#{username} ")
    void uploadPhoto(String username,String url);//保存文件路径到数据库
    @Select("select payimg from sys_user_table where user_name = #{username}")
    String downloadFile(String username);//获取文件路径
    @Select("select * from sys_user_table where user_name = #{username}")
    SysUserEntity findByUserName(String username);//通过用户名查找用户
}
