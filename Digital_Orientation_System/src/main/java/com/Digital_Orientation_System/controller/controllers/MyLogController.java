package com.Digital_Orientation_System.controller.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.AdminLog;
import com.Digital_Orientation_System.log.MyLog;
import com.Digital_Orientation_System.service.MyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mylog")//日志
public class MyLogController {
    @Autowired
    MyLogService myLogService;
    @MyLog(operation = "获取所有日志信息")
    @GetMapping("/getall")//获取所有日志信息
    public R getMyLogAll(){
        return R.ok(myLogService.getMylogAll());
    }
    @MyLog(operation = "删除日志")
    @PostMapping("/delete")//删除日志
    public R deleteMyLog(@RequestParam List<Integer> list){
        int x=0;
        for(int i=0;i<list.size();i++){
            AdminLog adminLog = myLogService.getById(list.get(i));
            if(adminLog==null){
                x++;
            }
        }
        if(x!=0){
            return R.failed("删除失败！日志不存在！");
        }else{
            myLogService.batchDelete(list);
            return R.ok("删除成功！");
        }
    }

    @MyLog(operation = "通过用户名查找日志")
    @PostMapping("/getmylogbyusername")
    public R getMyLogByUserName(@RequestParam String username){
        List<AdminLog> adminLog = myLogService.getMylogByUserName(username);
        if(adminLog!=null){
            return R.ok(adminLog);
        }else{
            return R.ok("用户名不存在！");
        }
    }
}
