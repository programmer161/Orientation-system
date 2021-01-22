package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.components.BCryptPasswordEncoderUtil;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/getpwd")
public class CodeController {
    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
    @Autowired
    CodeService codeService;
    @Autowired
    JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String sender;
//    @GetMapping("/username")
//    public R user(String username){
//        codeService.findByUserName(username);
//        return R.succ();
//    }

    @MyLog(operation = "获取验证码")
    @GetMapping("/getcode")//获取验证码
    public R getCode(@RequestParam String receiver) {
//        Logger logger = LoggerFactory.getLogger(CodeController.class);
//        logger.info("");
        SysUserEntity user1 = codeService.findByEmail(receiver);
        if(user1!=null){
            codeService.VerCode(sender, mailSender, receiver);
            codeService.saveCode(receiver, codeService.getCode());
            return R.ok("发送成功！");
        }else {
            return R.failed("发送失败！, 用户不存在");
        }
    }
    @MyLog(operation = "通过邮箱验证码修改密码")
    @PostMapping("/updatepwd")//检验验证码是否正确 以及保存密码  二次输入密码是否相同
    public R Check(@RequestParam String code,
                        @RequestParam String firstPassword,
                        @RequestParam String secondPassword){
        String str1 = firstPassword;
        String str2 = secondPassword;
        SysUserEntity user = codeService.checkCode(code);
        if(user!=null){
            if(str1.equals(str2)){

                codeService.updatePwd(code,secondPassword);
                return R.ok("修改密码成功！");
            }else {
                return R.failed("修改失败！,两次密码不相同！");
            }
        }else {
            return R.failed("修改失败！,验证码不正确!");
        }
    }
}
