package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.system.entity.RoleResource;
import com.sff.rbacdemo.system.mapper.RoleResourceMapper;
import com.sff.rbacdemo.system.service.RoleResourceServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("roleResourceService")
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceServie {

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public void deleteRoleResourcesByRoleIds(String roleIds) {
		List<String> list = Arrays.asList(roleIds.split(","));
		list.stream().forEach(s -> this.roleResourceMapper.deleteByRoleId(Long.valueOf(s)));
	}

	@Override
	public void deleteRoleResourcesByResourceIds(String resourceIds) {
		List<String> list = Arrays.asList(resourceIds.split(","));
		list.stream().forEach(s -> this.roleResourceMapper.deleteByResourceId(Long.valueOf(s)));
	}
}
