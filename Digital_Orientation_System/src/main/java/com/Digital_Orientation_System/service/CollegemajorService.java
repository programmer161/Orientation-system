package com.Digital_Orientation_System.service;

import com.Digital_Orientation_System.entity.Collegemajor;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CollegemajorService extends IService<Collegemajor> {
    List<Collegemajor> getCollege();//获取所有学院
    List<Collegemajor> getMajor();//获取所有专业
    List<Collegemajor> getMajorByCollege(String college);//查找学院对应的专业
    List<Collegemajor> getClassesByMajor(String major);//查找专业对应的班级
    List<Collegemajor>  getByCollege(String college);//判断学院是否存在
    List<Collegemajor> getByMajor(String major);//判断专业是否存在

}
