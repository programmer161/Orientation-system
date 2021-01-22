package com.Digital_Orientation_System.service;

import com.Digital_Orientation_System.entity.Dorm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DormService {
    List<String> getApartMents();//获取公寓楼号
    List<String> getDormByApartMents(String apartments);//通过公寓楼号获取对应的宿舍
    String getByApartMents(String apartments);//判断公寓是否存在

}
