package com.lcxbs.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Request请求参数处理类
 *
 * @author ahanflw
 *
 */
public class RequestUtils {

    /**
     * 默认编码
     */
    public static String charset = "UTF-8";

    /**
     * 编码字符串
     *
     * @param src 字符串
     * @return
     */
    public static String escapeStr(String src) {
        String ret = "";
        if (src == null) {
            return ret;
        }
        for (int i = src.length() - 1; i >= 0; --i) {
            int iCh = src.substring(i, i + 1).hashCode();
            if (iCh == 10) {
                iCh = 15;
            } else if (iCh == 13) {
                iCh = 16;
            } else if (iCh == 32) {
                iCh = 17;
            } else if (iCh == 9) {
                iCh = 18;
            } else {
                iCh += 5;
            }
            ret = ret + (char) iCh;
        }
        return ret;
    }

    /**
     * 获取对象
     *
     * @param request
     * @param name 对象名称
     * @param def 如果不存在，默认返回值
     * @return
     */
    public static Object getAttribute(HttpServletRequest request, String name, Object def) {
        Object value = getAttribute(request, name);
        if (value == null) {
            return def;
        }
        return value;
    }

    /**
     * 获取对象,如果不存在，则返回null
     *
     * @param request
     * @param name 对象名称
     * @return
     */
    public static Object getAttribute(HttpServletRequest request, String name) {
        if (name == null) {
            return null;
        }
        return request.getAttribute(name.trim());
    }

    /**
     * 获取值并转为Double
     *
     * @param request
     * @param name 对象名称
     * @param def 如果不存在，默认返回值
     * @return
     */
    public static Double getDoubleParameter(HttpServletRequest request, String name, Double def) {
        try {
            return getDoubleParameter(request, name);
        } catch (Exception e) {
        }
        return def;
    }

    /**
     * 获取值并转为Double
     *
     * @param request
     * @param name 对象名称
     * @return
     */
    public static Double getDoubleParameter(HttpServletRequest request, String name) {
        return Double.parseDouble(request.getParameter(name));
    }

    /**
     * 获取值并转为Float
     *
     * @param request
     * @param name 对象名称
     * @param def 如果不存在，默认返回值
     * @return
     */
    public static Float getFloatParameter(HttpServletRequest request, String name, Float def) {
        try {
            return getFloatParameter(request, name);
        } catch (Exception e) {
        }
        return def;
    }

    /**
     * 获取值并转为Float
     *
     * @param request
     * @param name 对象名称
     * @return
     */
    public static Float getFloatParameter(HttpServletRequest request, String name) {
        return Float.parseFloat(request.getParameter(name));
    }

    /**
     * 获取值并转为Integer
     *
     * @param request
     * @param name 对象名称
     * @param def 如果不存在，默认返回值
     * @return
     */
    public static Integer getIntegerParameter(HttpServletRequest request, String name, Integer def) {
        try {
            return getIntegerParameter(request, name);
        } catch (Exception e) {
        }
        return def;
    }

    /**
     * 获取值并转为Short
     *
     * @param request
     * @param name 对象名称
     * @param def 如果不存在，默认返回值
     * @return
     */
    public static Short getShortParameter(HttpServletRequest request, String name, Short def) {
        try {
            return getShortParameter(request, name);
        } catch (Exception e) {
        }
        return def;
    }

    /**
     * 获取值并转为Integer
     *
     * @param request
     * @param name 对象名称
     * @return
     */
    public static Integer getIntegerParameter(HttpServletRequest request, String name) {
        return Integer.parseInt(request.getParameter(name));
    }

    /**
     * 获取值并转为Short
     *
     * @param request
     * @param name 对象名称
     * @return
     */
    public static Short getShortParameter(HttpServletRequest request, String name) {
        return Short.parseShort(request.getParameter(name));
    }

    /**
     * 获取request里的指定参数名称的值，返回一个长整形数值
     *
     * @param request
     * @param name 参数名称
     * @param def 获取出错时，返回的值
     * @return
     */
    public static Long getLongParameter(HttpServletRequest request, String name, Long def) {
        try {
            return getLongParameter(request, name);
        } catch (Exception e) {
        }
        return def;
    }

