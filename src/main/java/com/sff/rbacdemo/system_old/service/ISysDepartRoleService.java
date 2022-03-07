package com.sff.rbacdemo.system_old.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system_old.entity.SysDepartRole;

import java.util.List;

/**
 * @Description: 部门角色
 */
public interface ISysDepartRoleService extends IService<SysDepartRole> {

    /**
     * 根据用户id，部门id查询可授权所有部门角色
     *
     * @param orgCode
     * @param userId
     * @return
     */
    List<SysDepartRole> queryDeptRoleByDeptAndUser(String orgCode, String userId);

}
