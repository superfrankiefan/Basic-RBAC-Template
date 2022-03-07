package com.sff.rbacdemo.system_old.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 在线用户信息
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUserDTO {

	/**
	 * 登录人id
	 */
	private String id;

	/**
	 * 登录人账号
	 */
	private String username;

	/**
	 * 登录人名字
	 */
	private String realname;

	/**
	 * 登录人密码
	 */
	private String password;

     /**
      * 当前登录部门code
      */
    private String orgCode;
	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 性别（1：男 2：女）
	 */
	private Integer gender;

	/**
	 * 电子邮件
	 */
	private String email;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 状态(1：正常 2：冻结 ）
	 */
	private Integer status;
	
	private Integer delFlag;

	/**
	 * 创建时间
	 */
	private Date createTime;


	/**
	 * 管理部门ids
	 */
	private String departIds;


	/**
	 * 座机号
	 */
	private String telephone;

}
