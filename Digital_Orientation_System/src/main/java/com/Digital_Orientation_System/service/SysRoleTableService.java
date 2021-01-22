package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.SysRoleTable;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;

import java.util.List;

/**
 * (SysRoleTable)表服务接口
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-07 14:31:50
 */
public interface SysRoleTableService extends IService<SysRoleTable> {

    /**
     * 根据用户名称查询角色
     * @param userName
     * @return
     */
    List<String> getRolesByUserName(String userName);

    /**
     * 根据roleId找用户以及用户是否被设置在某个角色上，用在显示在用于角色设置处
     * @param roleId
     * @return
     */
    List<SysRoleAndPermissionVo> getRoleAndUserList(String roleId);

    /**
     * 根据roleId找菜单
     */
    List<SysRoleAndPermissionVo> getRoleAndMenuList(String roleId) ;

    /**
     * 根据roleId找API
     */
    List<SysRoleAndPermissionVo> getRoleAndApiList(String roleId);


    List<SysRoleTable> getRoleAll();//获取所有角色信息

    SysRoleTable getCollegeMC(String roleId);//通过角色id获取角色管理的学院专业班级信息
    void updateCollegeMC(SysRoleTable sysRoleTable);//通过角色id修改角色管理的学院班级信息
}