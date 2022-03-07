package com.sff.rbacdemo.common.enums;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:14
 * @description: http response code
 */

public enum ResponseStatus {

    SUCCESS(201),
    UNAUTH(401),
    ERROR(500);

    private Integer code;
    private ResponseStatus(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
