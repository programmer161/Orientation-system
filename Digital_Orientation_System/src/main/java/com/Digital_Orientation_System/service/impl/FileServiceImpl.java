package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.FileDao;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileServiceImpl extends ServiceImpl<FileDao, SysUserEntity>  implements FileService {

    @Autowired
    FileDao fileDao;
    @Override
    public R uploadPay(String username, MultipartFile file) {
        SysUserEntity user = fileDao.findByUserName(username);
        if (user != null) {
            if (file.isEmpty()) {
                return R.failed("文件为空！");
            }
            String OriginalFilename = file.getOriginalFilename();//文件名
            String fileName = OriginalFilename;//获取主文件名和文件扩展名
            String filePath = "D:\\appwork\\IDE\\SpringBootProjects\\file\\PayImg\\";//存放文件路径
            fileDao.uploadPay(username,filePath+fileName);
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();//如果文件不存在就创建一个文件
            }
            try {
                file.transferTo((dest));//把file拷贝到目标文件
            } catch (Exception e) {
                e.printStackTrace();
                return R.failed(OriginalFilename + "上传失败！");
            }
            return R.ok(fileName);
        } else {
            return R.failed("用户不存在!");
        }
    }

    @Override
    public R uploadPhoto(String username, MultipartFile file) {
        SysUserEntity user = fileDao.findByUserName(username);
        if (user != null) {
            if (file.isEmpty()) {
                return R.failed("文件为空！");
            }
            String OriginalFilename = file.getOriginalFilename();//文件名
            String fileName = OriginalFilename;//获取主文件名和文件扩展名
            String filePath = "D:\\appwork\\IDE\\SpringBootProjects\\file\\PhotoImg\\";//存放文件路径
            fileDao.uploadPhoto(username,filePath+fileName);
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();//如果文件不存在就创建一个文件
            }
            try {
                file.transferTo((dest));//把file拷贝到目标文件
            } catch (Exception e) {
                e.printStackTrace();
                return R.failed(OriginalFilename + "上传失败！");
            }
            return R.ok(fileName);
        } else {
            return R.failed("用户不存在!");
        }
    }

    @Override
    public String download(String username) {
        return fileDao.downloadFile(username);
    }

    @Override
    public SysUserEntity findByUserName(String username) {
        return fileDao.findByUserName(username);
    }
}
