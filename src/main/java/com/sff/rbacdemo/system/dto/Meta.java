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

    private Boolean ignoreKeepAlive;

    private Boolean affix;

    private String icon;

    private String frameSrc;

    private String transitionName;

    private Boolean hideBreadcrumb;

    private Boolean hideChildrenInMenu;

    private Boolean carryParam;

    private Boolean single;

    private String currentActiveMenu;

    private Boolean hideTab;

    private Boolean hideMenu;

    private Boolean isLink;

    private Boolean hidePathForChildren;

}
