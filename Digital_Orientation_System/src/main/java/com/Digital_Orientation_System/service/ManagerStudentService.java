package com.Digital_Orientation_System.service;

import com.Digital_Orientation_System.entity.Manager_Stduent;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ManagerStudentService extends IService<Manager_Stduent> {

    /**
     * 批量保存角色-用户表
     * 该方法内有两个操作，先删除、后批量新增，因此存在事务控制
     * @param roleId
     * @param sysRoleAndPermissionVos
     * @return
     */
    boolean saveRoleUser(String roleId, SysRoleAndPermissionVo... sysRoleAndPermissionVos);
    List<SysRoleAndPermissionVo> getRoleAndUserList(String roleId);

    List<SysUserEntity> getUserByUserNameAndManager(@RequestParam String username);//通过管理员的username获取所管理的用户
    void insertSysManager_Student(String userId);//系统管理员管理所有用户在manager_student表中



}
