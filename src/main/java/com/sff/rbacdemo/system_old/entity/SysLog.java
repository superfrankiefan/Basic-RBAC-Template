package com.sff.rbacdemo.system_old.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog implements Serializable {

	private static final long serialVersionUID = -8878596941954995444L;

	@TableId(value = "ID", type = IdType.ASSIGN_ID)
	private Long id;

	@TableField("USERNAME")
	private String username;

	@TableField("OPERATION")
	private String operation;

	@TableField("TIME")
	private Long time;

	@TableField("METHOD")
	private String method;

	@TableField("PARAMS")
	private String params;

	@TableField("IP")
	private String ip;

	@TableField("CREATE_TIME")
	private Date createTime;

	@TableField("LOCATION")
	private String location;

}