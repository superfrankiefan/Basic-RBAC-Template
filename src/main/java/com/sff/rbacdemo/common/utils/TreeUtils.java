package com.sff.rbacdemo.common.utils;

import com.sff.rbacdemo.common.model.TreeModel;
import com.sff.rbacdemo.common.properties.GlobalConstant;

import java.util.*;

public class TreeUtils {

	protected TreeUtils(){

	}
	
	public static <T> TreeModel<T> build(List<TreeModel<T>> nodes) {
		if (nodes == null) {
			return null;
		}
		List<TreeModel<T>> topNodes = new ArrayList<>();
		nodes.forEach(child -> {
			String pid = child.getParentId();
			if (pid == null || "100".equals(pid)) {
				topNodes.add(child);
				return;
			}
			for (TreeModel<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(child);
					child.setHasParent(true);
					parent.setHasChildren(true);
					return;
				}
			}
		});

		TreeModel<T> root = new TreeModel<>();
		root.setId("100");
		root.setOrderNo(1);
		root.setCode("ROOT");
		root.setHasParent(false);
		root.setHasChildren(true);
		root.setRemark("ROOT");
		root.setChildren(topNodes);
		root.setText("根节点");
		root.setCreateBy("admin");
		root.setCreateTime(new Date().toString());
		root.setUpdateBy("admin");
		root.setUpdateTime(new Date().toString());
		root.setStatus(GlobalConstant.STATUS_VALID);
		return root;
	}

	public static <T> List<TreeModel<T>> buildList(List<TreeModel<T>> nodes, String idParam) {
		if (nodes == null) {
			return new ArrayList<>();
		}
		List<TreeModel<T>> topNodes = new ArrayList<>();
		nodes.forEach(child -> {
			String pid = child.getParentId();
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(child);
				return;
			}
			nodes.forEach(parent -> {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					if (parent.getChildren() == null){
						parent.setChildren(new ArrayList<>());
					}
					parent.getChildren().add(child);
					child.setHasParent(true);
					parent.setHasChildren(true);
				}
			});
		});
		return topNodes;
	}
}