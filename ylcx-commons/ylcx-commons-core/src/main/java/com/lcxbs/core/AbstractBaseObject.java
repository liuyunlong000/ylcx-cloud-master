package com.lcxbs.core;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiModel("基础对象")
public abstract class AbstractBaseObject implements IBaseObject {

    private static final long serialVersionUID = -2158297429595677511L;

    protected static Logger logger =null;

    public AbstractBaseObject() {
        logger = LoggerFactory.getLogger(this.getClass());
    }


    @ApiModelProperty(value = "当前页码，默认值：1",required = true,example = "1")
    private Integer pageNum=1;

    @ApiModelProperty(value = "每页记录数,默认值：30",required = true,example = "30")
    private Integer pageSize=30;

    @ApiModelProperty("排序字段属性,多个以英文逗号分隔")
    private String sortField;

    @ApiModelProperty(value = "排序方式[asc|desc|ASC|DESC]",example = "ASC")
    private String sortOrder;

    @ApiModelProperty("分组字段属性,多个以英文逗号分隔")
    private String groupField;

    /** 扩展map属性集合 **/
    @ApiModelProperty(hidden = true)
    private final Map<String,Object> map=new HashMap<String, Object>();

    /**扩展list属性集合**/
    @ApiModelProperty(hidden = true)
    private final List list=new ArrayList();

    /**
     * 扩展List属性集合
     * @return
     */
    public List getList() {
        return list;
    }

    /**
     * 扩展Map属性
     * @return
     */
    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 当前页码，默认值：1
     * @return
     */
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 每页记录数,默认值：30
     * @return
     */
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 排序字段属性,多个以英文逗号分隔
     * @return
     */
    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    /**
     * 排序方式[asc|desc|ASC|DESC],默认值：ASC
     * @return
     */
    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder.toUpperCase();
    }

    /**
     * 分组字段属性,多个以英文逗号分隔
     * @return
     */
    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }

    /**
     * 按照 sortField()和 sortOrder属性生成order by，例如：order by id desc
     * @see AbstractBaseObject#getSortField()
     * @see AbstractBaseObject#getSortOrder()
     * @return
     */
    @JsonIgnore
    @JSONField(serialize=false)
    public String getOrderBy() {
        if (this.sortField!=null&&this.sortField.length() > 0) {
            return " ORDER BY " + this.sortField + " " + this.getSortOrderStr();
        }
        return null;
    }

    /**
     * 按照 groupField 属性生成group by语句，例如：group by id,name
     * @see AbstractBaseObject#getGroupField(()
     * @return
     */
    @JsonIgnore
    @JSONField(serialize=false)
    public String getGroupBy() {
        if (this.groupField!=null&&this.groupField.length() > 0) {
            return " GROUP BY " + this.groupField + " ";
        }
        return null;
    }

    /**
     * 按照给定的数据库字段属性生成group by语句，例如：group by id,name
     * @param dbField 数据库字段名称（多个字段以英文逗号分隔）
     * @return
     */
    public String getGroupBy(String dbField) {
        if (dbField!=null&&dbField.length() > 0) {
            return " GROUP BY " + dbField + " ";
        }
        return null;
    }

    /**
     * 按照给定的数据库字段属性和 sortOrder 属性生成order by语句，例如：order by id desc
     * @see AbstractBaseObject#getSortOrder()
     * @param dbField 数据库字段名称（多个字段以英文逗号分隔）
     * @return
     */
    public String getOrderBy(String dbField) {
        if (dbField!=null&&dbField.length() > 0) {
            return " ORDER BY " + dbField + " " + this.getSortOrderStr();
        }
        return null;
    }

    private String getSortOrderStr(){
        if(this.sortOrder!=null&&(this.sortOrder.equalsIgnoreCase("DESC")||this.sortOrder.equalsIgnoreCase("descending"))){
            return "DESC";
        }
        return "ASC";
    }
}
