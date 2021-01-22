package com.Digital_Orientation_System.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Digital_Orientation_System.entity.SysUserEntity;

import com.Digital_Orientation_System.entity.Vo.StudentVo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    @Select("select user_name from sys_user_table where idnumber = #{idnumber}")
    String findSnoById(String idnumber);//通过身份证号查找学号
    @Select("select * from sys_user_table where idnumber = #{idnumber}")
    SysUserEntity findUserByIdnumber(String idnumber);//通过身份证号查找用户

    @Select("select * from sys_user_table where user_id=#{id} and pass_word=#{oldpwd}")
    SysUserEntity checkOldPwd(String id,String oldpwd);//验证旧密码是否正确
    //中间对第一次输入密码和第二次输入密码进行判断
//    @Update("update sys_user_table set pass_word = #{snewpwd} where user_id = #{id}")
//    void updatePwd(String id,String snewpwd);//修改密码

    @Select("SELECT * FROM sys_user_table WHERE info = '已完成' AND  reportnow = '已完成' AND reportinfo = '已完成' AND affair = '已完成'  AND pay='已完成'  AND user_id IN(SELECT user_id FROM manager_student WHERE role_id in(SELECT role_id FROM sys_role_user_table WHERE user_id in(SELECT user_id FROM sys_user_table WHERE user_name=#{username}))) \n")
    List<SysUserEntity> getFinished(String username);//获取已完成报到的学生信息
    @Select("SELECT * FROM sys_user_table WHERE  user_name NOT IN (SELECT user_name FROM sys_user_table WHERE info = '已完成' AND  reportnow = '已完成' AND reportinfo = '已完成' AND affair = '已完成'  AND pay='已完成') AND user_id IN(SELECT user_id FROM manager_student WHERE role_id in(SELECT role_id FROM sys_role_user_table WHERE user_id=(SELECT user_id FROM sys_user_table WHERE user_name=#{username})))  ")
    List<SysUserEntity> getUnFinished(String username);
    @Select("select count(*) as Sum from sys_user_table where user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))" )
    int getSum(String username);//获取所有学生人数
    @Select("select count(info) as Infocount from sys_user_table where info='已完成' and user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))")
    int getInfocount(String username);//信息确认人数
    @Select("select count(reportnow) as Reportnowcount from sys_user_table where reportnow='已完成' and user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))")
    int getReportnowcount(String username);//获取现场报道完成 人数
    @Select("select count(reportinfo) as Reportinfocount from sys_user_table where reportinfo='已完成' and user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))")
    int getReportinfocount(String username);//报道信息完成 人数
    @Select("select count(affair) as Affaircount from sys_user_table where affair='已完成' and user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))")
    int getAffaircount(String username);//事务完成 人数
    @Select("select count(pay) as Paycount from sys_user_table where pay='已完成' and user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))")
    int getPaycount(String username);//缴费完成 人数
    @Select("select count(*) as Finishedcount from sys_user_table where user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username}))) and info = '已完成' and reportnow = '已完成' and reportinfo = '已完成' and pay = '已完成'  and affair = '已完成'")
    int getFinishedcount(String username);//完成报到 人数
    @Select("select age,count(*) as Agecount from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3')  group by age ")
    List<StudentVo> getAgecount();//统计每个年龄的人数
    @Update("update sys_user_table set pay='已完成' where user_name = #{username}")
    void PayConfirm(String id);//确认缴费
    @Update("update sys_user_table set reportnow = '已完成' where user_name = #{username}")
    void ReportConfirm(String id);//确认报道
    @Select("SELECT college,major,user_name,user_id,NAME,photo,reportnow FROM sys_user_table WHERE user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username}))) AND user_name NOT IN (SELECT user_name FROM sys_user_table WHERE reportnow='已完成' )")
    List<SysUserEntity> getReportNow(String username);//获取所有管理的新生的现场报道
    @Select("SELECT NAME,college,major,user_name,user_id,photo FROM sys_user_table WHERE user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3')  AND user_name = #{username} AND user_name NOT IN(SELECT user_name FROM sys_user_table WHERE reportnow='已完成')")
    SysUserEntity getReportNowByUserName(String username);//通过id获取所有管理的新生的现场报道情况
    @Select("select college from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    String getCollege();//找学院
    @Select("select user_name from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    String getUserName();//找学院
    @Select("select major from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    String getMajor();//找学院
    @Select("select user_id from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    String getUserId();//找学院
    @Select("select college,name,major,user_id,user_name,paytype,payimg,pay from sys_user_table where user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username}))) and user_name not in (select user_name from sys_user_table where pay='已完成')")
//    @Select("select college,name,major,user_id,user_name,paytype,payimg,pay from sys_user_table where description = '学生' and user_name not in (select user_name from sys_user_table where pay='已完成')")
//    List<SysUserEntity> getPay();//获取学生缴费情况
    List<SysUserEntity> getPay(String username);//获取学生缴费情况
    @Select("select name,college,major,user_id,user_name,paytype,payimg from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3')  and user_name = #{username} and user_name not in (select user_name from sys_user_table where pay='已完成') ")
    SysUserEntity getPayByUserName(String username);//通过id获取学生缴费情况
    @Select("select * from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3')  and user_id = #{id}")
    SysUserEntity findStudentById(String id);//通过id查找学生
    @Select("select * from sys_user_table where user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))")
    List<SysUserEntity> findStudentAll(String username);//查找所有学生管理员对应的


    //---------------------------------------------------------------------------------------Sys_Manager
    @Select("SELECT COUNT(*) AS SUM FROM sys_user_table WHERE user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='2')")
    int getManagerSum();//获取管理员人数
    @Select("SELECT * FROM sys_user_table WHERE user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='2') ")
    List<SysUserEntity> getManagerAll();//获取所有管理员名单

//    @Insert("(intsert into sys_user_table (studystate,college,idnumber,classes,birth,instructname,instructphone,userName,sex,nation,major,grade,drom,studenttype,politics,studytype,xuezhi,description,state) values (#{studystate},#{college},#{idnumber},#{classes},#{birth},#{instructname},#{instructphone},#{userName},#{sex},#{nation},#{major},#{grade},#{drom},#{studenttype},#{politics},#{studytype},#{xuezhi},#{‘学生’},#{1})")
//    @Update("update sys_user_table set password='000000',description='学生',state=1 where ")
//    void setStudent(SysUserEntity sysUserEntity);//设置生成学生 的 password state description
    @Update("update sys_user_table set studystate = #{studystate},college= #{college},idnumber= #{idnumber},classes= #{classes},birth= #{birth},instructname= #{instructname},instructphone= #{instructphone},sex= #{sex},nation= #{nation},major= #{major},grade= #{grade},drom= #{drom},studenttype= #{studenttype},politics= #{politics},studytype= #{studytype},xuezhi= #{xuezhi} where userName = #{userName}")
    void addStudent(SysUserEntity sysUserEntity);//生成学生
    @Select("select * from sys_user_table where college = #{college} and user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    List<SysUserEntity> findByCollege(String college);//通过学院查找学生
    @Select("select * from sys_user_table where major = #{major} and user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    List<SysUserEntity> findByMajor(String major);//通过专业查找
    @Select("SELECT * FROM sys_user_table WHERE user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='2') AND user_name=#{username}")
    SysUserEntity findManagerByUserName(String username);//通过id查找manager


//    @Select("select * from sys_user_table where description = '学生' and ")
                                                                 //}
//    @Update("update sys_user_table set studystate=#{studystate},college=#{college},idnumber=#{idnumber},classes=#{classes},birth=#{birth},instructname=#{instructphone},user_name=#{userName},sex=#{sex},nation=#{nation},major=#{major},grade=#{grade},drom=#{drom},studenttype=#{studenttype},politics=#{politics},studytype=#{studytype},xuezhi=#{xuezhi} where user_id=#{userId} and description = '学生'")


    @Select("SELECT * FROM sys_user_table WHERE user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='2')" +
            "AND college=#{college}")
    List<SysUserEntity> findManagerByCollege(String college);//通过学院查找管理员
//    @Select("select * from sys_user_table where college = #{college}")
//    SysUserEntity getByCollege(String college);//判断学院是否存在
    @Select("select * from sys_user_table where user_name = #{username}")
    SysUserEntity getUserByUsername(String username);//通过用户名找用户

    @Update("update sys_user_table set pass_word=#{password} where user_name = #{username}")
    void updatePwd(String username, String password);//更改密码
    @Select("select * from sys_user_table where major=#{major} and user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    List<SysUserEntity> findStudentByManager(String major);//查找每个管理员对应的管理的学生信息  通过专业对应管理
    @Select("select name,phonenumber from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='2') and college = #{college}")
    List<SysUserEntity> getInstructByCollege(String college);//查辅导员姓名电话by college



    @Select("SELECT COUNT(sex) AS Mc FROM sys_user_table WHERE sex='男' and user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))")
    int getMnumber(String username);//获取男生人数
    @Select("SELECT COUNT(sex) AS FMc FROM sys_user_table WHERE sex='女' and user_id in(select user_id from manager_student where role_id in(select role_id from sys_role_user_table where user_id in(select user_id from sys_user_table where user_name=#{username})))")
    int getFMnumber(String username);//获取女生人数
    @Select("select * from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3')  and user_name=#{username}")
    SysUserEntity findStudentByUserName(String username);//通过username查找学生


    @Select("select user_id from sys_user_table where user_name = #{userName}")
    String getUserIdByUserName(String userName);//通过用户名找用户Id
    @Select("select name from sys_user_table where user_name = #{userName}")
    String getNameByUserName(String userName);//通过用户名找用户name
    @Select("select * from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3')  and classes = #{classes} and major = #{major}")
    List<SysUserEntity> getStudentByClasses(String major,String classes);//通过专业和班级查找学生

    @Update("update sys_user_table set name=#{name},phonenumber=#{phonenumber},sex=#{phonenumber},college=#{college},politics=#{politics},idnumber=#{idnumber},nation=#{nation},birth=#{birth} where user_name = #{userName} and user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='2')")
    void updateManager(SysUserEntity sysUserEntity);//修改管理员信息

    @Update("update sys_user_table set name=#{name},studystate=#{studystate},college=#{college},idnumber=#{idnumber},classes=#{classes},birth=#{birth},instructname=#{instructname},instructphone=#{instructphone},sex=#{sex},college=#{college},major=#{major},grade=#{grade},drom=#{drom},studenttype=#{studenttype},politics=#{politics},studytype=#{studytype},xuezhi=#{xuezhi} where user_name = #{userName} and user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    void updateStudent(SysUserEntity sysUserEntity);//修改学生信息

    @Select("select * from sys_user_table where info = '已完成' and  reportnow = '已完成' and reportinfo = '已完成' and affair = '已完成'  and pay='已完成'  and  user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    List<SysUserEntity> getFinishedSys();//获取已完成报到的学生信息
    @Select("SELECT * FROM sys_user_table WHERE  user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3')  AND user_name NOT IN (SELECT user_name FROM sys_user_table WHERE info = '已完成' AND  reportnow = '已完成' AND reportinfo = '已完成' AND affair = '已完成'  AND pay='已完成')   ")
    List<SysUserEntity> getUnFinishedSys();//获取为完成报到学生信息
    @Select("select * from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') ")
    List<SysUserEntity> getStudentAllSys();//获取所有学生信息

    @Select("select count(*) as Finishedcount from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3') and info = '已完成' and reportnow = '已完成' and reportinfo = '已完成' and pay = '已完成'  and affair = '已完成'")
    int getFinishedCountSys();//完成报到 人数
    @Select("select count(*) as Sum from sys_user_table where user_id IN(SELECT user_id FROM sys_role_user_table WHERE role_id='3')" )
    int getSumSys();//获取所有学生人数
}
