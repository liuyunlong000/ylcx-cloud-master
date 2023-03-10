package com.lcxbs.utils;

import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 特殊字符检测工具（防止传入非法字符和sql注入攻击）
 */
public class IllegalStrFilterUtil {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(IllegalStrFilterUtil.class);

    private static final String REGX = "!|！|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))|）|_|——|(\\+)|＋|(\\|)|§ ";
    /**
     * 对常见的sql注入攻击进行拦截
     *
     * @param sInput
     * @return
     *  true 表示参数不存在SQL注入风险
     *  false 表示参数存在SQL注入风险
     */
    public static Boolean sqlStrFilter(String sInput) {
        if (sInput == null || sInput.trim().length() == 0) {
            return false;
        }
        sInput = sInput.toUpperCase();
        if (sInput.indexOf("DELETE") >= 0 || sInput.indexOf("ASCII") >= 0 || sInput.indexOf("UPDATE") >= 0 || sInput.indexOf("SELECT") >= 0
                || sInput.indexOf("'") >= 0 || sInput.indexOf("SUBSTR(") >= 0 || sInput.indexOf("COUNT(") >= 0 || sInput.indexOf(" OR ") >= 0
                || sInput.indexOf(" AND ") >= 0 || sInput.indexOf("DROP") >= 0 || sInput.indexOf("EXECUTE") >= 0 || sInput.indexOf("EXEC") >= 0
                || sInput.indexOf("TRUNCATE") >= 0 || sInput.indexOf("INTO") >= 0 || sInput.indexOf("DECLARE") >= 0 || sInput.indexOf("MASTER") >= 0) {
            Logger.error("该参数怎么SQL注入风险：sInput=" + sInput);
            return false;
        }
        return true;
    }
    /**
     * 对非法字符进行检测
     *
     * @param sInput
     * @return
     *  true 表示参数不包含非法字符
     *  false 表示参数包含非法字符
     */
    public static Boolean isIllegalStr(String sInput) {
        if (sInput == null || sInput.trim().length() == 0) {
            return false;
        }
        sInput = sInput.trim();
        Pattern compile = Pattern.compile(REGX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compile.matcher(sInput);
        return matcher.find();
    }
}