package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.dto.RoleWithResource;
import com.sff.rbacdemo.system.entity.Role;

import java.util.List;

/**
 * @author frankie fan
 */
public interface RoleMapper extends BaseMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithResource> findById(Long roleId);

	List<Role> findAll();
}