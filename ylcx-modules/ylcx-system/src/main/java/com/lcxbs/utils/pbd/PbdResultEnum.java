package com.lcxbs.utils.pbd;
/**
 * <一句话功能简述>
 * @author fanlianwei
 * @create 2021/11/08
 * @since 1.0.0
 */
public enum PbdResultEnum {
    /**
     * 此参数为样例参数，后续有新参数时可以进行替换
     */
    UNIQUE_VALUE(101001, "编号重复！"),
    /**
     * 参数不存在
     */
    NULL_VALUE(101003, "查询结果为空！"),

    /**
     * 操作失败
     */
    BAT_OPERATION(101004, "操作失败！"),

    /**
     * 参数错误
     */
    PARAMS_ERROR(101005, "参数错误"),

    ;



    /**
     * 类型
     */
    private Integer code;
    /**
     * 名称
     */
    private String name;

    private PbdResultEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName(Integer type) {
        for (PbdResultEnum c : PbdResultEnum.values()) {
            if (c.getCode().equals(type)) {
                return c.name;
            }
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }
}