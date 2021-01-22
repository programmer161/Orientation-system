package com.Digital_Orientation_System.service.impl;

import com.Digital_Orientation_System.service.SysRoleTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Digital_Orientation_System.dao.SysRoleTableDao;
import com.Digital_Orientation_System.entity.SysRoleTable;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysRoleTable)表服务实现类
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-07 14:31:50
 */
@Service("sysRoleTableService")
public class SysRoleTableServiceImpl extends ServiceImpl<SysRoleTableDao, SysRoleTable> implements SysRoleTableService {
    @Autowired
    SysRoleTableDao sysRoleTableDao;
    /**
     * 根据用户名称查询角色
     * @param userName
     * @return
     */
    @Override
    public List<String> getRolesByUserName(String userName){
       return this.baseMapper.getRolesByUserName(userName);
    }

    /**
     * 根据roleId找用户以及用户是否被设置在某个角色上，用在显示在用于角色设置处
     * @param roleId
     * @return
     */
    @Override
    public List<SysRoleAndPermissionVo> getRoleAndUserList(String roleId){
      return this.baseMapper.getRoleAndUserList(roleId);
    }

    /**
     * 根据roleId找菜单
     */
    @Override
    public List<SysRoleAndPermissionVo> getRoleAndMenuList(String roleId)
             {
            return this.baseMapper.getRoleAndMenuList(roleId);
    }

    /**
     * 根据roleId找API
     */
    @Override
    public List<SysRoleAndPermissionVo> getRoleAndApiList(String roleId){
        return this.baseMapper.getRoleAndApiList(roleId);
    }

    @Override
    public List<SysRoleTable> getRoleAll() {
        return sysRoleTableDao.getRoleAll();
    }


    @Override
    public SysRoleTable getCollegeMC(String roleId) {
        return sysRoleTableDao.getCollegeMC(roleId);
    }

    @Override
    public void updateCollegeMC(SysRoleTable sysRoleTable) {
        sysRoleTableDao.updateCollegeMC(sysRoleTable);
    }
}