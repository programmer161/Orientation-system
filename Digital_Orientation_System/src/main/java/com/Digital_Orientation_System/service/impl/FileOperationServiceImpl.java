package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.FileOperationDao;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.service.FileOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileOperationServiceImpl implements FileOperationService {
    @Autowired
    FileOperationDao fileOperationDao;

    @Override
    public void upLoadPhoto(byte[] photo, String username) {
        fileOperationDao.upLoadPhoto(photo, username);
    }

    @Override
    public void upLoadPayImg(byte[] payimg, String username) {
        fileOperationDao.upLoadPayImg(payimg, username);
    }

    @Override
    public void upPhoto(String photo, String username) {
        fileOperationDao.upPhoto(photo, username);
    }

    @Override
    public void upPayImg(String payimg, String username) {
        fileOperationDao.upPayImg(payimg, username);
    }

    @Override
    public String getPhoto(String username) {
        return fileOperationDao.getPhoto(username);
    }

    @Override
    public String getPayImg(String username) {
        return fileOperationDao.getPayImg(username);
    }
}
