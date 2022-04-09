package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Frankie Fan
 * @date 2022-04-09 17:07
 */

@Data
public class UserQueryParam implements Serializable {
    private String username;
    private String realName;
    private String deptCode;
}
