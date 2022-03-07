package com.sff.rbacdemo.system_old.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends BaseEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 工号，唯一键
     */
    private String workNo;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;


    /**
     * 性别（1：男 2：女 3: 未知）
     */
    private int gender;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 管理部门
     */
    private String departIds;

    /**
     * 状态(1：正常  2：冻结 ）
     */
    private Integer status;

    /**
     * 删除状态（0，正常，1已删除）
     */
    @TableLogic
    private Integer delFlag;
}
