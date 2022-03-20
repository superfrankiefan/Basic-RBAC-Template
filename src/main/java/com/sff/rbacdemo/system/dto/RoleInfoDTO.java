package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Frankie Fan
 * @date 2022-03-13 16:36
 */

@Data
public class RoleInfoDTO implements Serializable {

    private static final long serialVersionUID = -5680235862276163462L;

    private String roleCode;

    private String roleName;

    private String remark;

}
