package com.Digital_Orientation_System.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.SysRoleUserTable;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;
import org.apache.ibatis.annotations.Update;

/**
 * (SysRoleUserTable)表服务接口
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-08 23:03:14
 */
public interface SysRoleUserTableService extends IService<SysRoleUserTable> {

    /**
     * 批量保存角色-用户表
     * 该方法内有两个操作，先删除、后批量新增，因此存在事务控制
     * @param roleId
     * @param sysRoleAndPermissionVos
     * @return
     */
    boolean saveRoleUser(String roleId, SysRoleAndPermissionVo... sysRoleAndPermissionVos);

    void saveRoleStudent(String userId);//初始用户为学生角色
    void saveRoleManager(String userId);//初始用户为管理员角色

}