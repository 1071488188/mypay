package com.har.unmanned.mfront.utils;


import com.har.unmanned.mfront.config.Constants;

import java.text.DecimalFormat;

/**
 * 验证工具类
 *
 * @author lixu
 */
public class CheckUtil {

    /**
     * 传入的对象是否为null（String是否等于空串）
     *
     * @param obj java对象
     * @return 对象为null或者空：true，以外：false
     */
    public static boolean isNull(Object obj) {

        if (obj instanceof String) {
            return null == obj || Constants.BLANK_STRING.equals(obj);
        }

        return null == obj;
    }

    /**
     * 传入的两个String对象是否相等
     *
     * @param str1 String对象1
     * @param str2 String对象2
     * @return 两个对象相等：true，以外：false
     */
    public static boolean isEquals(String str1, String str2) {
        if ((isNull(str1) && isNull(str2))
                || (str1 != null && str1.equals(str2))
                || (str2 != null && str2.equals(str1))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保留两位有效数字
     * @param d
     * @return
     */
    public static String m2(Double d){
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(d);
    }
}
