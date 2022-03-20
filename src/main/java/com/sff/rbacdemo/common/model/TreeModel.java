package com.sff.rbacdemo.common.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TreeModel<T> {
    /**
     * 节点ID
     */
    private String id;
    /**
     * 父ID
     */
    private String parentId;
    /**
     * 节点编码
     */
    private String code;
    /**
     * 显示节点文本
     */
    private String text;

    private String status;

    private String type;

    private int orderNo;

    private String remark;

    /**
     * 图标
     */
    private String icon;
    /**
     * url
     */
    private String url;
    /**
     * permission
     */
    private String perms;

    private String createBy;

    private String createTime;

    private String updateBy;

    private String updateTime;

    /**
     * 节点属性
     */
    private Map<String, Object> attributes;

    /**
     * 节点的子节点
     * = new ArrayList<>()
     */
    private List<TreeModel<T>> children;

    /**
     * 是否有父节点
     */
    private boolean hasParent = false;
    /**
     * 是否有子节点
     */
    private boolean hasChildren = false;


}