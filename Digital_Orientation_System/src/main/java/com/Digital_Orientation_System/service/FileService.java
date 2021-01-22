package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.SysUserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService extends IService<SysUserEntity> {
    R uploadPay(String username,MultipartFile file);//上传文件 上传缴费凭证
    R uploadPhoto(String username,MultipartFile file);//上传人像
    String download(String username) ;//获取文件路径
    SysUserEntity findByUserName(String username);//通过用户名查找用户
}
