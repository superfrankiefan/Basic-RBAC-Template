package com.sff.rbacdemo.common.enums;

public interface CommonConstant {

	/**
	 * 正常状态
	 */
	public static final Integer STATUS_NORMAL = 0;

	/**
	 * 禁用状态
	 */
	public static final Integer STATUS_DISABLE = -1;

	/**
	 * 删除标志
	 */
	public static final Integer DEL_FLAG_1 = 1;

	/**
	 * 未删除
	 */
	public static final Integer DEL_FLAG_0 = 0;
	
	
	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;
    
    /**访问权限认证未通过 510*/
    public static final Integer SC_JEECG_NO_AUTHZ=510;

    /** Token缓存时间：3600秒即一小时 */
    public static final int  TOKEN_EXPIRE_TIME  = 3600;


    /**
     * 系统日志类型： 登录
     */
    public static final int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    public static final int LOG_TYPE_2 = 2;

    /**
     * 操作日志类型： 查询
     */
    public static final int OPERATE_TYPE_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    public static final int OPERATE_TYPE_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    public static final int OPERATE_TYPE_3 = 3;

    /**
     * 部门编码
     */
    public static final String DEPART = "org_num_role";

    /**
     * 操作日志类型： 删除
     */
    public static final int OPERATE_TYPE_4 = 4;

    /**
     * 操作日志类型： 倒入
     */
    public static final int OPERATE_TYPE_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    public static final int OPERATE_TYPE_6 = 6;

    /**
     *  0：一级菜单
     */
    public static final Integer MENU_TYPE_0  = 0;
   /**
    *  1：子菜单 
    */
    public static final Integer MENU_TYPE_1  = 1;
    /**
     *  2：按钮权限
     */
    public static final Integer MENU_TYPE_2  = 2;
    
    /**
     * 状态(0无效1有效)
     */
    public static final String STATUS_0 = "0";
    public static final String STATUS_1 = "1";
    
    /**
     * 是否配置菜单的数据权限 1是0否
     */
    public static final Integer RULE_FLAG_0 = 0;
    public static final Integer RULE_FLAG_1 = 1;

    /**
     * 是否用户已被冻结 1正常(解冻) 2冻结
     */
    public static final Integer USER_UNFREEZE = 1;
    public static final Integer USER_FREEZE = 2;
    
    /**字典翻译文本后缀*/
    public static final String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * online参数值设置（是：Y, 否：N）
     */
    public static final String ONLINE_PARAM_VAL_IS_TURE = "Y";
    public static final String ONLINE_PARAM_VAL_IS_FALSE = "N";

    /** sys_user 表 username 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_USER_USERNAME = "uniq_sys_user_username";
    /** sys_user 表 work_no 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_USER_WORK_NO = "uniq_sys_user_work_no";
    /** sys_user 表 phone 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_USER_PHONE = "uniq_sys_user_phone";
    /** sys_user 表 email 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_USER_EMAIL = "uniq_sys_user_email";
    /** sys_quartz_job 表 job_class_name 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_JOB_CLASS_NAME = "uniq_job_class_name";
    /** sys_position 表 code 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_CODE = "uniq_code";
    /** sys_role 表 code 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_SYS_ROLE_CODE = "uniq_sys_role_role_code";
    /** sys_depart 表 code 唯一键索引 */
    public static final String SQL_INDEX_UNIQ_DEPART_ORG_CODE = "uniq_depart_org_code";


    public final static String X_ACCESS_TOKEN = "X-Access-Token";
    public final static String X_SIGN = "X-Sign";
    public final static String X_TIMESTAMP = "X-TIMESTAMP";


}
