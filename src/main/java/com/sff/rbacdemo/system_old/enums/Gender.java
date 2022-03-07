package com.sff.rbacdemo.system_old.enums;

/**
 * @author Frankie Fan
 * @date 2022-03-05 5:24 PM
 */

public enum Gender {
    MALE("0"),
    FEMALE("1"),
    UNKNOW("3");

    private String code;

    private Gender(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
