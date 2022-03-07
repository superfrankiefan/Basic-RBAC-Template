package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -4852732617765810959L;
    /**
     * 账户状态
     */
    public static final String STATUS_VALID = "1";

    public static final String STATUS_LOCK = "0";

    public static final String DEFAULT_THEME = "green";

    public static final String DEFAULT_AVATAR = "default.jpg";

    /**
     * 性别
     */
    public static final String SEX_MALE = "0";

    public static final String SEX_FEMALE = "1";

    public static final String SEX_UNKNOW = "2";

    @TableId(value = "USER_ID", type = IdType.ASSIGN_ID)
    private Long userId;

    @TableField("USERNAME")
    private String username;

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
    private String status = STATUS_VALID;

    @TableField("GENDER")
    private String gender;

    @TableField("THEME")
    private String theme;

    @TableField("AVATAR")
    private String avatar;

    @TableField("DESCRIPTION")
    private String description;

    // 非表字段
    private transient String roleName;

    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;
}
