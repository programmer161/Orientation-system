package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.components.BCryptPasswordEncoderUtil;
import com.Digital_Orientation_System.components.VerCodeUtils;
import com.Digital_Orientation_System.dao.CodeDao;
import com.Digital_Orientation_System.dao.SysUserMapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class CodeServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements CodeService {
    @Autowired
    CodeDao codeDao;

    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;

    private String code;
    /**
     * 发送时间
     */
    private Date sendTime;


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public SysUserEntity findByUserName(String username) {
        return codeDao.findByUserName(username);
    }

    @Override
    public SysUserEntity findByEmail(String email) {
        return codeDao.findByEmail(email);
    }

    @Override
    public R VerCode(String sender, JavaMailSenderImpl mailSender, String receiver) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("验证码");//设置邮件标题
        code = VerCodeUtils.generateVerCode();
        sendTime = new Date();
        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");    //设置邮件正文
        message.setFrom(sender);//发件人
        message.setTo(receiver);//收件人
        mailSender.send(message);//发送邮件
        return R.ok(null);
    }

    @Override
    public void saveCode(String email, String code) {
        codeDao.saveCode(email,code);
    }

    @Override
    public SysUserEntity checkCode(String code) {
        return codeDao.checkCode(code);
    }

    @Override
    public void updatePwd(String code, String password) {
        codeDao.updatePwd(code,bCryptPasswordEncoderUtil.encode(password));
    }

    @Override
    public SysUserEntity checkPwd(String password) {
        return codeDao.checkPwd(password);
    }
}
