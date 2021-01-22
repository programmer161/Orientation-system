package com.Digital_Orientation_System.dao;

import com.Digital_Orientation_System.entity.SysUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FileOperationDao {

    @Insert("update sys_user_table set photo = #{photo} where user_name = #{username}")
    void upLoadPhoto(byte[] photo,String username);//通过用户名上传身份证照片
    @Insert("update sys_user_table set payimg = #{payimg} where user_name = #{username}")
    void upLoadPayImg(byte[] payimg,String username);//通过用户名上传身份证照片
    @Insert("update sys_user_table set photo = #{photo} where user_name = #{username}")
    void upPhoto(String photo,String username);//通过用户名上传身份证照片
    @Insert("update sys_user_table set payimg = #{payimg} where user_name = #{username}")
    void upPayImg(String payimg,String username);//通过用户名上传身份证照片
    @Select("select photo from sys_user_table where user_name=#{username}")
    String getPhoto(String username);//获取身份证照片
    @Select("select payimg from sys_user_table where user_name=#{username}")
    String getPayImg(String username);//获取缴费凭证
}