    /**
     * 获取request里的指定参数名称的值，返回一个长整形数值
     *
     * @param request
     * @param name 参数名称
     * @return
     */
    public static Long getLongParameter(HttpServletRequest request, String name) {
        return Long.parseLong(request.getParameter(name));
    }

    /**
     * 如果参数中存在该名称的值，则将值放入Map中
     *
     * @param request
     * @param name 参数名称
     * @param params 值Map
     */
    public static void getParameter(HttpServletRequest request, String name, Map<String, Object> params) {
        String value = request.getParameter(name);
        if (value != null && value.length() > 0) {
            params.put(name, value);
        }
    }

    /**
     * 获取session里的指定参数名称的值
     *
     * @param request
     * @param id 参数名称
     * @param defaultObj 获取出错时，返回的值
     * @return
     */
    public static Object getSessionValue(HttpServletRequest request, String id, Object defaultObj) {
        if (getSessionValue(request, id) != null) {
            defaultObj = getSessionValue(request, id);
        }
        return defaultObj;
    }

    /**
     * 获取session里的指定参数名称的值
     *
     * @param request
     * @param id 参数名称
     * @return
     */
    public static Object getSessionValue(HttpServletRequest request, String id) {
        HttpSession session = request.getSession(true);
        return session.getAttribute(id);
    }

    /**
     * 获取attribute里的指定参数名称的值
     *
     * @param request
     * @param name 参数名称
     * @param def 没有找到时默认返回值
     * @return
     */
    public static String getStringAttribute(HttpServletRequest request, String name, String def) {
        String value = getStringAttribute(request, name);
        if (value == null) {
            return def;
        }
        return value;
    }

    /**
     * 获取attribute里的指定参数名称的值
     *
     * @param request
     * @param name 参数名称
     * @return
     */
    public static String getStringAttribute(HttpServletRequest request, String name) {
        if (name == null) {
            return null;
        }
        return ((String) request.getAttribute(name.trim()));
    }

    /**
     * 获取attribute里的指定参数名称的值
     *
     * @param request
     * @param name 参数名称
     * @param def 出错时返回值
     * @return
     */
    public static Integer getIntegerAttribute(HttpServletRequest request, String name, Integer def) {
        String value = getStringAttribute(request, name);
        if (value == null || value.equals("") || !IntegerPro.isInteger(value)) {
            return def;
        }
        return Integer.parseInt(value);
    }

    /**
     * 获取attribute里的指定参数名称的值
     *
     * @param request
     * @param name 参数名称
     * @param def 出错时返回值
     * @return
     */
    public static Short getShortAttribute(HttpServletRequest request, String name, Short def) {
        String value = getStringAttribute(request, name);
        if (value == null || value.equals("") || !IntegerPro.isInteger(value)) {
            return def;
        }
        return Short.parseShort(value);
    }

