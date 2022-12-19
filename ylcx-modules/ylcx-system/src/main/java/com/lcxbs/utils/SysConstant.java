package com.lcxbs.utils;

public class SysConstant {

    /**
     * 源资类型
     */
    public static class SYS_RESOURCE_RESOURCE_TYPE{
        /**
         * 目录
         */
        public static final Long DIRECTORY = 1L;
        /**
         * 菜单
         */
        public static final Long MENU = 2L;
        /**
         * 操作
         */
        public static final Long OPERATIONS = 3L;

    }

    /**
     * 启用标志
     */
    public static class SYS_DISABLE_FLAG{
        /**
         * 1启用
         */
        public static final Long ENABLE = 1L;
        /**
         * 0禁用
         */
        public static final Long DISABLE = 0L;

    }
    /**
     * 显示标志
     */
    public static class SYS_DISPLAY_FLAG{
        /**
         * 1启用
         */
        public static final Long SHOW = 1L;
        /**
         * 0禁用
         */
        public static final Long HIDE = 0L;

    }

    /**
     * 删除标志
     */
    public static class SYS_DELETE_FLAG{
        /**
         * 1删除
         */
        public static final Long DELETE = 1L;
        /**
         * 0未删除
         */
        public static final Long NOT_DELETE = 0L;

    }


    /**
     * 定时任务错误后执行策略
     */
    public static class SYS_TASK_CODE{
        /**
         * RMI 远程方法调用
         */
        public static final String LOOKUP_RMI = "rmi://";

        /**
         * LDAP 远程方法调用
         */
        public static final String LOOKUP_LDAP = "ldap://";


        /**
         * http请求
         */
        public static final String HTTP = "http://";

        /**
         * https请求
         */
        public static final String HTTPS = "https://";
        /**
         * 定时任务违规的字符
         */
        public static final String[] JOB_ERROR_STR = { "java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
                "org.springframework.jndi" };

    }

    /**
     * 用户更新类型1 为个人基本信息更新 2为用户管理更新
     */
    public static final String USER_UPDATE_TYPE_ONE ="1";
    public static final String USER_UPDATE_TYPE_TWO="2";
    //是否显示根节点 0 不显示 1显示
    public static final String SHOW_ROOT_NODE = "1";
    public static final String NOT_SHOW_ROOT_NODE = "0";

}
