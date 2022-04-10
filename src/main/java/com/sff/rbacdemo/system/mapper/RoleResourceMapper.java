package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.entity.RoleResource;

public interface RoleResourceMapper extends BaseMapper<RoleResource> {


    /**
     * 获取角色对应的资源ID
     * @param roleCode
     * @return
     */
    public String[] getResourceIds(String roleCode);

    /**
     * 根据角色ID删除角色资源
     * @param roleCode
     * @return
     */
    public int deleteByRoleCode(String roleCode);

    /**
     * 根据资源ID删除角色资源
     * @param resourceId
     * @return
     */
    public int deleteByResourceId(Long resourceId);

}