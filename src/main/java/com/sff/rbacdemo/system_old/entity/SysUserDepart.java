package com.sff.rbacdemo.system_old.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_depart")
public class SysUserDepart extends BaseEntity {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 部门id
     */
    private String depId;

    public SysUserDepart(String id, String deaprtId) {
    }
}
