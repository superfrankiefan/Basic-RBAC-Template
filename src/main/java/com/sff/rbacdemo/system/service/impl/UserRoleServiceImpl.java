package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.system.entity.UserRole;
import com.sff.rbacdemo.system.mapper.UserRoleMapper;
import com.sff.rbacdemo.system.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author frankie fan
 */
@Service("userRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	@Transactional
	public void deleteUserRolesByRoleId(String roleIds) {
		List<String> list = Arrays.asList(roleIds.split(","));
		list.stream().forEach(s -> this.userRoleMapper.deleteByRoleId(Long.valueOf(s)));
	}

	@Override
	@Transactional
	public void deleteUserRolesByUserId(String userIds) {
		List<String> list = Arrays.asList(userIds.split(","));
		list.stream().forEach(s -> this.userRoleMapper.deleteByUserId(Long.valueOf(s)));
	}

}
