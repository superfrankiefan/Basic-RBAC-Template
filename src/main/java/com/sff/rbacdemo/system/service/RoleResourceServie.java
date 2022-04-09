package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system.entity.RoleResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author frankie fan
 */
public interface RoleResourceServie extends IService<RoleResource> {

    /**
     * 根据角色ID删除记录
     * @param roleIds
     */
    void deleteRoleResourcesByRoleIds(String roleIds);

    /**
     * 根据资源ID删除记录
     * @param resourceIds
     */
    void deleteRoleResourcesByResourceIds(String resourceIds);
}
