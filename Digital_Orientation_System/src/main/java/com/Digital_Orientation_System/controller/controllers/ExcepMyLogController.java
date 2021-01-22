package com.Digital_Orientation_System.controller.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.ExcepMyLog;
import com.Digital_Orientation_System.log.MyLog;
import com.Digital_Orientation_System.service.ExcepMyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/excepmylog")//异常日志
public class ExcepMyLogController {
    @Autowired
    ExcepMyLogService excepMyLogService;
    @MyLog(operation = "获取所有异常日志信息")
    @GetMapping("/getall")//获取所有日志信息
    public R getExcepMyLogAll(){
        return R.ok(excepMyLogService.getExcepMylogAll());
    }
    @MyLog(operation = "删除异常日志")
    @PostMapping("/delete")//删除日志
    public R deleteExcepMyLog(@RequestParam List<Integer> list){
        int x=0;
        for(int i=0;i<list.size();i++){
            ExcepMyLog excepMyLog = excepMyLogService.getById(list.get(i));
            if(excepMyLog==null){
                x++;
            }
        }
        if(x!=0){
            return R.failed("删除失败！日志不存在！");
        }else{
            excepMyLogService.batchDelete(list);
            return R.ok("删除成功！");
        }
    }

    @MyLog(operation = "通过用户名查找异常日志")
    @PostMapping("/getexcepmylog")
    public R getExcepMyLog(@RequestParam String username){
        List<ExcepMyLog> excepMyLog = excepMyLogService.getExcepMylogByUserName(username);
        if(excepMyLog!=null){
            return R.ok(excepMyLog);
        }else{
            return R.failed("用户名不存在！");
        }
    }
}
