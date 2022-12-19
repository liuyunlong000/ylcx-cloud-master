package com.lcxbs.gateway.constant;

/**
 * @Description: 需要走解码过滤器的接口
 * @ClassName: NeedDecodeConstant
 * @Author: DING WEI
 * @Date: 2020/8/31 17:40
 * @Version: 1.0
 */
public class NeedDecodeConstant {

    /**
     * 云智囊登陆
     * @author DING WEI
     * @date 2020/8/31 17:44
     */
    public static final String UAA_LOGIN = "/uaa/oauth/token";

    /**
     * 租户(PORTAL_BASE|PORTAL_ZDZ|PORTAL_NK)
     * @author DING WEI
     * @date 2020/8/31 18:14
     */
    public static final String PORTAL_BASE_LOGIN = "/portal_base/user/login";
    public static final String PORTAL_ZDZ_LOGIN = "/portal_zdz/user/login";
    public static final String PORTAL_NK_LOGIN = "/portal_nk/user/login";

    public static final String PORTAL_BASE_USER_UPDATE_BY_FIELDS = "/portal_base/user/update_by_fields";
    public static final String PORTAL_ZDZ_USER_UPDATE_BY_FIELDS = "/portal_zdz/user/update_by_fields";
    public static final String PORTAL_NK_USER_UPDATE_BY_FIELDS = "/portal_nk/user/update_by_fields";

    public static final String PORTAL_BASE_USER_RESET_PASSWORD = "/portal_base/user/reset_password";
    public static final String PORTAL_ZDZ_USER_RESET_PASSWORD = "/portal_zdz/user/reset_password";
    public static final String PORTAL_NK_USER_RESET_PASSWORD = "/portal_nk/user/reset_password";

    /**
     * 用户(UC)
     * @author DING WEI
     * @date 2020/8/31 18:13
     */
    public static final String UC_SAVE = "/uc/user/save";
    public static final String UC_UPDATE = "/uc/user/update";
    public static final String UC_UPDATE_BY_FIELDS = "/uc/user/update_by_fields";
    public static final String UC_UPDATE_PASSWORD = "/uc/user/update/password";
    public static final String UC_UPDATE_PASSWORD_BY_PHONE = "/uc/user/update/password_phone";
    public static final String UC_UPDATE_ADMIN_SAVE = "/uc/user/admin_save";
    public static final String UC_UPDATE_ADMIN_UPDATE = "/uc/user/admin_update";

    /**
     * 设计器
     * @author DING WEI
     * @date 2020/8/31 18:13
     */
    public static final String DESIGN_DATASOURCE_SAVE = "/design/datasource/save";
    public static final String DESIGN_DATASOURCE_UPDATE = "/design/datasource/update";
    public static final String DESIGN_DATASOURCE_TEST_CONNECT = "/design/datasource/test_connect";
    public static final String DESIGN_DATASOURCE_CONNECT = "/design/datasource/connect";

    /**
     * 需要对密码 进行解码的接口
     * @author DING WEI
     * @date 2020/8/31 18:16
     */
    public static final String[] NEED_DECODE_URL = {
            UAA_LOGIN,
            UC_SAVE, UC_UPDATE, UC_UPDATE_BY_FIELDS, UC_UPDATE_PASSWORD, UC_UPDATE_PASSWORD_BY_PHONE, UC_UPDATE_ADMIN_SAVE, UC_UPDATE_ADMIN_UPDATE,
            PORTAL_BASE_LOGIN, PORTAL_ZDZ_LOGIN,PORTAL_NK_LOGIN,
            PORTAL_BASE_USER_UPDATE_BY_FIELDS, PORTAL_ZDZ_USER_UPDATE_BY_FIELDS, PORTAL_NK_USER_UPDATE_BY_FIELDS,
            PORTAL_BASE_USER_RESET_PASSWORD, PORTAL_ZDZ_USER_RESET_PASSWORD, PORTAL_NK_USER_RESET_PASSWORD,
            DESIGN_DATASOURCE_SAVE, DESIGN_DATASOURCE_UPDATE, DESIGN_DATASOURCE_TEST_CONNECT, DESIGN_DATASOURCE_CONNECT
    };

}
