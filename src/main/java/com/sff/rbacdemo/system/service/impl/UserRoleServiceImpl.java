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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	@Transactional
	public void deleteUserRolesByRoleCode(String roleCodes) {
		List<String> list = Arrays.asList(roleCodes.split(","));
		list.stream().forEach(s -> this.userRoleMapper.deleteByRoleCode(s));
	}

	@Override
	@Transactional
	public void deleteUserRolesByUserName(String userNames) {
		List<String> list = Arrays.asList(userNames.split(","));
		list.stream().forEach(s -> this.userRoleMapper.deleteByUserName(s));
	}

}
