package com.lcxbs.auth2.enums;


/**
 * oauth2 错误列表
 * 
 */

public enum AuthErrorEnum {

    /**
     * 无效的请求
     */
    INVALID_REQUEST(1000, "无效的请求"),

    /**
     * 不支持的grant_type
     */
    UNSUPPORTED_GRANT_TYPE(1001, "不支持的授权类型"),

    /**
     * 用户名为空
     */
    USERNAME_BLANK(1002, "用户名为空"),

    /**
     * 密码为空
     */
    PASSWORD_BLANK(1003, "密码为空"),

    /**
     * 用户不存在
     */
    USERNAME_NOT_FOUND(1004,"用户不存在"),

    /**
     * 密码错误
     */
    ACCOUNT_PASSWORD_ERROR(1005,"密码错误"),

    /**
     * 客户端凭证错误
     */
    INVALID_CLIENT(1006, "客户端凭证错误"),

    /**
     * token无效
     */
    TOKEN_INVALID(1007, "Token无效"),

    /**
     * access_token过期
     */
    TOKEN_EXPIRED(1008, "Token过期"),

    /**
     * 缺少请求参数
     */
    REQUEST_PARAMETER_MISSING(1009, "缺少请求参数"),


    /**
     * 权限不足
     */
    INSUFFICIENT_PERMISSIONS(1010,"对不起，功能权限不足，请先确认您已获得该功能授权！"),

    /**
     * token错误或过期
     */
    UNAUTHORIZED(1011,"对不起，会话过期，请重新登录！");

    /**
     * 名称
     * */
    private String name;

    /**
     * 类型
     * */
    private Integer code;


    private AuthErrorEnum(Integer code, String name) {
        this.name = name;
        this.code = code;
    }

    /**
     * 静态获取name方法
     * */
    public static String getName(Integer type) {
        for (AuthErrorEnum c : AuthErrorEnum.values()) {
            if (c.getCode().equals(type)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}