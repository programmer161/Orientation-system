package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.SysRoleUserTable;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * (SysRoleUserTable)表数据库访问层
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-08 23:03:13
 */
@Repository
public interface SysRoleUserTableDao extends BaseMapper<SysRoleUserTable> {
    @Update("insert into sys_role_user_table (role_id,user_id) values ('3',#{userId}) ")
    void saveRoleStudent(String userId);//初始用户为学生角色
    @Update("insert into sys_role_user_table (role_id,user_id) values('2',#{userId})")
    void saveRoleManager(String userId);//初始用户为管理员角色
}