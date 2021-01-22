package com.Digital_Orientation_System.dao;

import com.Digital_Orientation_System.entity.Manager_Stduent;
import com.Digital_Orientation_System.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.vo.SysRoleAndPermissionVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Repository
public interface ManagerStudentDao extends BaseMapper<Manager_Stduent> {
//    @Select("SELECT \n" +
//            "    t1.user_id as id, \n" +
//            "    t1.user_name as name, \n" +
//            "    t2.role_id \n" +
//            "FROM\n" +
//            "    sys_user_table t1\n" +
//            "        LEFT JOIN\n" +
//            "    (SELECT \n" +
//            "        a.user_id, a.user_name, b.role_id, b.role_name\n" +
//            "    FROM\n" +
//            "        sys_user_table a\n" +
//            "    JOIN (sys_role_table b\n" +
//            "    JOIN manager_student c ON c.role_id = b.role_id) ON c.user_id = a.user_id\n" +
//            "    WHERE\n" +
//            "        b.role_id = #{roleId}) t2 ON t2.user_id = t1.user_id")
    /**
     * 根据roleId找用户以及用户是否被设置在某个角色上，用在显示在用于角色设置处
     */
    @Select("SELECT user_name,NAME,college,college,major,classes,role_id FROM manager_student,sys_user_table WHERE role_id = #{roleId} AND manager_student.user_id = sys_user_table.user_id\n")
    List<SysRoleAndPermissionVo> getRoleAndUserList(@Param("roleId") String roleId);
    @Select("SELECT * FROM sys_user_table WHERE user_id IN(SELECT user_id FROM manager_student WHERE role_id IN(SELECT role_id FROM sys_role_user_table WHERE user_id IN (SELECT user_id FROM sys_user_table WHERE user_name = #{username})));")
    List<SysUserEntity> getUserByUserNameAndManager(@RequestParam String username);//通过管理员的username获取所管理的用户
    @Insert("insert into manager_student (role_id,user_id) values('1',#{userId})")
    void insertSysManager_Student(String userId);//系统管理员管理所有用户在manager_student表中

}