    /**
     * 获取attribute里的指定参数名称的值
     *
     * @param request
     * @param name 参数名称
     * @return
     */
    public static Integer getIntegerAttribute(HttpServletRequest request, String name) {
        if (name == null) {
            return 0;
        }
        String value = getStringAttribute(request, name);
        if (value == null || value.equals("") || !IntegerPro.isInteger(value)) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    /**
     * 获取request里的指定参数名称的值
     *
     * @param request
     * @param name 参数名称
     * @param def 出错时返回值
     * @return
     */
    public static String getStringParameter(HttpServletRequest request, String name, String def) {
        String value = getStringParameter(request, name);
        if (value == null) {
            return def;
        }
        return value;
    }

    /**
     * 获取request里的指定参数名称的值
     *
     * @param request
     * @param name 参数名称
     * @return
     */
    public static String getStringParameter(HttpServletRequest request, String name) {
        if (name == null) {
            return null;
        }
        return request.getParameter(name.trim());
    }

    /**
     * 获取request里的指定参数名称的值并解码
     *
     * @param request
     * @param name 参数名称
     * @return
     */
    public static String getUnEscapeParameter(HttpServletRequest request, String name) {
        String value = getStringParameter(request, name);
        if (value == null) {
            return "";
        }
        return unEscapeStr(value);
    }

    /**
     * 获取request里的指定参数名称的值并解码
     *
     * @param request
     * @param name 参数名称
     * @return
     */
    public static String getUrlDecodeParameter(HttpServletRequest request, String name) {
        String value = getStringParameter(request, name);
        if (value == null) {
            return "";
        }
        return decodeUrl(value, charset);
    }

    /**
     * 获取request里的指定参数名称的值并解码
     *
     * @param request
     * @param name 参数名称
     * @param def 出错时返回值
     * @return
     */
    public static String getUrlDecodeParameter(HttpServletRequest request, String name, String def) {
        String value = getStringParameter(request, name);
        if (value == null) {
            return def;
        }
        return decodeUrl(value, charset);
    }

    /**
     * 获取request里的指定参数名称的ISO-8859-1编码值
     *
     * @param request
     * @param name 参数名称
     * @param def 出错时返回值
     * @return
     */
    public static String getISO8859Parameter(HttpServletRequest request, String name, String def) {
        String value = getStringParameter(request, name);
        if (value == null) {
            return def;
        }
        try {
            value = new String(value.getBytes("ISO-8859-1"), charset);
        } catch (UnsupportedEncodingException e) {
        }
        return value;
    }

    /**
     * 判断是否为文件上传
     *
     * @param request
     * @return
     */
    public static Boolean isFileUpload(HttpServletRequest request) {
        Boolean isUpload;
        if ((request.getContentType() != null) && (request.getContentType().indexOf("multipart/form-data;") >= 0)) {
            isUpload = true;
        } else {
            isUpload = false;
        }
        return isUpload;
    }

    @SuppressWarnings("unchecked")
    public static void logRequest(HttpServletRequest request) {
        Enumeration headers = request.getHeaderNames();
        String reqName;
        @SuppressWarnings("unused")
        String reqValue;
        while (headers.hasMoreElements()) {
            reqName = (String) headers.nextElement();
            reqValue = request.getParameter(reqName);
        }
        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            reqName = (String) names.nextElement();
            reqValue = request.getParameter(reqName);
        }
    }

    /**
     * 从session中移出值
     *
     * @param request
     * @param id session名称
     */
    public static void removeSessionValue(HttpServletRequest request, String id) {
        HttpSession session = request.getSession(true);
        Object value = session.getAttribute(id);
        if (value != null) {
            session.removeAttribute(id);
        }
    }

    /**
     * 存放session值
     *
     * @param req
     * @param id session名称
     * @param value session值
     */
    public static void setSessionValue(HttpServletRequest req, String id, Object value) {
        HttpSession session = req.getSession(true);
        session.setAttribute(id, value);
    }

    /**
     * 编码URL
     *
     * @param src URL地址
     * @param charset 编码，如:utf-8
     * @return
     */
    public static String decodeUrl(String src, String charset) {
        try {
            return URLDecoder.decode(src, charset);
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    /**
     * 解码字符串
     *
     * @param src 字符串
     * @return
     */
    public static String unEscapeStr(String src) {
        String ret = "";
        if (src == null) {
            return ret;
        }
        for (int i = src.length() - 1; i >= 0; --i) {
            int iCh = src.substring(i, i + 1).hashCode();
            if (iCh == 15) {
                iCh = 10;
            } else if (iCh == 16) {
                iCh = 13;
            } else if (iCh == 17) {
                iCh = 32;
            } else if (iCh == 18) {
                iCh = 9;
            } else {
                iCh -= 5;
            }
            ret = ret + (char) iCh;
        }
        return ret;
    }

    /**
     * 判断是否为手机浏览器发送来得请求
     * @param request
     * @return 
     */
    public static boolean JudgeIsMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = {"iphone", "android", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
            "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
            "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
            "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
            "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
            "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
            "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
            "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
            "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
            "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
            "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
            "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
            "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tsm-", "upg1", "upsi", "vk-v",
            "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
            "Googlebot-Mobile"};
        if (request.getHeader("User-Agent") != null) {
            for (String mobileAgent : mobileAgents) {
                if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }
}
