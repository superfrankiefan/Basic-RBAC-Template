package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Frankie Fan
 * @date 2022-03-13 13:10
 */

@Data
public class LoginResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String token;

}
