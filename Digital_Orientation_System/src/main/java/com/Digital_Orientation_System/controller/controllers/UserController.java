package com.Digital_Orientation_System.controller.controllers;


import com.Digital_Orientation_System.dao.CollegemajorDao;
import com.Digital_Orientation_System.dao.SysUserMapper;
import com.Digital_Orientation_System.entity.Collegemajor;
import com.Digital_Orientation_System.entity.Message;
import com.Digital_Orientation_System.log.MyLog;
import com.Digital_Orientation_System.service.ManagerStudentService;
import com.Digital_Orientation_System.service.SysRoleUserTableService;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.components.BCryptPasswordEncoderUtil;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.entity.Vo.AddStudentVo;
import com.Digital_Orientation_System.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.baomidou.mybatisplus.extension.api.R.failed;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    CollegemajorDao collegemajorDao;

    @Autowired
    SysRoleUserTableService sysRoleUserTableService;

    @Autowired
    ManagerStudentService managerStudentService;

    @MyLog(operation = "通过ID查找用户信息")
    @PostMapping("/findbyid")//查找用户信息
    public R getUserById(@RequestParam int id) {
        SysUserEntity sysUserEntity = sysUserService.getById(id);
        if (sysUserEntity != null) {
            return R.ok(sysUserEntity);
        } else {
            return failed("用户不存在！");
        }
    }
    @MyLog(operation = "更改用户信息")
    @PostMapping("/update")//修改用户信息
    public R updateUser(@RequestBody SysUserEntity sysUserEntity) {
        SysUserEntity user = sysUserService.getById(sysUserEntity.getUserId());
        if (user != null) {
            sysUserService.updateById(sysUserEntity);
            return R.ok(null);
        } else {
            return failed("用户不存在！");
        }

    }
    @MyLog(operation = "通过身份证号码查找学号")
    @PostMapping("/findsno")//通过身份证号码查找学号(id)
    public R findSnoByIdNumber(@RequestParam String idnumber) {
        SysUserEntity user = sysUserService.findUserByIdnumber(idnumber);
        if (user != null) {
            return R.ok(sysUserService.findSnoById(idnumber));
        } else {
            return failed("身份证号不存在！");
        }

    }

    @MyLog(operation = "获取学生总人数和完成人数")
    @PostMapping("/getsumfinished")//获取学生总人数和完成人数
    public R getSumAndFinished(String username) {
        return R.ok(sysUserService.getSumAndFinished(username));
    }
    @MyLog(operation = "获取新生的现场报道情况")
    @PostMapping("/getreportnow")//获取所有管理的新生的现场报道情况 student
    public R getReportNow(String username) {
        return R.ok(sysUserService.getReportNow(username));
    }
    @MyLog(operation = "获取报道各事务完成人数")
    @PostMapping("/getreportstatus")//各业务完成人数
    public R getReportStatus(String username) {
        return R.ok(sysUserService.getReportStatus(username));
    }

    @MyLog(operation = "通过用户名查询新生报到情况")
    @PostMapping("/getreportnowbyusername")//通过id查询新生现场报道情况
    public R getReportNowById(@RequestParam String username) {
        SysUserEntity user = sysUserService.getReportNowByUserName(username);
        if (user != null) {
            return R.ok(sysUserService.getReportNowByUserName(username));
        } else {
            return failed("学生不存在或已报道！");
        }
    }
    @MyLog(operation = "获取学生缴费情况")
