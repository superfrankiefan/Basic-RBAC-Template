package com.sff.rbacdemo.system_old.entity;

import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole extends BaseEntity {
    
    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    public SysUserRole(String id, String roleId) {
    }

    public SysUserRole() {

    }
}
