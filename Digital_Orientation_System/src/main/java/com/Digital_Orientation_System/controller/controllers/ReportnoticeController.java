package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.Reportnotice;
import com.Digital_Orientation_System.service.ReportnoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportnotice")
public class ReportnoticeController {
    @Autowired
    ReportnoticeService reportnoticeService;
    @MyLog(operation = "获取报道须知标题")
    @GetMapping("/gettitle")//显示报到须知标题
    public R getTitle(){
        return R.ok(reportnoticeService.getTitle());
    }
    @MyLog(operation = "获取报道须知内容")
    @PostMapping("/getvalues")//获取报到须知内容
    public R getValuesById(@RequestParam int id){
        Reportnotice reportnotice  =  reportnoticeService.getById(id);
        if(reportnotice!=null){
            return R.ok(reportnoticeService.getValuesById(id));
        }else{
            return R.failed("id不存在！");
        }
    }


    @MyLog(operation = "更改报道须知")
    @PostMapping("/update")//更改报到须知
    public R updateReportnotice(@RequestBody Reportnotice reportnotice){
        Reportnotice reportnotice1 = reportnoticeService.getById(reportnotice.getId());
        if(reportnotice1!=null){
            return R.ok(reportnoticeService.updateById(reportnotice));
        }else {
            return R.failed("修改失败！");
        }
    }
    @MyLog(operation = "删除报道须知")
    @PostMapping("/delete")//删除/批量删除reportnotice
    public R batchDelete(@RequestParam(value = "list") List<Integer> list){
        int x=0;
        for(int i=0;i<list.size();i++){
            Reportnotice reportnotice = reportnoticeService.getById(list.get(i));
            if(reportnotice==null){
                x++;
            }
        }
        if(x!=0){
            return R.failed("删除失败！通知不存在！");
        }else{
            reportnoticeService.batchDelete(list);
            return R.ok("删除成功！");
        }
//
    }
    @MyLog(operation = "增加报道须知")
    @PostMapping("/add")//新增报到须知
    public R addReportnotice(@RequestBody Reportnotice reportnotice){
        reportnoticeService.addReportnotice(reportnotice);
        return R.ok("添加成功！");
    }

}
