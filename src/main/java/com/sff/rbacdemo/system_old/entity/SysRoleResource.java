package com.sff.rbacdemo.system_old.entity;

import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleResource extends BaseEntity {
    
    /**
     * 角色id
     */
    private String roleId;

    /**
     * 资源id
     */
    private String resourceId;

    public SysRoleResource(String roleId, String p) {
    }
}
