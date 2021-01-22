package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.SysUserEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public interface CodeService extends IService<SysUserEntity> {
    String getCode();

    SysUserEntity findByUserName(String username);
    SysUserEntity findByEmail(String email);
    /**
     * 发送验证码到指定邮箱
     * @param sender 发送地址
     * @param mailSender spring自带
     * @param receiver 接受地址
     */

    R VerCode(String sender, JavaMailSenderImpl mailSender, String receiver);
    void saveCode(String email, String code);//保存验证码在数据库
    SysUserEntity checkCode( String code);//判断验证码是否正确
    void updatePwd(String code, String password);//更改密码
    SysUserEntity checkPwd(String password);//确认密码是否输入正确
}
