package com.sff.rbacdemo.system_old.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system_old.entity.SysRole;

/**
 * <p>
 * 角色表 服务类
 * </p>
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 导入 excel ，检查 roleCode 的唯一性
     *
     * @param file
     * @param params
     * @return
     * @throws Exception
     */
//    Result importExcelCheckRoleCode(MultipartFile file, ImportParams params) throws Exception;

    /**
     * 删除角色
     *
     * @param roleid
     * @return
     */
    public boolean deleteRole(String roleid);

    /**
     * 批量删除角色
     *
     * @param roleids
     * @return
     */
    public boolean deleteBatchRole(String[] roleids);

}
