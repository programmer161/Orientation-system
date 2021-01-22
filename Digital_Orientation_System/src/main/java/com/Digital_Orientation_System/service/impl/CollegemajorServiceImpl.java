package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.CollegemajorDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.entity.Collegemajor;
import com.Digital_Orientation_System.service.CollegemajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CollegemajorService")
public class CollegemajorServiceImpl extends ServiceImpl<CollegemajorDao, Collegemajor> implements CollegemajorService {
    @Autowired
    CollegemajorDao collegemajorDao;
    @Override
    public List<Collegemajor> getCollege() {
        return collegemajorDao.getCollege();
    }

    @Override
    public List<Collegemajor> getMajor() {
        return collegemajorDao.getMajor();
    }

    @Override
    public List<Collegemajor> getMajorByCollege(String college) {
        return collegemajorDao.getMajorByCollege(college);
    }

    @Override
    public List<Collegemajor> getClassesByMajor(String major) {
        return collegemajorDao.getClassesByMajor(major);
    }

    @Override
    public List<Collegemajor> getByCollege(String college) {
        return collegemajorDao.getByCollege(college);
    }

    @Override
    public List<Collegemajor> getByMajor(String major) {
        return collegemajorDao.getByMajor(major);
    }
}
