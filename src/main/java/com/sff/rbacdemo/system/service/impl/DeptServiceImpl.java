package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.common.model.TreeModel;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.entity.Dept;
import com.sff.rbacdemo.system.mapper.DeptMapper;
import com.sff.rbacdemo.system.service.DeptService;
import com.sff.rbacdemo.common.utils.TreeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author frankie fan
 */
@Service("deptService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
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
			tree.setCreateTime(dept.getCreateTime().toString());
			tree.setUpdateBy(dept.getUpdateBy().toString());
			tree.setUpdateTime(dept.getUpdateTime().toString());
			trees.add(tree);
		});
		return TreeUtils.buildList(trees, GlobalConstant.ROOT_ID);
	}

	@Override
	public List<Dept> findAllDepts(Dept dept) {
		try {
			return this.deptMapper.selectAll();
		} catch (Exception e) {
			log.error("获取部门列表失败", e);
			return new ArrayList<>();
		}
	}

	@Override
	public Dept findByName(String deptName) {
		QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("deptName", deptName);
		return this.deptMapper.selectOne(queryWrapper);
	}

	@Override
	@Transactional
	public void addDept(Dept dept) {
		this.save(dept);
	}

	@Override
	@Transactional
	public void deleteDepts(String deptIds) {
		List<String> list = Arrays.asList(deptIds.split(","));
		this.deptMapper.deleteBatchIds(list);
		this.deptMapper.changeToTop(list);
	}

	@Override
	public Dept findById(Long deptId) {
		return this.deptMapper.selectById(deptId);
	}

	@Override
	@Transactional
	public void updateDept(Dept dept) {
		this.deptMapper.updateById(dept);
	}

}
