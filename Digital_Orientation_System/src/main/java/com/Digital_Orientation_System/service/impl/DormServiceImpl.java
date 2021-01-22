package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.dao.DormDao;
import com.Digital_Orientation_System.entity.Dorm;
import com.Digital_Orientation_System.service.DormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormServiceImpl implements DormService {
    @Autowired
    DormDao dormDao;

    @Override
    public List<String> getApartMents() {
        return dormDao.getApartMents();
    }

    @Override
    public List<String> getDormByApartMents(String apartments) {
        return dormDao.getDormByApartMents(apartments);
    }

    @Override
    public String getByApartMents(String apartments) {
        return dormDao.getByApartMents(apartments);
    }
}
