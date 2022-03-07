package com.sff.rbacdemo.system_old.enums;


/**
 * @author frankie fan
 * @date 2022-03-05 17:48
 */

public enum MenuType {
    MENU("0"),
    BUTTON("1");

    private String code;

    private MenuType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
