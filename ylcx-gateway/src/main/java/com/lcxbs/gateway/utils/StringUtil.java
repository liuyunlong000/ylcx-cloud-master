/**
 * FileName: StringUtil
 * Author:   Fang Tao Tao
 * Date:     2019/3/26 14:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lcxbs.gateway.utils;

import com.lcxbs.utils.UUIDGenerator;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Fang Tao Tao
 * @create 2019/3/26
 * @since 1.0.0
 */
public class StringUtil {
    /**
     * 日志类
     */
    private final static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StringUtil.class);


    /**
     * 上 下半年
     */
    //public static final String[] S_X = {"上", "下"};

    /**
     *
     */
    public static final String[] CHIESE_NUMBER_ARR = {"○", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};



    public static boolean isNotBlank(String string) {

        return !isBlank(string);
    }


    public static final String WINDOWS_PLATFORM = "windows";
    public static final String CLIENT_FALG = "Electron";
    public static final String MAC_OS = "MacOS";
    public static final String WIN = "WIN";

    /**
     * 获取device
     * @param agent
     * @return
     */
    public static String getPlatFormName(String agent){
        String name = WINDOWS_PLATFORM;
//        if(agent.contains(MAC_OS)){
//            name = MAC_OS;
//        }
        if(agent.contains(CLIENT_FALG)){
            String client = "@client";
            // 客户端
            //if(agent.contains(MAC_OS)){
            // name = name+client;
            // }else {
            //  name = name+client;
            //}
            return "client";
        }
        return name;
    }



    /**
     * 获取设备号
     * @param headerDevice
     * @param paramerDevice
     * @param agent
     * @return
     */
    public static String getDeviceCode(String headerDevice,String paramerDevice,String agent){
        String device =  agent;
        if(!StringUtil.isBlank(headerDevice)){
            device = headerDevice;
        }else if(!StringUtil.isBlank(paramerDevice)){
            device = paramerDevice;
        }
//        device = System.currentTimeMillis()+device;
        /**同时间多个用户登录造成设备号相同问题
         * @author Mei Shen Bo
         * 2021/1/12 15:18
         **/
        device = UUIDGenerator.generate()+device;
        return device;
    }

    /**
     * 获取版本号
     * @param version
     * @return
     */
    public static String getDeviceVersion(String version){
        return version == null ? "***" : version;
    }

    /**
     * 获取26个字母
     *
     * @return
     */
    public static String[] get26Letter() {
        char a = 'A';
        String str[] = new String[26];
        for (int i = 0; i < 26; i++) {
            str[i] = a + "";
            a++;
        }
        return str;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (null != str) {
            str = replaceBlank(str);
        }
        return str == null || str.length() == 0;
    }

    /**
     * 数据转字符串[aa,33,4]==>aa,33,4
     * @param arrs
     * @param split
     * @return
     */
    public static String arrayToDelimitedString(String arrs[], String split) {
        StringBuffer sf = new StringBuffer();
        int len = arrs.length;
        for (int i = 0; i < len; i++) {
            String str = arrs[i];
            sf.append(str).append(split);
            if (i != len - 1) {
                sf.append(split);
            }
        }
        return sf.toString();
    }
    /**
     * 字符转成UTF-8编码
     * @param text (%)
     * @return
     */
    public static String convertUTF8Charcter(String text) {
        String p = "", str = text;
        if (str.indexOf("(%)") != -1) {
            int len = text.length();
            str = text.substring(0, len - 3);
            p = text.substring(len - 3, len);
        }
        try {
            if (StringUtil.isBlank(str)) {
                str = "";
            }
            str = java.net.URLDecoder.decode(str, "UTF-8");
            return str + p;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取字符串中的所有数字
     * @param string
     * @return
     * @Author 龚佳新
     * @Time 2014年3月23日上午11:18:29
     */
    public static String getAllNumber(String string) {
        Pattern p = Pattern.compile(PatternConstants.REGEX_CHINESE);
        Matcher m = p.matcher(string);
        return m.replaceAll("").trim();
    }

    /**
     * 获取字符串中的所有字符
     *
     * @param string
     * @return
     * @Author 龚佳新
     * @Time 2014年3月23日上午11:18:29
     */
    public static String getAllLetter(String string) {
        Pattern p = Pattern.compile(PatternConstants.REGEX_LETTER);
        Matcher m = p.matcher(string);
        return m.replaceAll("").trim();
    }

    /**
     * 路径去空格
     * @param path
     * @return
     */
    public static String pathRemoveBlank(String path) {
        path = path.replaceAll("%20", " ");
        return path;
    }




    /**
     * 字母转数字
     *
     * @param column
     * @return
     */
    public static int convertLetterToNum(String column) {
        if (column.length() > 1) {
            return 0;
        }
        for (int i = 0; i < 256; i++) {
            String columnName = convertNumToColumnName(i);
            if (columnName.equalsIgnoreCase(column)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 数字转字母
     *
     * @param column
     * @return
     */
    public static String convertNumToLetter(int column) {
        column = column - 1;
        String result = "";
        for (; column >= 0; column = column / 26 - 1) {
            result = (char) ((char) (column % 26) + 'A') + result;
        }

        return result;
    }

    /**
     * 数字转列名称
     *
     * @param column
     * @return
     */
    public static String convertNumToColumnName(int column) {
        String result = "";
        for (; column >= 0; column = column / 26 - 1) {
            result = (char) ((char) (column % 26) + 'A') + result;
        }

        return result;
    }

    public static String parseNumber(String str) {
        if (str == null || str.equals("") || str.equals("0E-31") || str.equals("0E-15") || str.equals("0E-16")
                || str.equals("N/A")) {
            return "0";
        }
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if (Character.isDigit(x) == true || x == '.' || x == '+' || x == '-') {
                result += x;
            }
        }
        return result;
    }

    /**
     * 判断是否有百分符号
     *
     * @param str
     * @return
     */
    public static boolean whetherContainsPercent(String str) {
        return str.indexOf("%") != -1;
    }

    /**
     * 判断两个字符串中有,除exclude字符外count+1个相等的字符就定义为这两个字符相似
     * @param caption
     * @param newCaption
     * @return
     */
    public static boolean getLikeFrom2Str(String caption, String newCaption, String[] exclude, int count) {
        String old = caption, news = newCaption;
        for (String string : exclude) {
            old = old.replace(string, "");
            news = news.replace(string, "");
        }
        int like = getLevenshteinDistance(old, news);
        if (like < news.length() - count) {
            LOGGER.info("【" + newCaption + "】中有【" + caption + "】,相似度为【" + like + "】");
            return true;
        } else {
            LOGGER.info("【" + newCaption + "】中没有【" + caption + "】,相似度为【" + like + "】");
            return false;
        }
    }

    /**
     * @param s
     * @param t
     * @return 值越大越不相似
     */
    // 计算两个字符串的差异值
    public static int getLevenshteinDistance(CharSequence s, CharSequence t) {
        if (s == null || t == null) {
            // 容错，抛出的这个异常是表明在传参的时候，传递了一个不合法或不正确的参数。
            // 好像都这样用，illegal:非法。Argument:参数，证据。
            throw new IllegalArgumentException("Strings must not be null");
        }
        // 计算传入的两个字符串长度
        int n = s.length();
        int m = t.length();
        // 容错，直接返回结果。这个处理不错
        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }
        // 这一步是根据字符串长短处理，处理后t为长字符串，s为短字符串，方便后面处理
        if (n > m) {
            CharSequence tmp = s;
            s = t;
            t = tmp;
            n = m;
            m = t.length();
        }
        // 开辟一个字符数组，这个n是短字符串的长度
        int p[] = new int[n + 1];
        int d[] = new int[n + 1];
        // 用于交换p和d的数组
        int _d[];
        int i;
        int j;
        char t_j;
        int cost;
        // 赋初值
        for (i = 0; i <= n; i++) {
            p[i] = i;
        }
        for (j = 1; j <= m; j++) {
            // t是字符串长的那个字符
            t_j = t.charAt(j - 1);
            d[0] = j;
            for (i = 1; i <= n; i++) {
                // 计算两个字符是否一样，一样返回0。
                cost = s.charAt(i - 1) == t_j ? 0 : 1;
                // 可以将d的字符数组全部赋值。
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }
            // 交换p和d
            _d = p;
            p = d;
            d = _d;
        }
        // 最后的一个值即为差异值
        return p[n];
    }

    /**
     * 用来统计某个单词出现的个数
     *
     * @param source
     * @param word
     * @return
     */
    public static int countWord(String source, String word) {
        char[] word_arr = source.toCharArray();
        int word_num = 0; // 用来统计单词出现的次数
        char[] char_arr = word.toCharArray();
        for (int i = 0; i < word_arr.length; i++) {
            if (char_arr[0] == word_arr[i] && i + char_arr.length <= word_arr.length) {
                int m = i + 1;
                boolean flag = true;
                for (int j = 1; j < char_arr.length; ) {
                    if (word_arr[j++] != char_arr[m++]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    word_num++;
                }
            }
        }
        return word_num;
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        }else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        }else{
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 去除所有空格 回车 换行等…………
     *
     * @param str
     * @return 回车 \n 换行 \r
     * @Author 龚佳新
     * @Time 2014年3月14日上午8:49:42
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile(PatternConstants.BLANK_ENTRY_BR);
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        dest = dest.replace("\\n", "").replace("\t", "");
        return dest;
    }

    public static String replaceRN(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile(PatternConstants.BLANK_ENTRY_BR_R);
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去除左边与右边的空格 与回车换行
     *
     * @param str
     * @return
     */
    public static String replaceLeftAndRightAndTabBlank(String str) {
        str = str.trim();
        str = str.substring(str.lastIndexOf(str.trim()));
        Pattern p = Pattern.compile(PatternConstants.BLANK_ENTRY_BR_L);
        Matcher m = p.matcher(str);
        str = m.replaceAll("");
        return str;
    }

    /**
     * 去掉空格
     * @param str
     * @return
     */
    public static String replace(String str) {// replaceLeftAndRightBlank
        str = str.trim();
        str = str.substring(str.lastIndexOf(str.trim()));
        Pattern p = Pattern.compile(PatternConstants.BLANK_ENTRY_BRS_LR);
        Matcher m = p.matcher(str);
        str = m.replaceAll("");
        return str;
    }


    /**
     * 功能同上，只不过此方法不是用正则实现的哦 亲！鉴于正则匹配会消耗一定的资源，故建议用此方法！
     *
     * @param formula
     * @return
     */
    public static String[] getFomularArrayNoReg(String formula) {
        formula = formula.substring(formula.indexOf("(") + 1, formula.indexOf(")"));
        return formula.replace("\"", "").split("\\,");
    }

    public static String[] getDistinctArray(String[] array) {
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<String>();
        for (String s : array) {
            if (set.add(s.toLowerCase())) {
                list.add(s);
            }
        }
        return list.toArray(new String[list.size()]);
    }


    /**
     * 分割成数字数组 String str = "13+41/44*49-31" [13,41,44,49,31]
     */
    public static String[] getNumberArr(String str) {
        return str.split("\\p{Punct}");
    }

    /**
     * 分割出符号数组 negative * String str = "13+41/44*49-31" [+,/,*,-]
     */
    public static String[] getSym(String str) {
        return str.split("\\p{Digit}");
    }

    /**
     * 字符串数组 转为 字符串格式 已逗号分隔 方便 sql 语句
     *
     * @param strArr
     * @param prefix 需要添加的前缀
     * @param suffix 需要添加的后缀 需要在添加
     * @param tag    添加的标记
     * @return 字符串 已 逗号分隔
     **/
    public static String getArraytoSting(String[] strArr, String prefix, String suffix, String tag) {
        if (prefix == null) {
            prefix = "";
        }
        if (suffix == null) {
            suffix = "";
        }
        if (tag == null) {
            tag = "";
        }
        StringBuffer sf = new StringBuffer();
        if (strArr.length != 0) {
            for (int i = 0, len = strArr.length; i < len; i++) {
                sf.append(prefix + tag + strArr[i]);
                if (i != len - 1) {
                    sf.append(",");
                }
            }
        }
        return sf.toString();
    }


    /**
     * zhh
     *
     * @param str 需要操作的字符串
     * @return String 通过正则表达式 返回截取需要的字符串
     **/
    public static String splitStr(String str) {
        String regex = ".+\\/(.+)\\-";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(str);
        if (matcher.find()) {
            return matcher.group(matcher.groupCount());
        } else {
            LOGGER.info("no match...");
            return str.split("/")[1].split("-")[0];
        }
    }


    /**
     * 为字符增加单引号
     *
     * @param str
     * @return
     */
    public static String addSingleQuotes(String str) {
        str = "'" + str + "'";
        return str;
    }




    /**
     * 将年份或月份转化为大写
     *
     * @param value 转化的内容
     * @param type  转化的类型 0 年 1 月
     * @return
     */
    public static String convertInt2String(String value, int type) {
        String result = "";
        // 年
        if (type == 0) {
            for (int i = 0; i < value.length(); i++) {
                int index = Integer.valueOf(value.substring(i, i + 1));
                result += CHIESE_NUMBER_ARR[index];
            }
        }
        // 月
        else if (type == 1) {
            if (value.equals(SubEnum.Y.getCode())) {
            } else if (value.startsWith(SubEnum.H.getCode())) {
            } else if (value.startsWith(SubEnum.Q.getCode())) {
                int m = Integer.valueOf(value.substring(1));
                result = CHIESE_NUMBER_ARR[m];
            } else {
                int m = Integer.valueOf(value);
                result = CHIESE_NUMBER_ARR[m];
            }
            // } else if (type == 2) { // 季度
            // int m = Integer.valueOf(value);
            // result = s[m] + "季度";
        } else { // 其他
            result = value;
        }
        return result;
    }

    /**
     * 返回计算并转换后的字符串
     *
     * @param up    转换为大写
     * @param num   仅数字
     * @param month 月位置的期间
     * @param add   差值
     * @return
     */
    public static String calOnMonth(boolean up, boolean num, String month, int add) {
        if (month.equals(SubEnum.Y.getCode())) {
            if (!num) {
                month = SubEnum.Y.getName();
            } else {
                month = "";
            }
        } else if (month.matches("H\\d")) {
            int monthint = Integer.parseInt(month.substring(1));
            monthint += add;
            monthint %= 2;
            if (monthint <= 0) {
                monthint += 2;
            }
            if (monthint == 1) {
                month = SubEnum.ONE_HALF_YEAR.getName();
            } else if (monthint == 2) {
                month = SubEnum.TWO_HALF_YEAR.getName();
            }
        } else if (month.matches("Q\\d")) {
            int monthint = Integer.parseInt(month.substring(1));
            monthint += add;
            monthint %= 4;
            if (monthint <= 0) {
                monthint += 4;
            }
            month = monthint + "";
            if (up) {
                month = convertInt2String(month, 0);
            }
            if (!num) {
                month += "季度";
            }
        } else if (month.matches("[0|1][\\d]")) {
            int monthint = Integer.parseInt(month);
            monthint = monthint + add;
            monthint %= 12;
            if (monthint <= 0) {
                monthint += 12;
            }
            month = monthint + "";
            if (up) {
                month = convertInt2String(month, 1);
            }
            if (!num) {
                month = month + "月份";
            }
        }
        return month;
    }


    /**
     * 替换(仅适用替换[^/]&[\w])
     * @param str   原字符串
     * @param reg   正则字符串
     * @param tostr 替换字符串
     * @return
     */
    public static String repTitle(String str, String reg, String tostr) {
        StringBuffer bStr = new StringBuffer(str);
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            int s = m.start();
            int e = m.end();
            if (s != 0 && bStr.substring(s - 1, s).equals("/")) {
                continue;
            }
            bStr.replace(s, e, tostr);
        }
        return bStr.toString();
    }

    /**
     * 如果是null或空的返回传入的默认值
     *
     * @param src    检查字符串
     * @param defsrc 传入的默认字符串
     * @return
     */
    public static String getIfEmptyString(String src, String defsrc) {
        src = disposeString(src);
        if (src.equals("")) {
            src = defsrc;
        }
        return src;
    }

    /**
     * 获取去空格字符串处理null
     *
     * @param src
     * @return 返回去空格字符串 如果是null的返回""
     */
    public static String disposeString(String src) {
        if (src == null) {
            src = "";
        }
        return src.trim();
    }

    public static String addComs(String str) {
        String split = ",";
        if (!str.contains(split)) {return addCom(str);}
        String arr[] = str.split("\\"+split);
        StringBuffer sf = new StringBuffer();
        for (int i = 0, len = arr.length; i < len; i++) {
            sf.append(addCom(arr[i]));
            if (i != len - 1) {
                sf.append(",");
            }
        }
        return sf.toString();
    }


    public static String addCom(String str) {
        return "'" + str + "'";
    }


    /**
     *
     * @param set
     * @return
     */
    public static String setToString( Set<String> set ){
        StringBuffer sf = new StringBuffer();
        String[] ids = set.toArray(new String[set.size()]);
        for(String id:ids){
            sf.append("'").append(id).append("',");
        }
        sf.deleteCharAt(sf.length()-1);
        return sf.toString();
    }

}