package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_user_role")
public class UserRole extends BaseEntity {
	
	private static final long serialVersionUID = -3166012934498268403L;

	@TableField("USERNAME")
	private String userName;

	@TableField("ROLE_CODE")
	private String roleCode;

}