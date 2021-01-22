package com.Digital_Orientation_System.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("reportnotice")
public class Reportnotice extends Model<Reportnotice> {
    private int id;
    private String title;
    private String tvalues;
}
