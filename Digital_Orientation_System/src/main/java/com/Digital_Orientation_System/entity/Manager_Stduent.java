package com.Digital_Orientation_System.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("manager_student")//管理员角色对应管理的用户
public class Manager_Stduent extends Model<Manager_Stduent> {
    private int id;
    private String roleId;
    private String userId;
    private Integer sort;
}