//    @GetMapping("/getpay")//获取学生缴费情况
//    public R getPay() {
//        return R.ok(sysUserService.getPay());
//    }
    @PostMapping("/getpay")//获取学生缴费情况
    public R getPay(@RequestParam String username) {
        return R.ok(sysUserService.getPay(username));
    }
    @MyLog(operation = "通过用户名获取学生缴费情况")
    @PostMapping("/getpaybyusername")
    public R getPayById(@RequestParam String username) {
        SysUserEntity userEntity = sysUserService.getPayByUserName(username);
        if (userEntity != null) {
            return R.ok(sysUserService.getPayByUserName(username));
        } else {
            return failed("学生不存在或已报道！");
        }
    }
    @MyLog(operation = "通过学号查找学生信息")
    @PostMapping("/findstudentbyusername")//通过学号(username)查询学生信息
    public R findStudentById(@RequestParam String username) {
        SysUserEntity user = sysUserService.findStudentByUserName(username);
        if (user != null) {
            return R.ok(user);
        } else {
            return failed("学生不存在！");
        }
    }
    @MyLog(operation = "查找所有学生信息")
    @PostMapping("/findsdtuentall")//查找所有学生信息
    public R findStudentAll(String username) {
        return R.ok(sysUserService.findStudentAll(username));
    }
    @MyLog(operation = "查找完成报到学生人数")
    @PostMapping("/getfinished")//查找完成报道学生人数
    public R getFinished(@RequestParam String username) {
        return R.ok(sysUserService.getFinished(username));
    }
    @MyLog(operation = "查找未完成报道学生人数")
    @PostMapping("/getunfinished")//查找未完成报道学生人数
    public R getUnFinished(@RequestParam String username) {
        return R.ok(sysUserService.getUnFinished(username));
    }
    @MyLog(operation = "确认支付学费")
    @PostMapping("/payconfirm")
    public R payConfirm(@RequestParam String username) {
        SysUserEntity user = sysUserService.getUserByUserName(username);
        if (user != null) {
            sysUserService.payConfirm(username);
            return R.ok("确认成功！");
        } else {
            return failed("学生不存在!");
        }

    }
    @MyLog(operation = "确认完成现场报道")
    @PostMapping("/reportconfirm")
    public R reportConfirm(@RequestParam String username) {
        SysUserEntity user = sysUserService.getUserByUserName(username);
        if (user != null) {
            sysUserService.reportConfirm(username);
            return R.ok("确认成功！");
        } else {
            return failed("学生不存在!");
        }

    }
    @MyLog(operation = "获取每个年龄段的人数")
    @GetMapping("/getagecount")//获取每个年龄段的人数
    public R getAgeCount() {
        return R.ok(sysUserService.getAgecount());
    }
    //---------------------------------------------------------------------------------------Sys_Manager
    @MyLog(operation = "获取管理员人数")
    @GetMapping("/getmanagersum")//获取管理员人数
    public R getManagerSum() {

        return R.ok(sysUserService.getManagerSum());
    }
    @MyLog(operation = "获取管理员名单")
    @GetMapping("/getmanagerall")//获取管理员名单
    public R getManagerAll() {
        return R.ok(sysUserService.getManagerAll());
    }

    //    @PostMapping("/addstudent")//生成用户
