package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.common.model.TreeModel;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.common.utils.TreeUtils;
import com.sff.rbacdemo.system.entity.Dept;
import com.sff.rbacdemo.system.mapper.DeptMapper;
import com.sff.rbacdemo.system.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author frankie fan
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<TreeModel<Dept>> getDeptTree(String deptCode, String deptName, int status) {
		QueryWrapper queryWrapper = new QueryWrapper();
		if(deptCode != null && !deptCode.isEmpty()){
			queryWrapper.eq("DEPT_CODE", deptCode);
		}
		if(deptName != null && !deptName.isEmpty()){
			queryWrapper.like("DEPT_NAME", deptName);
		}
		queryWrapper.eq("STATUS", status);
		List<Dept> depts = this.deptMapper.selectList(queryWrapper);
		List<TreeModel<Dept>> trees = new ArrayList<>();
		depts.forEach(dept -> {
			TreeModel<Dept> tree = new TreeModel<>();
			tree.setId(dept.getDeptId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getDeptName());
			tree.setCode(dept.getDeptCode());
			tree.setOrderNo(dept.getOrderNo());
			tree.setRemark(dept.getRemark());
			tree.setStatus(dept.getStatus());
			tree.setCreateBy(dept.getCreateBy().toString());
			tree.setCreateTime(dept.getCreateTime());
			tree.setUpdateBy(dept.getUpdateBy().toString());
			tree.setUpdateTime(dept.getUpdateTime());
			trees.add(tree);
		});
		return TreeUtils.buildList(trees, GlobalConstant.ROOT_ID);
	}

	@Override
	public void addDept(Dept dept) {
		this.deptMapper.insert(dept);
	}

	@Override
	public void updateDept(Dept dept) {
		this.deptMapper.updateById(dept);
	}

	@Override
	@Transactional
	public void deleteDepts(String deptIds) {
		List<String> list = Arrays.asList(deptIds.split(","));
		list.stream().forEach(s -> this.deptMapper.deleteById(Long.valueOf(s)));
		this.deptMapper.changeToTop(list);
	}

	@Override
	public Dept findByDeptCode(String deptCode) {
		QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("DEPT_CODE", deptCode);
		return this.deptMapper.selectOne(queryWrapper);
	}

}
