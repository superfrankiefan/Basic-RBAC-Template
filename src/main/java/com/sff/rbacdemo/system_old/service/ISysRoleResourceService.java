package com.sff.rbacdemo.system_old.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system_old.entity.SysRoleResource;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 */
public interface ISysRoleResourceService extends IService<SysRoleResource> {

    /**
     * 保存授权/先删后增
     *
     * @param roleId
     * @param resourceIds
     */
    public void saveRoleResource(String roleId, String resourceIds);

    /**
     * 保存授权 将上次的权限和这次作比较 差异处理提高效率
     *
     * @param roleId
     * @param resourceIds
     * @param lastresourceIds
     */
    public void saveRoleResource(String roleId, String resourceIds, String lastresourceIds);

}
