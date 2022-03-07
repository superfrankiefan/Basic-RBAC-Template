package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.entity.Dept;

import java.util.List;

/**
 * @author frankie fan
 * 
 */
public interface DeptMapper extends BaseMapper<Dept> {
	
	// 删除父节点，子节点变成顶级节点（根据实际业务调整）
	void changeToTop(List<String> deptIds);

	// 查询所有部门
	List<Dept> selectAll();
}