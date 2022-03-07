package com.sff.rbacdemo.common.model;

import lombok.Data;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:37
 */

@Data
public class ResponseDTO {

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public ResponseDTO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
