package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.Message;
import com.Digital_Orientation_System.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    MessageService messageService;

    @MyLog(operation = "获取校园通知标题")
    @GetMapping("/gettitle")//显示校园通知标题
    public R getTitle(){
        return R.ok(messageService.getTitle());
    }
    @MyLog(operation = "获取校园通知内容")
    @PostMapping("/getvalues")//获取校园通知内容
    public R getValues(@RequestParam int id){
        Message message  =  messageService.getById(id);
        if(message!=null){
            return R.ok(messageService.getValuesById(id));
        }else{
            return R.failed("id不存在！");
        }
    }
    @MyLog(operation = "更改校园通知")
    @PostMapping("/updatemessage")//更改学校通知
    public R updateMessage(@RequestBody Message message){

        Message message1 = messageService.getById(message.getId());
        if(message1!=null){
            return R.ok(messageService.updateById(message));
        }else {
            return R.failed("修改失败！");
        }
    }
    //    String[] Snos = sno.split(",");
//    List<Integer> list = new ArrayList<>();
//        for(String str:Snos){
//        list.add(Integer.parseInt(str));
//        if(studentService.findById(Integer.parseInt(str))==null) {
//            return Result.fail("删除失败！", "学生不存在！");
//        }
//    }
//        studentService.batchDelete(list);
//        return Result.succ("删除成功！");
    @MyLog(operation = "删除校园通知")
    @PostMapping("/delete")//删除/批量删除message
    public R batchDelete(@RequestParam(value = "list") List<Integer> list){
        int x=0;
        for(int i=0;i<list.size();i++){
            Message message = messageService.getById(list.get(i));
            if(message==null){
                x++;
            }
        }
        if(x!=0){
            return R.failed("删除失败！通知不存在！");
        }else{
            messageService.batchDelete(list);
            return R.ok("删除成功！");
        }
//
    }
    @MyLog(operation = "增加校园通知")
    @PostMapping("/add")//新增学校通知
    public R addMessage(@RequestBody Message message){
        messageService.addMessage(message);
        return R.ok("添加成功！");
    }

}
