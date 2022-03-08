package com.sff.rbacdemo.common.utils;

import com.sff.rbacdemo.common.model.TreeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils {

	protected TreeUtils(){

	}
	
	public static <T> TreeModel<T> build(List<TreeModel<T>> nodes) {
		if (nodes == null) {
			return null;
		}
		List<TreeModel<T>> topNodes = new ArrayList<>();
		nodes.forEach(children -> {
			String pid = children.getParentId();
			if (pid == null || "0".equals(pid)) {
				topNodes.add(children);
				return;
			}
			for (TreeModel<T> parent : nodes) {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
					return;
				}
			}
		});

		TreeModel<T> root = new TreeModel<>();
		root.setId("0");
		root.setParentId("");
		root.setHasParent(false);
		root.setChildren(true);
		root.setChecked(true);
		root.setChildren(topNodes);
		root.setText("根节点");
		Map<String, Object> state = new HashMap<>(16);
		state.put("opened", true);
		root.setState(state);
		return root;
	}

	public static <T> List<TreeModel<T>> buildList(List<TreeModel<T>> nodes, String idParam) {
		if (nodes == null) {
			return new ArrayList<>();
		}
		List<TreeModel<T>> topNodes = new ArrayList<>();
		nodes.forEach(children -> {
			String pid = children.getParentId();
			if (pid == null || idParam.equals(pid)) {
				topNodes.add(children);
				return;
			}
			nodes.forEach(parent -> {
				String id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setChildren(true);
				}
			});
		});
		return topNodes;
	}
}