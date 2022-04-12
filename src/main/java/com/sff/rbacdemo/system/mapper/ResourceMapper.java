package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.entity.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceMapper extends BaseMapper<Resource> {
	
	List<Resource> findUserPermissions(String userName);

	/**
	 * 获取用户的授权资源
	 * @param userName
	 * @return
	 */
	List<Resource> findUserResources(String userName);

	/**
	 * 获取资源的所有上曾节点
	 * @param resourceId
	 * @return
	 */
	List<Long> getAncestors(Long resourceId);

//	List<Resource> findAll();

	/**
	 * 删除父节点，子节点变成顶级节点（根据实际业务调整）
	 * @param menuIds
	 */
	void changeToTop(List<String> menuIds);

}