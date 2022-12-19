package com.lcxbs.sys.model;
import com.lcxbs.core.ITree;
import com.lcxbs.sys.entity.SysCommonXzqhEntity;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.lcxbs.validate.UpdateGroup;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Title: SysCommonXzqh.java</p>
 * <p>Description:行政区划实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-3-7 11:15:33
 * @version V1.0
 */
@ApiModel("行政区划")
public class SysCommonXzqh extends SysCommonXzqhEntity implements ITree {

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增ID不能空")
    @ApiModelProperty(value = "自增ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "名称长度不能超过 {max}")
    @ApiModelProperty(value = "名称",hidden=false,required=false,example = "")
    public String getXzqhName(){return super.getXzqhName();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "编码长度不能超过 {max}")
    @ApiModelProperty(value = "编码",hidden=false,required=false,example = "")
    public String getXzqhCode(){return super.getXzqhCode();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "上级行政区划编码长度不能超过 {max}")
    @ApiModelProperty(value = "上级行政区划编码",hidden=false,required=false,example = "")
    public String getParentCode(){return super.getParentCode();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "拼音全拼长度不能超过 {max}")
    @ApiModelProperty(value = "拼音全拼",hidden=false,required=false,example = "")
    public String getFullSpell(){return super.getFullSpell();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "音拼简拼长度不能超过 {max}")
    @ApiModelProperty(value = "音拼简拼",hidden=false,required=false,example = "")
    public String getShortSpell(){return super.getShortSpell();}
    
    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "名称+编码+全拼+简拼长度不能超过 {max}")
    @ApiModelProperty(value = "名称+编码+全拼+简拼",hidden=false,required=false,example = "")
    public String getSearchString(){return super.getSearchString();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "默认经度应该在{min}-{max}之间")
    @ApiModelProperty(value = "默认经度",hidden=false,required=false,example = "")
    public Double getDefaultLongitude(){return super.getDefaultLongitude();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "默认纬度应该在{min}-{max}之间")
    @ApiModelProperty(value = "默认纬度",hidden=false,required=false,example = "")
    public Double getDefaultLatitude(){return super.getDefaultLatitude();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "默认缩放等级应该在{min}-{max}之间")
    @ApiModelProperty(value = "默认缩放等级",hidden=false,required=false,example = "")
    public Long getDefaultScale(){return super.getDefaultScale();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否启用标识;字典：1启用，0禁用应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否启用标识;字典：1启用，0禁用",hidden=false,required=false,example = "")
    public Long getDisableFlag(){return super.getDisableFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否删除标识;字典：1删除，0未删除应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否删除标识;字典：1删除，0未删除",hidden=false,required=false,example = "")
    public Long getDeleteFlag(){return super.getDeleteFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "排序编号应该在{min}-{max}之间")
    @ApiModelProperty(value = "排序编号",hidden=false,required=false,example = "")
    public Long getSortNum(){return super.getSortNum();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "乐观锁长度不能超过 {max}")
    @ApiModelProperty(value = "乐观锁",hidden=false,required=false,example = "")
    public String getRevision(){return super.getRevision();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "创建人长度不能超过 {max}")
    @ApiModelProperty(value = "创建人",hidden=false,required=false,example = "")
    public String getCreatedBy(){return super.getCreatedBy();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "创建时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "创建时间",hidden=false,required=false,example = "")
    public Long getCreatedTime(){return super.getCreatedTime();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "更新人长度不能超过 {max}")
    @ApiModelProperty(value = "更新人",hidden=false,required=false,example = "")
    public String getUpdatedBy(){return super.getUpdatedBy();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "更新时间应该在{min}-{max}之间")
    @ApiModelProperty(value = "更新时间",hidden=false,required=false,example = "")
    public Long getUpdatedTime(){return super.getUpdatedTime();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "租户号长度不能超过 {max}")
    @ApiModelProperty(value = "租户号",hidden=false,required=false,example = "")
    public String getTenantId(){return super.getTenantId();}
    
	//endregion
	
	//region 构造方法
	/**无参构造函数*/
    public SysCommonXzqh(){super();}
    /**带主键构造函数*/
    public SysCommonXzqh(Long id){super(id);}
	//endregion

    private List<SysCommonXzqh> children;

    @Override
    public String getTreeId() {
        return this.getXzqhCode();
    }

    @Override
    public String getParentTreeId() {
        return this.getParentCode();
    }

    @Override
    public List getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List children) {
        this.children=children;
    }
}
