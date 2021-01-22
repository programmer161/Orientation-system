package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.Collegemajor;
import com.Digital_Orientation_System.service.CollegemajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegemajorController {
    @Autowired
    CollegemajorService collegemajorService;
    @MyLog(operation = "获取所有学院")
    @GetMapping("/getcollege")//获取所有学院
    public R getCollege(){
        return R.ok(collegemajorService.getCollege());
    }
    @MyLog(operation = "获取所有专业")
    @GetMapping("/getmajor")//获取所有专业
    public R getMajor(){
        return R.ok(collegemajorService.getMajor());
    }
    @MyLog(operation = "查找学院对应的专业")
    @PostMapping("/getmajorbycollege")//查找学院对应的专业
    public R getMajorByCollege(@RequestParam String college){
        List<Collegemajor> list1 = collegemajorService.getByCollege(college);
        if(list1.isEmpty()){
            return R.failed("学院不存在！");
        }else{
            List<Collegemajor> list = collegemajorService.getMajorByCollege(college);
            return R.ok(list);
        }
    }
    @MyLog(operation = "查找专业对应的班级")
    @PostMapping("/getclassesbymajor")
    public R getClassesByMajor(@RequestParam String major){
        List<Collegemajor> list = collegemajorService.getByMajor(major);
        if(list.isEmpty()){
            return R.failed("专业不存在！");
        }else{
            List<Collegemajor> list1 = collegemajorService.getClassesByMajor(major);
            return R.ok(list);
        }
    }
}
