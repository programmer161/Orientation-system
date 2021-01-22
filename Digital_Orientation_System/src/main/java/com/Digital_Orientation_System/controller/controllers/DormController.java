package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.entity.Dorm;
import com.Digital_Orientation_System.log.MyLog;
import com.Digital_Orientation_System.service.DormService;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dorm")
public class DormController {

    @Autowired
    DormService dormService;

    @MyLog(operation = "获取公寓楼号")
    @GetMapping("/getapartments")
    public R getApartMents(){
        return R.ok(dormService.getApartMents());
    }

    @MyLog(operation = "通过公寓楼号获取宿舍")
    @PostMapping("/getdorm")
    public R  getDormByApartMents(@RequestParam String apartments){
        String apartments1 = dormService.getByApartMents(apartments);
        if(apartments1!=null){
            return R.ok(dormService.getDormByApartMents(apartments));
        }else{
            return R.failed("公寓不存在！");
        }
    }
}
