package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.entity.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户ID批量删除记录
     * @param userId
     * @return
     */
    public int deleteByUserId(Long userId);

    /**
     * 根据角色ID批量删除记录
     * @param roleId
     * @return
     */
    public int deleteByRoleId(Long roleId);
}