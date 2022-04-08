package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.dto.UserInfoDTO;
import com.sff.rbacdemo.system.dto.UserWithRole;
import com.sff.rbacdemo.system.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

	/**
	 * 查询包含所在部门的用户信息
	 * @param user
	 * @return
	 */
	List<User> findUserWithDept(User user);

	/**
	 * 查询用户角色信息
	 * @param userId
	 * @return
	 */
	List<UserWithRole> findUserWithRole(Long userId);

	/**
	 * 获取用户简要信息，包含角色
	 * @param userId
	 * @return
	 */
	List<UserInfoDTO> getUserInfo(Long userId);

}