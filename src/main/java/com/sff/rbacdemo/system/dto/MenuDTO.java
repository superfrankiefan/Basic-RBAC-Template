package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Frankie Fan
 * @date 2022-03-16 09:57
 */

@Data
public class MenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private long parentId;

    private String icon;

    private String component;

    private String type;

    private String menuName;

    private String permission;

    private int orderNo;

    private String createTime;

    private int status;

    private List<MenuDTO> children;

}
