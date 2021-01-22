package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
//    @RequestMapping("/upload")//上传文件
//    public void upload(@RequestParam("file")MultipartFile file) throws Exception{
//        String filePath = file.;
//        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
//        outputStream.write(file.getBytes());
//        outputStream.flush();
//        outputStream.close();
//    }
    @Autowired
    FileService fileService;
    @MyLog(operation = "上传学费支付凭证")
    @RequestMapping("/uploadpay")//上传学费支付凭证
    public R uploadPay(@RequestParam("file")MultipartFile file,@RequestParam String username){
        return fileService.uploadPay(username,file);
    }
    @MyLog(operation = "上传身份证照片")
    @RequestMapping("/uploadphoto")//上传人像
    public R uploadPhoto(@RequestParam("file")MultipartFile file,@RequestParam String username){
        return fileService.uploadPhoto(username,file);
    }
    @MyLog(operation = "获取图片路径")
    @PostMapping("/geturl")//获取图片路径
    public R download(@RequestParam String username){
        SysUserEntity user = fileService.findByUserName(username);
        if(user!=null){
            return R.ok(fileService.download(username));
        }else{
            return R.failed("操作失败！");
        }
    }

}
