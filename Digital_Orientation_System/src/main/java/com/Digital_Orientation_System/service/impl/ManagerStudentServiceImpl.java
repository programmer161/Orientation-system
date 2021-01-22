package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.dao.ManagerStudentDao;
import com.Digital_Orientation_System.entity.Manager_Stduent;
import com.Digital_Orientation_System.service.ManagerStudentService;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("ManagerStudentService")
public class ManagerStudentServiceImpl extends ServiceImpl<ManagerStudentDao, Manager_Stduent> implements ManagerStudentService {
    /**
     * 批量保存角色-用户表
     * 该方法内有两个操作，先删除、后批量新增，因此存在事务控制
     * @param roleId
     * @param sysRoleAndPermissionVos
     * @return
     */
    @Autowired
    ManagerStudentDao managerStudentDao;
    @Override
    @Transactional
    public   boolean saveRoleUser(String roleId, SysRoleAndPermissionVo... sysRoleAndPermissionVos){

        System.out.println("roleId = " + roleId);

        //先删除数据
        this.delRoleId(roleId);
        //
        if(sysRoleAndPermissionVos !=null) {
            Set<Manager_Stduent> set = new HashSet<>();
            Manager_Stduent roleUser = null;
            for (SysRoleAndPermissionVo roleVo : sysRoleAndPermissionVos) {
                roleUser = new Manager_Stduent();
                //存储roleID和userID到多对对的中间表
                roleUser.setRoleId(roleVo.getRoleId());
                roleUser.setUserId(roleVo.getId());
                set.add(roleUser);
            }
            System.out.println("set = " + set);
            //再批量保存
            return this.saveBatch(set);
        }
        return  false;
    }

    /**
     * 根据RoleId删除
     * @param roleId
     * @return
     */
    private boolean delRoleId(String roleId) {
        LambdaQueryWrapper<Manager_Stduent> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Manager_Stduent::getRoleId,roleId);
        return this.remove(lambdaQueryWrapper);
    }

    @Override
    public List<SysRoleAndPermissionVo> getRoleAndUserList(String roleId) {
        return this.baseMapper.getRoleAndUserList(roleId);
    }

    @Override
    public List<SysUserEntity> getUserByUserNameAndManager(String username) {
        return managerStudentDao.getUserByUserNameAndManager(username);
    }

    @Override
    public void insertSysManager_Student(String userId) {
        managerStudentDao.insertSysManager_Student(userId);
    }
}
