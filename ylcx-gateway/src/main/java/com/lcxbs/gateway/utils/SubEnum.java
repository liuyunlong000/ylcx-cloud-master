/**
 * FileName: SubEnum
 * Author:   Fang Tao Tao
 * Date:     2019/3/26 14:18
 * Description: 数据主体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lcxbs.gateway.utils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈数据主体〉
 *
 * @author Fang Tao Tao
 * @create 2019/3/26
 * @since 1.0.0
 */
public enum SubEnum {
    /**
     * 时点表的主题
     */
    POINT("itempoint","0001"),
    /**
     * 时期表的主题
     */
    PERIOD("itemperiod","0002"),
    /**
     * 科目余额表的主题
     */
    ACCOUNT("account","0003"),
    /**
     * 损益科目表的主题
     */
    SY("account","0004"),
    /**
     * 成本费用收入表的主题
     */
    COST("profitandLoss","0005"),

    /**
     * 指标表的主题
     */
    INDICATOR("indicator","1016"),
    /**
     * 上半年
     */
    ONE_HALF_YEAR("上半年","H1"),
    /**
     * 下半年
     */
    TWO_HALF_YEAR("下半年","H2"),
    /**
     * 季度
     */
    ONE_QU_YEAR("一季度","Q1"),
    /**
     * 季度
     */
    TWO_QU_YEAR("二季度","Q2"),
    /**
     * 季度
     */
    THREE_QU_YEAR("三季度","Q3"),
    /**
     * 季度
     */
    FOURTH_QU_YEAR("四季度","Q4"),
    /**
     * 年度
     */
    Y("年度","00"),

    /**
     * H
     */
    Q("Q","Q"),
    /**
     * H
     */
    H("H","H");

    /**
     * 名称
     * */
    private String name;

    /**
     * 类型
     * */
    private String code;

    private SubEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 静态获取name方法
     * */
    public static String getName(String code) {
        for (SubEnum c : SubEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}