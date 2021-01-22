package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.Trategy;
import com.Digital_Orientation_System.service.TrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trategy")
public class TrategyController {
    @Autowired
    TrategyService trategyService;
    @MyLog(operation = "获取校园攻略标题")
    @GetMapping("/gettitle")//显示校园攻略标题
    public R getTitle(){
        return R.ok(trategyService.getTitle());
    }
    @MyLog(operation = "获取校园攻略内容")
    @PostMapping("/getvalues")//获取校园攻略内容
    public R getValuesById(@RequestParam int id){
        Trategy trategy  =  trategyService.getById(id);
        if(trategy!=null){
            return R.ok(trategyService.getValuesById(id));
        }else{
            return R.failed("id不存在！");
        }


    }


    @MyLog(operation = "更改校园攻略")
    @PostMapping("/updatetrategy")//更改校园攻略
    public R updateTrategy(@RequestBody Trategy trategy){
        Trategy trategy1 = trategyService.getById(trategy.getId());
        if(trategy1!=null){
            return R.ok(trategyService.updateById(trategy));
        }else {
            return R.failed("修改失败！");
        }
    }
    @MyLog(operation = "删除校园攻略")
    @PostMapping("/delete")//删除/批量删除trategy
    public R batchDelete(@RequestParam(value = "list") List<Integer> list){
        int x=0;
        for(int i=0;i<list.size();i++){
            Trategy trategy = trategyService.getById(list.get(i));
            if(trategy==null){
                x++;
            }
        }
        if(x!=0){
            return R.failed("删除失败！通知不存在！");
        }else{
            trategyService.batchDelete(list);
            return R.ok("删除成功！");
        }
//
    }
    @MyLog(operation = "新增校园攻略")
    @PostMapping("/add")//新增校园攻略
    public R addTrategy(@RequestBody Trategy trategy){
        trategyService.addTrategy(trategy);
        return R.ok("添加成功！");
    }


}
