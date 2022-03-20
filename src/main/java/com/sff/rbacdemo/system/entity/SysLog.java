package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = -8878596941954995444L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("USERNAME")
    private String username;

    @TableField("OPERATION")
    private String operation;

    @TableField("TIME")
    private Long time;

    @TableField("METHOD")
    private String method;

    @TableField("PARAMS")
    private String params;

    @TableField("IP")
    private String ip;

    @TableField("LOCATION")
    private String location;

    @TableField("CREATE_TIME")
    private Date createTime;

    // 用于搜索条件中的时间字段
    private transient String timeField;

}