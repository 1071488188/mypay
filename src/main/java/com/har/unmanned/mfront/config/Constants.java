package com.har.unmanned.mfront.config;

/**
 * 常量
 */
public class Constants {

    private Constants(){}

    public static final String Topic = "har-business-front";

    // ######################系统常量############################
    public static final String BLANK_STRING = "";

    public static final String SESSION_KEY = "user";

    public static final String SCOPR_USERINFO = "snsapi_userinfo";

    public static final String SCOPR_base = "snsapi_base";

    public static final String ACCESS_TOKEN = "access_token";
    // ######################系统常量############################

    // ######################配置文件路径############################
    public static final String APP_CONFIG = "app_config";

    public static final String WX_CONFIG = "wx_config";
    // ######################配置文件路径############################

    /**
     * 存在头部信息中的常量key
     */
    public static interface constantHead{
        /*** 当前url*/
        public static String CURRENTURL="currenturl";
    }

}
