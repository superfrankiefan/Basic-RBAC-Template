package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = -1714476694755654924L;

	@TableId(value = "ROLE_ID", type = IdType.ASSIGN_ID)
	private Long roleId;

	@TableField("ROLE_CODE")
	private String roleCode;

	@TableField("ROLE_NAME")
	private String roleName;

	@TableField("REMARK")
	private String remark;

	@TableField("STATUS")
	private String roleStatus;

	@TableField("ORDER_NO")
	private String orderNo;

}