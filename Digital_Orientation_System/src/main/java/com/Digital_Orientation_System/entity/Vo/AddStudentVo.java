package com.Digital_Orientation_System.entity.Vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AddStudentVo {
    @TableId(type = IdType.UUID)
    String id;
    String username;
    String password="000000";//默认密码为000000
    String description="学生";//添加学生
    int state=1;//账号状态默认为1
    String name;
    String sex;
    String nation;
    String college;
    String major;
    String grade;
    String classes;
    String drom;
    String xuezhi;
    String studytype;
    String politics;
    String studystate;
    String instructname;
    String instructphone;
    String birth;
    String studenttype;

}
