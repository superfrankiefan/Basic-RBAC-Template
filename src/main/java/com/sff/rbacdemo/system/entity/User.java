package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sff.rbacdemo.common.model.BaseEntity;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -4852732617765810959L;

    public static final String DEFAULT_AVATAR = "default.jpg";

    @JsonSerialize(using = ToStringSerializer.class) // 解决前后端Long类型数值精度问题
    @TableId(value = "USER_ID", type = IdType.ASSIGN_ID)
    private Long userId;

    @TableField("USERNAME")
    private String username;

    @TableField("WORK_NO")
    private String workNo;

    @TableField("REAL_NAME")
    private String realName;

    @TableField("PASSWORD")
    private String password;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("DEPT_CODE")
    private String deptCode;

    @TableField("EMAIL")
    private String email;

    @TableField("MOBILE")
    private String mobile;

    @TableField("STATUS")
    private String userStatus = GlobalConstant.STATUS_VALID;

    @TableField("GENDER")
    private String gender;

    @TableField("AVATAR")
    private String avatar;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;
}
