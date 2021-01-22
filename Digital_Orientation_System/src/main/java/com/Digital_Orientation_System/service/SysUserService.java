package com.Digital_Orientation_System.service;

import com.Digital_Orientation_System.vo.SysUserVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Digital_Orientation_System.entity.SysUserEntity;

import com.Digital_Orientation_System.entity.Vo.AddStudentVo;
import com.Digital_Orientation_System.entity.Vo.StudentVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserService extends IService<SysUserEntity>  {

    /**
     * 通过账号查询用户
     * @param username
     * @return
     */
    SysUserEntity getUserByUserName(String username);

    /**
     * 个性化验证登录
     * @param username 账号
     * @param rawPassword 原始密码
     * @return
     */
    boolean checkLogin(String username,String rawPassword) throws Exception;

    /**
     * 注册
     * @param sysUserVo
     * @return
     * @throws Exception
     */
    boolean register(SysUserVo sysUserVo) throws Exception;

    SysUserEntity voToPo(SysUserVo vo);

    String findSnoById(String idnumber);//通过身份证号查找学号
    SysUserEntity findUserByIdnumber(String idnumber);//通过身份证号查找用户

    SysUserEntity checkOldPwd(String id,String oldpwd);//验证旧密码是否正确
    //中间对第一次输入密码和第二次输入密码进行判断

//    void updatePwd(String id,String snewpwd);//修改密码

    StudentVo getSumAndFinished(String username);//获取学生总人数和已完成报到的人数

    List<SysUserEntity> getReportNow(String username);//获取所有管理的新生的现场报道情况 student

    StudentVo getReportStatus(String username);//获取所有管理的新生的现场报道情况 各个业务人数
//    SysUserEntity getReportNowById(String id);//通过id获取所有管理的新生的现场报道情况

    List<SysUserEntity> getPay(String username);//获取学生缴费情况
    //    SysUserEntity getPayById(String id);//通过id获取学生缴费情况
    SysUserEntity findStudentById(String id);//通过id查找学生
    List<SysUserEntity> findStudentAll(String username);//查找所有学生
    List<SysUserEntity> getFinished(String username);//查找完成报道的学生
    List<SysUserEntity> getUnFinished(String username);//查找未完成报道的学生
    void payConfirm(String username);//确认现场缴费
    void reportConfirm(String username);//确认现场报道
    List<StudentVo> getAgecount();//统计每个年龄的人数

    //---------------------------------------------------------------------------------------Sys_Manager

    int getManagerSum();//获取管理员人数
    List<SysUserEntity> getManagerAll();//获取所有管理员名单
    //    void addStudent(SysUserEntity sysUserEntity);//生成学生 系统管理员
    List<SysUserEntity> findByCollege(String college);//通过学院查找
    List<SysUserEntity> findByMajor(String major);//通过专业查找
    SysUserEntity findManagerByUserName(String username);//通过username查找manager
    void updateManager(SysUserEntity sysUserEntity);//修改管理员信息
    void updateStudent(SysUserEntity sysUserEntity);//修改学生信息
    List<SysUserEntity> findManagerByCollege(String college);//通过学院查找管理员
//    SysUserEntity getByCollege(String college);//判断学院是否存在

    //    void addStudent(AddStudentVo studentVo);
    void addStudent(AddStudentVo studentVo);//新增学生
    void deleteUser(String list);//删除用户
    SysUserEntity getUserByUsername(String username);//通过用户名找用户
    int updatePwd(String username, String password, String firstPassword ,String secondPassword);//更改密码
    List<SysUserEntity> findStudentByManager(String major);//查找每个管理员对应的管理的学生信息  通过专业对应管理
    List<SysUserEntity> getInstructByCollege(String college);//根据用户所填写的学院查找辅导员的姓名和辅导员的电话
    StudentVo getSexNumber(String username);//获取男女生人数
    SysUserEntity findStudentByUserName(String username);//通过username查找学生
    SysUserEntity getReportNowByUserName(String username);//通过username查找学生现场报到信息
    SysUserEntity getPayByUserName(String username);//通过username查找学生缴费信息
    List<SysUserEntity> getStudentByClasses(String major, String classes);//通过专业和班级查找学生
    void insertUser(SysUserEntity user);//增加用户
    List<SysUserEntity> getFinishedSys();//获取已完成报到的学生信息
    List<SysUserEntity> getUnFinishedSys();//获取为完成报到学生信息
    List<SysUserEntity> getStudentAllSys();//获取所有学生信息
    int getFinishedCountSys();//完成报到 人数
    int getSumSys();//获取所有学生人数



}
