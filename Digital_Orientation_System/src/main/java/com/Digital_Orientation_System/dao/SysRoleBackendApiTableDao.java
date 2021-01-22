package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.SysRoleBackendApiTable;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * (SysRoleBackendApiTable)表数据库访问层
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-09 12:11:56
 */

@Repository
public interface SysRoleBackendApiTableDao extends BaseMapper<SysRoleBackendApiTable> {
    @Insert("insert into sys_role_backend_api_table (role_id,backend_api_id) values (#{roleId},'1')   ")
    void saveRoleApi(String roleId);//新建角色 插入角色id 到 role_api表中
}