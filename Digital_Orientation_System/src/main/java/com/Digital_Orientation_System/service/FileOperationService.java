package com.Digital_Orientation_System.service;

import com.Digital_Orientation_System.entity.SysUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

public interface FileOperationService {
    void upLoadPhoto(byte[] photo,String username);//通过用户名上传身份证照片
    void upLoadPayImg(byte[] payimg,String username);//通过用户名上传身份证照片
    void upPhoto(String photo,String username);//通过用户名上传身份证照片
    void upPayImg(String payimg,String username);//通过用户名上传身份证照片
    String getPhoto(String username);//获取身份证照片
    String getPayImg(String username);//获取缴费凭证
}
