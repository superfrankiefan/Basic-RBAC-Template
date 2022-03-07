package com.sff.rbacdemo.system_old.enums;

/**
 * @author Frankie Fan
 * @date 2022-03-05 5:23 PM
 * @description: user status
 */

public enum UserStatus {

    VALID("1"),
    INVALID("2"),
    BLOCKED("3");

    private String code;

    private UserStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}