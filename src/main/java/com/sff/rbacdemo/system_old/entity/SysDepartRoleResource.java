package com.sff.rbacdemo.system_old.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@TableName("sys_depart_role_resource")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDepartRoleResource extends BaseEntity {

    /**
     * 部门id
     */
    private String departId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限id
     */
    private String resourceId;
}
