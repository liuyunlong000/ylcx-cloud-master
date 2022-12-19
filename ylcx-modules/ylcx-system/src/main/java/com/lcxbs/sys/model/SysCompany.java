package com.lcxbs.sys.model;
import com.lcxbs.core.ITree;
import com.lcxbs.sys.entity.SysCompanyEntity;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.lcxbs.validate.UpdateGroup;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Title: SysCompany.java</p>
 * <p>Description:公司信息实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author fanlianwei
 * @date 2022-3-7 11:01:46
 * @version V1.0
 */
@ApiModel("公司信息")
public class SysCompany extends SysCompanyEntity implements ITree {

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "自增ID不能空")
    @ApiModelProperty(value = "自增ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "公司ID长度不能超过 {max}")
    @ApiModelProperty(value = "公司ID",hidden=false,required=false,example = "")
    public String getCmpyId(){return super.getCmpyId();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "上级公司ID长度不能超过 {max}")
    @ApiModelProperty(value = "上级公司ID",hidden=false,required=false,example = "")
    public String getParentId(){return super.getParentId();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "公司简称长度不能超过 {max}")
    @ApiModelProperty(value = "公司简称",hidden=false,required=false,example = "")
    public String getCmpyName(){return super.getCmpyName();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "公司全称长度不能超过 {max}")
    @ApiModelProperty(value = "公司全称",hidden=false,required=false,example = "")
    public String getCmpyLongName(){return super.getCmpyLongName();}
    
    @Override
    @Range(min = 0,max = 99999999999L,groups ={Default.class,UpdateGroup.class}, message = "公司类型应该在{min}-{max}之间")
    @ApiModelProperty(value = "公司类型",hidden=false,required=false,example = "")
    public Long getCmpyType(){return super.getCmpyType();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "公司地址长度不能超过 {max}")
    @ApiModelProperty(value = "公司地址",hidden=false,required=false,example = "")
    public String getCmpyAddress(){return super.getCmpyAddress();}
    
    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "所属行政区划长度不能超过 {max}")
    @ApiModelProperty(value = "所属行政区划",hidden=false,required=false,example = "")
    public String getZoneCode(){return super.getZoneCode();}
    
    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "公司描述长度不能超过 {max}")
    @ApiModelProperty(value = "公司描述",hidden=false,required=false,example = "")
    public String getCmpyDescription(){return super.getCmpyDescription();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否已初始化;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否已初始化;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getInitFlag(){return super.getInitFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否默认公司;字典：1是，0否应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否默认公司;字典：1是，0否",hidden=false,required=false,example = "")
    public Long getDefaultFlag(){return super.getDefaultFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "排序编号应该在{min}-{max}之间")
    @ApiModelProperty(value = "排序编号",hidden=false,required=false,example = "")
    public Long getSortNum(){return super.getSortNum();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否启用标识;字典：1启用，0禁用应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否启用标识;字典：1启用，0禁用",hidden=false,required=false,example = "")
    public Long getDisableFlag(){return super.getDisableFlag();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "是否删除标识;字典：1删除，0未删除应该在{min}-{max}之间")
    @ApiModelProperty(value = "是否删除标识;字典：1删除，0未删除",hidden=false,required=false,example = "")
    public Long getDeleteFlag(){return super.getDeleteFlag();}
    
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
    public SysCompany(){super();}
    /**带主键构造函数*/
    public SysCompany(Long id){super(id);}
	//endregion

    private List<SysCompany> children;

    @Override
    public String getTreeId() {
        return this.getCmpyId();
    }

    @Override
    public String getParentTreeId() {
        return this.getParentId();
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
