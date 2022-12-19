package com.lcxbs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.web.multipart.MultipartFile;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈json工具类〉
 * @since 1.0.0
 */
public class JsonUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *日志
     */
    public final static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JsonUtil.class);

    public static  ObjectMapper mapper = null;

    static {
        mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    /**
     * 生成JSON
     * @param object
     */
    public static String generateJson(Object object) {
        try {
            if (null == object) {
                return "{}";
            }
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("JsonProcessingException \n" + e);
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error("IOException \n" + e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取本地文件中JSON字
     * @param json
     * @param classs
     * @return
     * @throws Exception
     */
    public static Object conventString2BeanByJackson(String json, Class<?> classs) throws Exception {
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        return mapper.readValue(json, classs);
    }

    /**
     * 做导出报告接口时加入的方法
     * @param json
     * @param classs
     * @return
     * @author DING WEI
     * @date 2020/4/20 9:58
     * @version 1.0
     */
    public static Object conventString2BeanByFastJson(String json,Class<?> classs){
        try {
            return JSON.parseObject(json, classs, Feature.OrderedField);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("fastJson转对象出错了\n"+e);
        }
        return null;
    }

    public static Object conventString2Bean(String json, Class<?> classs){
        try {
            LinkedHashMap<String, Object> jsonMap = JSON.parseObject(json, LinkedHashMap.class, Feature.OrderedField);
            JSONObject jsonObject = new JSONObject(true);
            jsonObject.putAll(jsonMap);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonObject.toJSONString(), classs);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("fastJson转对象出错了\n"+e);
        }
        return null;
    }

    /**
     * 对象转json
     * @param obj
     * @return
     */
    @Deprecated
    public static String object2json(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean
                || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal
                || obj instanceof BigInteger || obj instanceof Byte) {
            json.append("\"").append(string2json(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(array2json((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(list2json((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(map2json((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(set2json((Set<?>) obj));
        } else if (obj instanceof Character) {
        } else {
            json.append(bean2json(obj));
        }
        return json.toString();
    }

    /**
     * 对象转json
     * @param bean
     * @return
     */
    public static String bean2json(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
        }
        if (props != null) {
            for (int i = 0,len = props.length; i < len; i++) {
                String name = object2json(props[i].getName());
                String value = "";
                try {
                    value = object2json(props[i].getReadMethod().invoke(bean));
                    if (name.equals("\"attributes\"") && value.contains("attr")) {
                        value = value.replace("\\", "");
                    }
                    if (!value.equals("\"\"") && null != value) {
                        json.append(name);
                        json.append(":");
                        json.append(value);
                        json.append(",");
                    } else {
                    }
                } catch (Exception e) {
                    LOGGER.info("==>" + name + "==>" + value);
                }
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    /**
     * list转json
     * @param list
     * @return
     */
    public static String list2json(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    /**
     * json2M
     * @param json
     * @return
     */
    public static Map<?, ?> json2Map(String json) {
        try {
            return mapper.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("JSON【" + json + "】转Map出错了：" + e);
        }
        return null;
    }

    /**
     * 数组转json
     * @param array
     * @return
     */
    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            for (Object key : map.keySet()) {
                json.append(object2json(key));
                json.append(":");
                json.append(object2json(map.get(key)));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    public static String set2json(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    public static String string2json(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                /*
                 * case '/': sb.append("\\/"); break;
                 */
                default:
                    if (ch >= '\u0000' && ch <= '\u001F') {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }

    /**
     * 转换成bean
     * @param o
     * @param c
     * @return ${return_type}
     * @throws
     * @author FANG TAO TAO
     * @date 2018/9/28 11:33
     */
    public static Object toBean(Object o,Class c){
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(o);
            return mapper.readValue(jsonStr, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
        return JSON.parseObject(jsonString,beanCalss);
    }

    /**
     * 将对象转换成json字符串
     * 此方法不会去除字符串中的空格
     * 传什么保存什么
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        if(data == null){
            return null;
        }
        if(data instanceof String){
            return (String) data;
        }
        if(data instanceof MultipartFile){
            return((MultipartFile) data).getOriginalFilename();
        }
        try {
            String string = mapper.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当json是[{},{}...]可以转成List<Map<String,Object>>
     * @param jsonString
     * @param beanCalss
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String jsonString, Class<T> beanCalss) {
        try {
            return mapper.readValue(jsonString,beanCalss);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}