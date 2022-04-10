package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system.entity.RoleResource;

/**
 * @author frankie fan
 */
public interface RoleResourceServie extends IService<RoleResource> {

    /**
     * 根据角色编码删除记录
     * @param roleCodes
     */
    void deleteRoleResourcesByRoleCodes(String roleCodes);

    /**
     * 根据资源ID删除记录
     * @param resourceIds
     */
    void deleteRoleResourcesByResourceIds(String resourceIds);
}
