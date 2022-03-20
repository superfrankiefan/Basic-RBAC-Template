package com.sff.rbacdemo.common.properties;

/**
 * @author frankie fan
 * @date 2022-03-07
 * 系统全局常量
 */
public interface GlobalConstant {

    /**
     * HTTP协议返回码
     */
    public static final Integer HTTP_500 = 500;
    public static final Integer HTTP_200 = 200;
    public static final Integer HTTP_401 = 401;
    public static final Integer TOKEN_EXPIRE = 411;
    public static final Integer TOKEN_INVALID = 410;

    /**
     * 业务返回类型
     */
    public static final String RES_OK = "success";
    public static final String RES_ERROR = "error";
    public static final String RES_WARN = "warning";

    /**
     * 应用资源类型
     */
    public static final String RES_TYPE_MENU = "0";
    public static final String RES_TYPE_BTN = "1";
    public static final String RES_TYPE_API = "2";
    public static final String RES_TYPE_CATALOG = "3";

    /**
     * 用户性别
     */
    public static final String SEX_MALE = "0";

    public static final String SEX_FEMALE = "1";

    public static final String SEX_UNKNOW = "2";

    /**
     * 状态
     */
    public static final String STATUS_VALID = "1";

    public static final String STATUS_LOCK = "0";

    public static final String STATUS_INVALID = "2";

    public static final String STATUS_DELETE = "-1";

    /**
     * Tree Root ID
     */
    public static final String ROOT_ID = "100000";

}
