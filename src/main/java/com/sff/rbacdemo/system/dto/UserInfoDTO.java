package com.sff.rbacdemo.system.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @JsonSerialize(using = ToStringSerializer.class) // 解决前后端Long类型数值精度问题
    private Long userId;

    private String userName;

    private String realName;

    private String password;

    private String workNo;

    private String avatar;

    private String email;

    private String deptCode;

    @JsonSerialize(using = ToStringSerializer.class) // 解决前后端Long类型数值精度问题
    private Long roleId;

    private String roleCode;

    private String roleName;

    private String remark;

    private String roleCodes;

    private String deptName;

    private List<RoleInfoDTO> roles;

}
