package com.Digital_Orientation_System.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("collegemajor")
public class Collegemajor extends Model<Collegemajor> {
    private int id;
    private String college;//学院
    private String major;//专业
    private String classes;//班级
}
