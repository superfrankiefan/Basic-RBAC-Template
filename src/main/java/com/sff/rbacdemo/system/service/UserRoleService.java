package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system.entity.UserRole;

/**
 * @author frankie fan
 */
public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
