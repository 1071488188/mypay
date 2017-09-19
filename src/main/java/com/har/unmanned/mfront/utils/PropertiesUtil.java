package com.har.unmanned.mfront.utils;


import com.har.unmanned.mfront.config.Constants;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * 属性文件操作工具类。
 *
 * @author lixu
 */
@Slf4j
public final class PropertiesUtil {

    /**
     * Default constructor.
     */
    private PropertiesUtil() {

    }

    /**
     * 读取配置文件对应KEY的值
     *
     * @param path 配置文件的路径
     * @param key  配置文件的KEY
     * @return String 配置文件对应KEY的值
     */
    public static String getAppConfig(String path, String key) {

        return getParamValue(path, key);

    }

    /**
     * 读取配置文件对应KEY的值
     *
     * @param key 配置文件的KEY
     * @return String 配置文件对应KEY的值
     */
    public static String getAppConfig(String key) {

        return getParamValue(Constants.APP_CONFIG, key);

    }

    /**
     * 读取配置文件对应KEY的值，替换其中{0},{1}这样的参数为传入的参数 (从appconfig.properties文件中读取)
     *
     * @param key    配置文件的KEY
     * @param params 需要替换的值
     * @return String 配置文件的对应key中，参数被动态植入后的值
     */
    public static String getAppConfigWithParam(String path, String key, String[] params) {

        String value = getParamValue(path, key);
        return MessageFormat.format(value, (Object[]) params);

    }

    /**
     * 读取配置文件对应KEY的值，替换其中{0},{1}这样的参数为传入的参数 (从appconfig.properties文件中读取)
     *
     * @param key    配置文件的KEY
     * @param params 需要替换的值
     * @return String 配置文件的对应key中，参数被动态植入后的值
     */
    public static String getAppConfigWithParam(String key, String[] params) {

        String value = getParamValue(Constants.APP_CONFIG, key);
        return MessageFormat.format(value, (Object[]) params);

    }

    private static String getParamValue(String path, String key) {

        try {
            ResourceBundle resource = ResourceBundle.getBundle(path);
            return resource.getString(key);
        } catch (Exception e) {
            log.error("读取文件[" + path + "]信息出错:" + e.getMessage() + "[" + key + "]");
            return Constants.BLANK_STRING;
        }
    }
}
