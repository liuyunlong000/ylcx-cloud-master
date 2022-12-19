package com.lcxbs.utils;

/**
 * @Description: 计算长度工具类
 * @ClassName: LengthUtil
 * @Author: DING WEI
 * @Date: 2020/10/22 14:48
 * @Version: 1.0
 */
public class LengthUtil {

    /**
     * 计算字符串字节代销
     * @param str
     * @return
     * @author DING WEI
     * @date 2020/10/22 14:48
     * @version 1.0
     */
    public static int getStrLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int len = 0;
        for (int i = 0, j = str.length(); i < j; i++) {
            // UTF-8编码格式中文占三个字节，GBK编码格式 中文占两个字节
            len += (str.charAt(i) > 255 ? 3 : 1);
        }
        return len;
    }

}
