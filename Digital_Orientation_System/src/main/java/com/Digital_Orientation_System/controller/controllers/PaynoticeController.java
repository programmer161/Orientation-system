package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.Paynotice;
import com.Digital_Orientation_System.service.PaynoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paynotice")
public class PaynoticeController {
    @Autowired
    PaynoticeService paynoticeService;
    @MyLog(operation = "获取缴费须知标题")
    @GetMapping("/gettitle")//显示缴费须知标题
    public R getTitle(){
        return R.ok(paynoticeService.getTitle());
    }
    @MyLog(operation = "获取缴费须知内容")
    @PostMapping("/getvalues")//获取缴费须知内容
    public R getValuesById(@RequestParam int id){
        Paynotice paynotice  =  paynoticeService.getById(id);
        if(paynotice!=null){
            return R.ok(paynoticeService.getValuesById(id));
        }else{
            return R.failed("id不存在！");
        }
    }
    @MyLog(operation = "更改缴费须知")
    @PostMapping("/updatepaynotice")//更改缴费须知
    public R updatePaynotice(@RequestBody Paynotice paynotice){
        Paynotice paynotice1 = paynoticeService.getById(paynotice.getId());
        if(paynotice1!=null){
            return R.ok(paynoticeService.updateById(paynotice));
        }else {
            return R.failed("修改失败！");
        }
    }
    @MyLog(operation = "删除缴费须知")
    @PostMapping("/delete")//删除/批量删除paynotice
    public R batchDelete(@RequestParam(value = "list") List<Integer> list){
        int x=0;
        for(int i=0;i<list.size();i++){
            Paynotice paynotice = paynoticeService.getById(list.get(i));
            if(paynotice==null){
                x++;
            }
        }
        if(x!=0){
            return R.failed("删除失败！通知不存在！");
        }else{
            paynoticeService.batchDelete(list);
            return R.ok("删除成功！");
        }
//
    }
    @MyLog(operation = "增加缴费须知")
    @PostMapping("/add")//新增校缴费须知
    public R addPaynotice(@RequestBody Paynotice paynotice){
        paynoticeService.addPaynotice(paynotice);
        return R.ok("添加成功！");
    }
}
