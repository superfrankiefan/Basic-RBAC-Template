package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Frankie Fan
 * @date 2022-03-14 10:41
 */

@Data
public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private int orderNo;

    private String dynamicLevel;

    private String realPath;

    private boolean ignoreKeepAlive;

    private boolean affix;

    private String icon;

    private String frameSrc;

    private String transitionName;

    private boolean hideBreadcrumb;

    private boolean hideChildrenInMenu;

    private boolean carryParam;

    private boolean single;

    private String currentActiveMenu;

    private boolean hideTab;

    private boolean hideMenu;

    private boolean isLink;

    private boolean hidePathForChildren;

}
