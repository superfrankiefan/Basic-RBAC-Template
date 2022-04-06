package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.model.TreeModel;
import com.sff.rbacdemo.system.entity.Dept;

import java.util.List;

/**
 * @author frankie fan
 */
public interface DeptService extends IService<Dept> {

	/**
	 * 查询部门
	 * @param deptCode
	 * @param deptName
	 * @param status
	 * @return
	 */
	List<TreeModel<Dept>> getDeptTree(String deptCode, String deptName, int status);

	/**
	 * 根据部门编码获取部门信息
	 * @param deptCode
	 * @return
	 */
	Dept findByDeptCode(String deptCode);

	/**
	 * 增加新的部门
	 * @param dept
	 */
	void addDept(Dept dept);

	/**
	 * 更新部门信息
	 * @param dept
	 */
	void updateDept(Dept dept);

	void deleteDepts(String deptIds);
}
