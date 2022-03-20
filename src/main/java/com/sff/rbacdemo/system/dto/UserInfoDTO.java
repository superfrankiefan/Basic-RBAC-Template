package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Frankie Fan
 * @date 2022-03-13 16:23
 */

@Data
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = -5680235862276163462L;

    private Long userId;

    private String userName;

    private String realName;

    private String avatar;

    private String desc;

    private Long roleId;

    private String roleCode;

    private String roleName;

    private String remark;

    private List<RoleInfoDTO> roles;

}
