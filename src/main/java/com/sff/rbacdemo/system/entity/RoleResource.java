package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_role_resource")
public class RoleResource extends BaseEntity {
	
	private static final long serialVersionUID = -7573904024872252113L;

	@TableField("ROLE_ID")
    private Long roleId;

    @TableField("RESOURCE_ID")
    private Long resourceId;
}