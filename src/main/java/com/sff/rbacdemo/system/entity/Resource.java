package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_resource")
public class Resource extends BaseEntity {

	private static final long serialVersionUID = 7187628714679791771L;

	@JsonSerialize(using = ToStringSerializer.class)
	@TableId(value = "RESOURCE_ID", type = IdType.ASSIGN_ID)
	private Long resourceId;

	@TableField("PARENT_ID")
	private Long parentId;

	@TableField("RESOURCE_NAME")
	private String resourceName;

	@TableField("PATH")
	private String path;

	@TableField("COMPONENT")
	private String component;

	@TableField("REAL_PATH")
	private String realPath;

	@TableField("REDIRECT")
	private String redirect;

	@TableField("CURRENT_ACTIVE_MENU")
	private String currentActiveMenu;

	@TableField("PERMS")
	private String perms;

	@TableField("ICON")
	private String icon;

	@TableField("TYPE")
	private String type;

	@TableField("ORDER_NO")
	private int orderNo;

	@TableField("STATUS")
	private String status;

	@TableField("IS_EXT")
	private boolean isExt;

	@TableField("KEEP_ALIVE")
	private boolean keepalive;

	@TableField("IS_SHOW")
	private boolean isShow;

}