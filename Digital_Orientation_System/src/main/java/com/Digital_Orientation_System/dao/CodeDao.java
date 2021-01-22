package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.SysUserEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDao extends BaseMapper<SysUserEntity> {
    @Select("select * from sys_user_table where user_name = #{username}")
    SysUserEntity findByUserName(String username);
    @Select("select * from sys_user_table where email = #{email}")
    SysUserEntity findByEmail(String email);
    @Update("update sys_user_table set code = #{code} where email = #{email}")
    void saveCode(String email, String code);//保存验证码在数据库
    @Select("select * from sys_user_table where code = #{code}")
    SysUserEntity checkCode(String code);//判断验证码是否正确
    @Update("update sys_user_table set pass_word=#{password} where code = #{code}")
    void updatePwd(String code, String password);//更改密码
    @Select("select * from sys_user_table where password = #{password}")
    SysUserEntity checkPwd(String password);//确认密码是否输入正确
}
