package com.sff.rbacdemo.common.model;

import com.sff.rbacdemo.common.properties.GlobalConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * @author frankie fan
 * @date 2022-03-07
 * 接口返回数据格式
 */
@Data
public class APIResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 响应类型
     */
    private String type;

    /**
     * 返回处理消息
     */
    private String message;

    /**
     * 返回数据对象 data
     */
    private T result;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public APIResponse() {

    }

    public APIResponse<T> success(String message) {
        this.message = message;
        this.code = GlobalConstant.HTTP_200;
        this.type = GlobalConstant.RES_OK;
        return this;
    }

    public APIResponse<T> internalError(String message) {
        this.message = message;
        this.code = GlobalConstant.HTTP_500;
        this.type = GlobalConstant.RES_ERROR;
        return this;
    }

    public APIResponse<T> noAuth(String message) {
        this.message = message;
        this.code = GlobalConstant.HTTP_401;
        this.type = GlobalConstant.RES_ERROR;
        return this;
    }

    public static <T> APIResponse<T> OK(String msg, T data) {
        APIResponse<T> r = new APIResponse<T>();
        r.setType(GlobalConstant.RES_OK);
        r.setCode(GlobalConstant.HTTP_200);
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }

    public static <T> APIResponse<T> ERROR(int code, String msg, T data) {
        APIResponse<T> r = new APIResponse<T>();
        r.setType(GlobalConstant.RES_ERROR);
        r.setCode(code);
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }

//    public static APIResponse<Object> ERROR(String msg) {
//        return ERROR(CommonConstant.SVC_INTERNAL_SERVER_ERROR, msg);
//    }
//
//    public static APIResponse<Object> ERROR(int code, String msg) {
//        APIResponse<Object> r = new APIResponse<Object>();
//        r.setCode(code);
//        r.setMessage(msg);
//        r.setSuccess(false);
//        return r;
//    }

//    public static <T> APIResponse<T> OK() {
//        APIResponse<T> r = new APIResponse<T>();
//        r.setSuccess(true);
//        r.setCode(CommonConstant.SVC_OK);
//        r.setMessage("成功");
//        return r;
//    }
//
//    public static <T> APIResponse<T> OK(T data) {
//        APIResponse<T> r = new APIResponse<T>();
//        r.setSuccess(true);
//        r.setCode(CommonConstant.SVC_OK);
//        r.setResult(data);
//        return r;
//    }

}