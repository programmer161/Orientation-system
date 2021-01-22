package com.Digital_Orientation_System.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 日志表
 * */
@Data
@TableName("mylog")
public class AdminLog implements Serializable {

    private static final long serialVersionUID = 7925874058046995566L;

    private int id;
    private String userId;//用户id 操作人ID
    private String userName;//用户名称 关联admin_user
    private String loginip;//登录ip
    private String type="INFO";//操作类型
    private String url; // 操作的url
    private String operation;//操作内容
    private String createtime;//操作时间
    private String remark;//备注
    private String methodName;//使用的方法
    private String params;//参数
    private String name;//姓名
    private String result;//返回值
/**生成getter和setter方法 */
}
