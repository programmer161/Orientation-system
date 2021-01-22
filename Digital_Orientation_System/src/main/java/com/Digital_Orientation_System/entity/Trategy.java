package com.Digital_Orientation_System.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("trategy")
public class Trategy extends Model<Trategy> {
    private int id;
    private String title;
    private String tvalues;
}
