package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_log")
public class SysLog extends BaseEntity {

	private static final long serialVersionUID = -8878596941954995444L;

	@TableId(type = IdType.ASSIGN_ID)
	@TableField("ID")
	private Long id;

	@TableField("USERNAME")
//	@ExportConfig(value = "操作用户")
	private String username;

	@TableField("OPERATION")
//	@ExportConfig(value = "描述")
	private String operation;

	@TableField("TIME")
//	@ExportConfig(value = "耗时（毫秒）")
	private Long time;

	@TableField("METHOD")
//	@ExportConfig(value = "操作方法")
	private String method;

	@TableField("PARAMS")
//	@ExportConfig(value = "参数")
	private String params;

	@TableField("IP")
//	@ExportConfig(value = "IP地址")
	private String ip;

	@TableField("LOCATION")
//	@ExportConfig(value = "地点")
	private String location;
	
	// 用于搜索条件中的时间字段
	private transient String timeField;

}