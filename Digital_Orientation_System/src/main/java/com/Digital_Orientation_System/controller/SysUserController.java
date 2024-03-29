package com.Digital_Orientation_System.controller;

import com.Digital_Orientation_System.log.MyLog;
import com.Digital_Orientation_System.vo.SysUserVo;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.service.SysRoleTableService;
import com.Digital_Orientation_System.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录接口
 */
@RestController
@RequestMapping("user")
public class SysUserController extends ApiController {

    @Autowired
    SysUserService userService;
    @Autowired
    SysRoleTableService sysRoleTableService;

    /**
     * 查询所有数据集
     * @return
     */
    @MyLog(operation = "获取所有用户信息")
    @GetMapping("search")
    public R getList() {

        List<SysUserEntity> list =  userService.list();
        return  success(list);
    }

    /**
     * 添加用户、用户自行注册。
     * @param userVo
     * @return
     */
    @MyLog(operation = "注册/新增用户")
    @PostMapping("register")
    public R register(@RequestBody(required = false) SysUserVo userVo) {
       try {
           System.out.println("registerVo = " + userVo);
           return  success(userService.register(userVo));
       }catch (Exception e){
           return failed(e.getMessage());
       }
    }

    /**
     * 修改数据
     *
     * @param sysUserVo 实体对象
     * @return 修改结果
     */
    @MyLog(operation = "修改用户信息")
    @PutMapping
    public R update(@RequestBody SysUserVo sysUserVo) {
        SysUserEntity po = this.userService.voToPo(sysUserVo);
        System.out.println("po = " + po);
        return success(this.userService.updateById(po));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @MyLog(operation = "删除用户")
    @DeleteMapping
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.userService.removeByIds(idList));
    }



}