//    public R addStudent(@RequestBody SysUserEntity sysUserEntity) {
//        sysUserService.addStudent(sysUserEntity);
//        return R.ok("添加成功！");
//    }
    @MyLog(operation = "通过学院查找学生")
    @PostMapping("/findbycollege")//通过学院查找student
    public R findByCollege(@RequestParam String college) {
        List<SysUserEntity> list = new ArrayList<>();
        list = sysUserService.findByCollege(college);
        if (list.isEmpty()) {
            return failed("查找失败！");
        } else {
            return R.ok(list);
        }
    }
    @MyLog(operation = "通过专业查找学生")
    @PostMapping("/findbymajor")//通过专业查找student
    public R findByMajor(@RequestParam String major) {
        List<SysUserEntity> list = new ArrayList<>();
        list = sysUserService.findByMajor(major);
        if (list.isEmpty()) {
            return failed("查找失败！");
        } else {
            return R.ok(list);
        }
    }
    @MyLog(operation = "通过用户名查找管理员")
    @PostMapping("/findmanagerbyusername")//通过username查找管理员
    public R findManagerByUserName(@RequestParam String username) {
        SysUserEntity sysUserEntity = sysUserService.findManagerByUserName(username);
        if (sysUserEntity != null) {
            return R.ok(sysUserEntity);
        } else {
            return failed("用户不存在！");
        }
    }
    @MyLog(operation = "修改管理员信息")
    @PostMapping("/updatemanager")//修改管理员信息
    public R updateManager(@RequestBody SysUserEntity sysUserEntity) {
        SysUserEntity user = sysUserService.getUserByUserName(sysUserEntity.getUserName());
        if (user != null) {
            sysUserService.updateManager(sysUserEntity);
            return R.ok("修改成功！");
        } else {
            return failed("修改失败！");
        }
    }
    @MyLog(operation = "修改学生信息")
    @PostMapping("/updatestudent")//修改学生信息
    public R updateStudent(@RequestBody SysUserEntity sysUserEntity) {
        SysUserEntity user = sysUserService.getUserByUserName(sysUserEntity.getUserName());
        if (user != null) {
            sysUserService.updateStudent(sysUserEntity);
            return R.ok("修改成功！");
        } else {
            return failed("修改失败！");
        }
    }
    @MyLog(operation = "通过学院查找管理员")
    @PostMapping("/findmanagerbycollege")//通过学院找管理员
    public R findManagerByCollege(@RequestParam String college){
        List<SysUserEntity> user = sysUserService.findManagerByCollege(college);
        if(user.isEmpty()){
            return failed("查找失败！");

        }else{

            return R.ok(user);
        }
    }



    @MyLog(operation = "添加用户")
    @PostMapping("/add")//添加用户
    public R addStudent(@RequestBody(required = false) AddStudentVo studentVo) {
        if(studentVo!=null){
            SysUserEntity f = sysUserService.getUserByUserName(studentVo.getUsername());
            if(f!=null){
                return R.failed("添加失败！用户名存在！");
            }

            sysUserService.addStudent(studentVo);
            return R.ok("添加成功！");
        }else{
            return R.failed("添加失败！输入为空！");
        }
    }
    @MyLog(operation = "修改密码")
    @PostMapping("/updatepwd")//修改密码
    public R updatePwd(@RequestParam  String username,
                       @RequestParam  String password,
                       @RequestParam  String firstPassword,
                       @RequestParam String secondPassword){
        int str = sysUserService.updatePwd(username,password,firstPassword,secondPassword);
        if(str==4){
            return R.failed("用户名不存在！");
        }else if(str==2){
            return R.failed("两次密码输入不一致！");
        }else if(str==3){
            return R.ok("修改成功！");
        }else {
            return R.failed("密码不正确！");
        }
    }
    @MyLog(operation = "查找每个管理员对应的学生")
    @PostMapping("/getstudentbymanager")//查找每个管理员对应的学生
    public R getStudentByManager(@RequestParam String major){
        List<SysUserEntity> list = sysUserService.findByMajor(major);
        if(!list.isEmpty()){
            return R.ok(sysUserService.findStudentByManager(major));
        }else{
            return R.failed("查找失败！");
        }
    }
    @MyLog(operation = "通过学院查找辅导员电话")
    @PostMapping("/getinstruct")//通过学院查找辅导员姓名电话
    public R getInstructByCollege(@RequestParam String college){
        List<SysUserEntity> user = sysUserService.findByCollege(college);
        if(!user.isEmpty()){
            return R.ok(sysUserService.getInstructByCollege(college));
        }else{
            return R.failed("查找失败！");
        }
    }
    @MyLog(operation = "获取男女生人数")
    @PostMapping("/getsexnumber")//获取男女生人数
    public R getSexNumber(String username){
        return R.ok(sysUserService.getSexNumber(username));
    }

    @MyLog(operation = "通过专业和班级查找学生")
    @PostMapping("/getstudentbyclasses")
    public R getStudentByClasses(@RequestParam String major,@RequestParam String classes){
        List<Collegemajor> collegemajor = collegemajorDao.getByMajor(major);
        if(collegemajor.isEmpty()){
            return R.failed("专业不存在！");
        }else{
            List<SysUserEntity> list = sysUserService.getStudentByClasses(major, classes);
            return R.ok(list);
        }
    }

    @MyLog(operation = "通过用户名获取用户信息")
    @PostMapping("/getuserbyusername")
    public R getUserByUserName(@RequestParam String username){
        SysUserEntity user = sysUserService.getUserByUserName(username);
        if(user!=null){
            return R.ok(user);
        }else{
            return R.failed("用户不存在！");
        }
    }

    @MyLog(operation = "增加/批量增加用户")
    @PostMapping("/addusers")
    public R addUsers(@RequestBody List<SysUserEntity> list){
        int x=0,y=0;
        for(int i=0;i<list.size();i++){
            SysUserEntity user = sysUserService.getUserByUserName(list.get(i).getUserName());
            if(user==null){
                sysUserService.insertUser(list.get(i));
                x++;
            }else{
                y++;
            }
        }
        return R.ok("添加成功用户数:"+x+",添加失败用户数:"+y);
    }
    @MyLog(operation = "增加/批量增加学生")
    @PostMapping("/addstudent")
    public R addStudent(@RequestBody List<SysUserEntity> list){
        int x=0,y=0;
        for(int i=0;i<list.size();i++){
            SysUserEntity user = sysUserService.getUserByUserName(list.get(i).getUserName());
            if(user==null){
                list.get(i).setDescription("学生");//默认description为学生
                String idnumber = list.get(i).getIdnumber();
                String pwd = idnumber.substring(idnumber.length() - 6);
                list.get(i).setPassWord(bCryptPasswordEncoderUtil.encode(pwd));//初始密码设置为身份证后6位
                String sex = list.get(i).getSex().trim();
                list.get(i).setSex(sex);
                sysUserService.insertUser(list.get(i));
                String userId = list.get(i).getUserId();
                managerStudentService.insertSysManager_Student(userId);//系统管理员管理所有用户在manager_student表中
                sysRoleUserTableService.saveRoleStudent(userId);
                x++;
            }else{
                y++;
            }
        }
        return R.ok("添加成功用户数:"+x+",添加失败用户数:"+y);
    }
    @MyLog(operation = "增加/批量增加管理员")
    @PostMapping("/addmanager")
    public R addManager(@RequestBody List<SysUserEntity> list){
        int x=0,y=0;
        for(int i=0;i<list.size();i++){
            SysUserEntity user = sysUserService.getUserByUserName(list.get(i).getUserName());
            if(user==null){
                list.get(i).setDescription("普通管理员");//默认description为普通管理员
                String idnumber = list.get(i).getIdnumber();
                String pwd = idnumber.substring(idnumber.length() - 6);
                list.get(i).setPassWord(bCryptPasswordEncoderUtil.encode(pwd));//初始密码设置为身份证后6位
                String sex = list.get(i).getSex().trim();
                list.get(i).setSex(sex);
                sysUserService.insertUser(list.get(i));
                String userId = list.get(i).getUserId();
                managerStudentService.insertSysManager_Student(userId);//系统管理员管理所有用户在manager_student表中
                sysRoleUserTableService.saveRoleManager(userId);
                x++;
            }else{
                y++;
            }
        }
        return R.ok("添加成功用户数:"+x+",添加失败用户数:"+y);
    }

    @MyLog(operation = "系统管理员获取所有学生名单")
    @GetMapping("/getstudentsys")
    public R getStudentAllSys(){
        return R.ok(sysUserService.getStudentAllSys());
    }
    @MyLog(operation = "系统管理员获取所有完成报到学生名单")
    @GetMapping("/getfinishedsys")
    public R getFinishedSys(){
        return R.ok(sysUserService.getFinishedSys());
    }
    @MyLog(operation = "系统管理员获取所有未完成报到学生")
    @GetMapping("/getunfinishedsys")
    public R getUnFinishedSys(){
        return R.ok(sysUserService.getUnFinishedSys());
    }

    @MyLog(operation = "系统管理员获取已完成报到学生人数")
    @GetMapping("/getfinishedcountsys")
    public R getFinishedCountSys(){
        return R.ok(sysUserService.getFinishedCountSys());
    }

    @MyLog(operation = "系统管理员获取全部学生人数")
    @GetMapping("/getsumsys")
    public R getSumSys(){
        return R.ok(sysUserService.getSumSys());
    }
}

