package com.sff.rbacdemo.common.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

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
     * 菜单使用的字段
     * 1、图标
     * 2、组件/路径
     * 3、权限标识
     * 4、外部链接
     * 5、是否缓存页面
     * 6、是否显示菜单
     * 7、隐藏子菜单
     * 8、动态路由允许打开的最大页签
     * 9、动态路由的实际Path, 即去除路由的动态部分;
     * 10、实际路由
     * 11、当前激活的菜单。用于配置详情页时左侧激活的菜单路径
     * 12、路由重定向
     */
    private String icon;
    private String path;
    private String component;
    private String perms;
    private boolean isExt;
    private boolean keepalive;
    private boolean isShow;
    private String realPath;
    private String currentActiveMenu;
    private String redirect;

    /**
     * 审计字段
     */
    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

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