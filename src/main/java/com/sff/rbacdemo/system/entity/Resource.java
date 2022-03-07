package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_resource")
public class Resource extends BaseEntity {

	private static final long serialVersionUID = 7187628714679791771L;

	public static final String TYPE_RESOURCE = "0";

	public static final String TYPE_BUTTON = "1";

	@TableId(type = IdType.ASSIGN_ID)
	@TableField("RESOURCE_ID")
//	@ExportConfig(value = "编号")
	private Long resourceId;

	@TableField("PARENT_ID")
	private Long parentId;

	@TableField("RESOURCE_NAME")
//	@ExportConfig(value = "名称")
	private String resourceName;

	@TableField("URL")
//	@ExportConfig(value = "地址")
	private String url;

	@TableField("PERMS")
//	@ExportConfig(value = "权限标识")
	private String perms;

	@TableField("ICON")
//	@ExportConfig(value = "图标")
	private String icon;

	@TableField("TYPE")
//	@ExportConfig(value = "类型", convert = "s:0=菜单,1=按钮")
	private String type;

	@TableField("ORDER_NUM")
	private Long orderNum;

}