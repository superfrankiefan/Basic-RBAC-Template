package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.entity.Resource;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {
	
//	List<Resource> findUserPermissions(String userName);
	
//	List<Resource> findUserResources(String userName);

//	List<Resource> findAll();

	/**
	 * 删除父节点，子节点变成顶级节点（根据实际业务调整）
	 * @param menuIds
	 */
	void changeToTop(List<String> menuIds);

}