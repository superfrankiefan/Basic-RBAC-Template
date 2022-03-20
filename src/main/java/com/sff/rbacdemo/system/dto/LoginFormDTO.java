package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Frankie Fan
 * @date 2022-03-11 17:50
 * 登陆请求参数
 */

@Data
public class LoginFormDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;
}
