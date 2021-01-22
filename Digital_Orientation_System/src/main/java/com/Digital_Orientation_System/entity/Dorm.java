package com.Digital_Orientation_System.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dorm")
public class Dorm {
    String id;
    String number_apartments;
    String number_dorm;
}
