package com.Digital_Orientation_System.controller.controllers;

import com.Digital_Orientation_System.dao.SysUserMapper;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.Digital_Orientation_System.log.MyLog;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.Digital_Orientation_System.service.ManagerStudentService;
import com.Digital_Orientation_System.service.SysRoleTableService;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managerstudent")
public class ManagerStudentController extends ApiController {
    @Autowired
    SysRoleTableService sysRoleTableService;
    @Autowired
    ManagerStudentService managerStudentService;
    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 对应前端：系统管理->用户角色设置->右边用户查询
     * @param roleId
     * @return
     */
    @MyLog(operation = "获取该角色管理的用户")
    @GetMapping("/roleUser/{roleId}")
    public R getRoleAndUserList(@PathVariable("roleId") String roleId) {
        return success(managerStudentService.getRoleAndUserList(roleId));
    }
    @MyLog(operation = "给角色分配用户")
    @PostMapping("/roleUser/set")
    public R saveRoleUser(@RequestParam("roleId") String roleId,
                          @RequestBody SysRoleAndPermissionVo[] sysRoleAndPermissionVos){

        System.out.println("--------------现在进入方法saveRoleUser--------------");
        return success(managerStudentService.saveRoleUser(roleId, sysRoleAndPermissionVos));
    }

    @MyLog(operation = "通过管理员username查找所管理的用户")
    @PostMapping("/getuserbyusernameandmanager")
    public R getUserByUserNameAndManager(@RequestParam String username){
        SysUserEntity userEntity = sysUserMapper.getUserByUsername(username);
        if(userEntity!=null){
            return R.ok(managerStudentService.getUserByUserNameAndManager(username));
        }else{
            return R.failed("用户不存在！");
        }
    }
}
