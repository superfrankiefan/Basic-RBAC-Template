package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -4852732617765810959L;

    public static final String DEFAULT_AVATAR = "default.jpg";

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

    @TableField("DEPT_ID")
    private Long deptId;

    //非表字段
    private transient String deptName;

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

    // 非表字段
    private transient String roleName;

    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;
}
