package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.dao.SysUserMapper;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.log.MyLog;
import com.Digital_Orientation_System.service.FileOperationService;
import com.Digital_Orientation_System.service.SysUserService;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/fileoperation")
public class FileOperationController {
//    @Value("${file.path}")
//    private String filePath;
    @Autowired
    FileOperationService fileOperationService;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserService sysUserService;

//    @MyLog(operation = "上传身份证照片")
//    @PostMapping("/uploadphoto")
//    public R upLoadPhoto(@RequestParam("file")MultipartFile file, @RequestParam String username)throws IOException {
//        SysUserEntity user = sysUserMapper.getUserByUsername(username);
////        String OriginalFilename = file.getOriginalFilename();//文件名
////        String fileName = OriginalFilename;//获取主文件名和文件扩展名
//        if(user!=null){
//            if(file==null){
//                return R.failed("文件为空！");
//            }else{
//                SysUserEntity user1 = new SysUserEntity();
//                byte[] photo = file.getBytes();
//                fileOperationService.upLoadPhoto(photo, username);
//                return R.ok("上传成功！");
//            }
//        }else {
//            return R.failed("用户不存在！");
//        }
//    }
//    @MyLog(operation = "获取身份证照片")
//    @PostMapping("/downphoto")
//    public R downLoadPhoto(@RequestParam String username)throws IOException {
//        SysUserEntity user = sysUserMapper.getUserByUsername(username);
//        if(user!=null){
//            String fileName = username + "_" +sysUserMapper.getNameByUserName(username);//文件名由  学号_姓名  组成
//            fileName = fileName+".jpg";//文件名加后缀
//            byte[] photo = user.getPhoto();
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(photo);
//            String filePath = "D:\\Images\\PhotoImg\\";//存放文件路径
////            Files.copy(inputStream, Paths.get(filePath+fileName));
//            File dest = new File(filePath+fileName);
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();//如果文件不存在就创建一个文件
//            }
//            try {
//                Files.copy(inputStream, Paths.get(filePath+fileName));
//            } catch (Exception e) {
//                e.printStackTrace();
//                return R.failed(fileName+"获取失败！文件已存在！");
//            }
//            return R.ok("获取成功！文件存放在:"+filePath);
//        }else{
//            return R.failed("用户不存在");
//        }
//    }
//    @MyLog(operation = "上传缴费凭证")
//    @PostMapping("/uploadpayimg")
//    public R upLoadPayImg(@RequestParam("file")MultipartFile file, @RequestParam String username)throws IOException {
//        SysUserEntity user = sysUserMapper.getUserByUsername(username);
////        String OriginalFilename = file.getOriginalFilename();//文件名
////        String fileName = OriginalFilename;//获取主文件名和文件扩展名
//        if(user!=null){
//            if(file==null){
//                return R.failed("文件为空！");
//            }else{
//                SysUserEntity user1 = new SysUserEntity();
//                byte[] photo = file.getBytes();
//                fileOperationService.upLoadPayImg(photo, username);
//                return R.ok("上传成功！");
//            }
//        }else {
//            return R.failed("用户不存在！");
//        }
//    }
//    @MyLog(operation = "获取缴费凭证")
//    @PostMapping("/downpayimg")
//    public R downLoadPayImg(@RequestParam String username)throws IOException {
//        SysUserEntity user = sysUserMapper.getUserByUsername(username);
//        if(user!=null){
//            String fileName = username + "_" +sysUserMapper.getNameByUserName(username);//文件名由  学号_姓名  组成
//            fileName = fileName+".jpg";//文件名加后缀
//            byte[] photo = user.getPhoto();
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(photo);
//            String filePath = "D:\\Images\\PayImg\\";//存放文件路径
////            Files.copy(inputStream, Paths.get(filePath+fileName));
//            File dest = new File(filePath+fileName);
//            if (!dest.getParentFile().exists()) {
//                dest.getParentFile().mkdirs();//如果文件不存在就创建一个文件
//            }
//            try {
//                Files.copy(inputStream, Paths.get(filePath+fileName));
//            } catch (Exception e) {
//                e.printStackTrace();
//                return R.failed(fileName+"获取失败！文件已存在！");
//            }
//            return R.ok("获取成功！文件存放在:"+filePath);
//        }else{
//            return R.failed("用户不存在");
//        }
//    }


    @MyLog(operation = "上传身份证照片")
    @PostMapping("/upphoto")
    public R upPhoto(@RequestParam String username,@RequestParam String photo){
        SysUserEntity user = sysUserService.getUserByUsername(username);
        if(user!=null){
            fileOperationService.upPhoto(photo,username);
            return R.ok("身份证照片上传成功！");
        }else{
            return R.failed("用户不存在");
        }
    }
    @MyLog(operation = "上传缴费凭证")
    @PostMapping("/uppayimg")
    public R upPayImg(@RequestParam String username,@RequestParam String payimg){
        SysUserEntity user = sysUserService.getUserByUsername(username);
        if(user!=null){
            fileOperationService.upPayImg(payimg,username);
            return R.ok("支付凭证上传成功！");
        }else{
            return R.failed("用户不存在");
        }
    }
    @MyLog(operation = "获取身份证照片")
    @PostMapping("/getphoto")
    public R getPhoto(@RequestParam String username){
        SysUserEntity user = sysUserService.getUserByUsername(username);
        if(user!=null){

            return R.ok(fileOperationService.getPhoto(username));
        }else{
            return R.failed("用户不存在");
        }
    }
    @MyLog(operation = "获取缴费凭证")
    @PostMapping("/getpayimg")
    public R getPayImg(@RequestParam String username){
        SysUserEntity user = sysUserService.getUserByUsername(username);
        if(user!=null){

            return R.ok(fileOperationService.getPayImg(username));
        }else{
            return R.failed("用户不存在");
        }
    }
}
