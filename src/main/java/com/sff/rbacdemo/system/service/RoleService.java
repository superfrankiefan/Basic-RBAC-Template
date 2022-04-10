package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.system.dto.RoleAndMenus;
import com.sff.rbacdemo.system.dto.RoleWithResource;
import com.sff.rbacdemo.system.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author frankie fan
 */
public interface RoleService extends IService<Role> {

	List<Role> findUserRole(String userName);

	/**
	 * 分页获取角色信息
	 *
	 * @param roleName
	 * @param page
	 * @param count
	 * @return
	 */
	PageResponseDTO<Role> getRoleByPage(String roleName, String status, Integer page, Integer count);

	/**
	 * 获取所有角色，根据状态进行筛选
	 *
	 * @param status
	 * @return
	 */
	List<Role> getAllRole(int status);


	/**
	 * 获取角色菜单
	 *
	 * @param roleCode
	 * @return
	 */
	String[] getRoleMenus(String roleCode);

	/**
	 * 设置角色状态
	 *
	 * @param roleCode
	 * @param status
	 */
	void setRoleStatus(String roleCode, String status);

	/**
	 * 根据RoleCode查询
	 * @param roleCode
	 * @return
	 */
	Role findByRoleCode(String roleCode);

	/**
	 * 新增角色
	 *
	 * @param roleAndMenus
	 */
	void addRole(RoleAndMenus roleAndMenus);

	/**
	 * 更新角色
	 *
	 * @param roleAndMenus
	 */
	void updateRole(RoleAndMenus roleAndMenus);

	/**
	 * 删除角色
	 *
	 * @param roleIds
	 */
	void deleteRoles(String roleIds);
}
