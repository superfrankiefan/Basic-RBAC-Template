package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.entity.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户名批量删除记录
     * @param userName
     * @return
     */
    public int deleteByUserName(String userName);

    /**
     * 根据角色编码批量删除记录
     * @param roleCode
     * @return
     */
    public int deleteByRoleCode(String roleCode);
}