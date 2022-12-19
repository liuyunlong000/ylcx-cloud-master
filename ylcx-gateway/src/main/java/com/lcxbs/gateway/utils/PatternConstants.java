/**
 * FileName: PatternConstants
 * Author:   Fang Tao Tao
 * Date:     2019/3/26 14:20
 * Description: z正则
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lcxbs.gateway.utils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈z正则〉
 *
 * @author Fang Tao Tao
 * @create 2019/3/26
 * @since 1.0.0
 */
public class PatternConstants {

    /**
     * 正则表达式：验证字母
     */
    public static final String REGEX_LETTER = "[^a-zA-Z]";

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,6}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 圆角校验
     */
    public static final String FULL_SHAPE = "[\\uFF00-\\uFFFF]";

    /**
     * 数字校验
     */
    public static final String []NUMBER_LEFFER_ARR = {"0E-31","0E-15","0E-16"};

    /**
     * 字母与数字
     */
    public static final String LETTER_CHIESE = "[[\\u4E00-\\u9FA0]a-zA-Z]";

    /**
     * 数字校验
     */
    public static final String NUMBER = "(-|\\+|)[0-9]+(\\.|)[0-9]*";

    /**
     * 匹配空格 回车 换行
     */
    public static final String BLANK_ENTRY_BR = "\\s*|\t|\n|\n";

    /**
     * 去右
     */
    public static final String BLANK_ENTRY_BR_R = "\\s*|\r|\n";
    /**
     * 去左
     */
    public static final String BLANK_ENTRY_BR_L = "\\t|\r|\n";

    /**
     * 去右去左
     */
    public static final String BLANK_ENTRY_BRS_LR = "\\t|\r|\n";

    /**
     * 解析fact公式用到的
     */
    public static final String FACT_FORMULAR = "\\(.*?\\)";


    /**
     * 去除浏览器header.agent
     */
    public static final String BROSWER_ANGET = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

    /**
     * 公司名称(带有括号的也可以)
     */
    public static final String COMAPNY_NAME = "^[\\(\\)\\（\\）\\u4E00-\\u9FA5]+$";
    /**
     * 图片地址(比如公司logo)
     */
    public static final String PHOTO_REG = "http://.*(.jpg|.png|.gif|.jpeg|.bmp)$";

    /**
     * 图片
     */
    public static final String PHOTO = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG)$";

    /**
     * mp3
     */
    public static final String MP3 = ".+(.MP3|.mp3)$";

    /**
     *修改密码规则
     */
    public static final String UPDATE_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
}