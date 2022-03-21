package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.system.dto.RoleWithResource;
import com.sff.rbacdemo.system.entity.Role;

import java.util.List;

/**
 * @author frankie fan
 */
public interface RoleService extends IService<Role> {

	List<Role> findUserRole(String userName);

	/**
	 * 分页获取角色信息
	 *
	 * @param status
	 * @param roleName
	 * @param page
	 * @param count
	 * @return
	 */
	PageResponseDTO<Role> getRoleByPage(int status, String roleName, Integer page, Integer count);
	
	RoleWithResource findRoleWithResources(Long roleId);

	Role findByName(String roleName);

	void addRole(Role role, Long[] resourceIds);
	
	void updateRole(Role role, Long[] resourceIds);

	void deleteRoles(String roleIds);
}
