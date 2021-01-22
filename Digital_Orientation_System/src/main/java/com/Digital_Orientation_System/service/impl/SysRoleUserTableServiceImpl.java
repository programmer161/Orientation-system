package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.service.SysRoleUserTableService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.dao.SysRoleUserTableDao;
import com.Digital_Orientation_System.entity.SysRoleUserTable;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * (SysRoleUserTable)表服务实现类
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-08 23:03:14
 */
@Service("sysRoleUserTableService")
public class SysRoleUserTableServiceImpl extends ServiceImpl<SysRoleUserTableDao, SysRoleUserTable> implements SysRoleUserTableService {

    @Autowired
    SysRoleUserTableDao sysRoleUserTableDao;
    /**
     * 批量保存角色-用户表
     * 该方法内有两个操作，先删除、后批量新增，因此存在事务控制
     * @param roleId
     * @param sysRoleAndPermissionVos
     * @return
     */
    @Override
    @Transactional
    public   boolean saveRoleUser(String roleId, SysRoleAndPermissionVo... sysRoleAndPermissionVos){

        System.out.println("roleId = " + roleId);

        //先删除数据
        this.delRoleId(roleId);
        //
        if(sysRoleAndPermissionVos !=null) {
            Set<SysRoleUserTable> set = new HashSet<>();
            SysRoleUserTable roleUser = null;
            for (SysRoleAndPermissionVo roleVo : sysRoleAndPermissionVos) {
                roleUser = new SysRoleUserTable();
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
        LambdaQueryWrapper<SysRoleUserTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRoleUserTable::getRoleId,roleId);
        return this.remove(lambdaQueryWrapper);
    }

    @Override
    public void saveRoleStudent(String userId) {
        sysRoleUserTableDao.saveRoleStudent(userId);
    }

    @Override
    public void saveRoleManager(String userId) {
        sysRoleUserTableDao.saveRoleManager(userId);
    }
}