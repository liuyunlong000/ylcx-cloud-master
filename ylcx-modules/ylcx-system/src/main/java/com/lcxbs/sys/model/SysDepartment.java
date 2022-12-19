package com.lcxbs.sys.model;
import com.lcxbs.core.ITree;
import com.lcxbs.sys.entity.SysDepartmentEntity;
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
 * <p>Title: SysDepartment.java</p>
 * <p>Description:部门信息实体扩展</p>
 * <p>Copyright: Copyright (c) 2022</p>
 * @author Administrator
 * @date 2022-2-26 22:10:30
 * @version V1.0
 */
@ApiModel("部门信息")
public class SysDepartment extends SysDepartmentEntity implements ITree {

		                                                   
	//region Get和Set方法
    @Override
    @NotNull(groups = {UpdateGroup.class},message = "主键ID不能空")
    @ApiModelProperty(value = "主键ID",hidden=false,required=true,example = "")
    public Long getNid(){return super.getNid();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "部门编码长度不能超过 {max}")
    @ApiModelProperty(value = "部门编码",hidden=false,required=false,example = "")
    public String getDeptId(){return super.getDeptId();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "部门父编码长度不能超过 {max}")
    @ApiModelProperty(value = "部门父编码",hidden=false,required=false,example = "")
    public String getParentId(){return super.getParentId();}
    
    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "部门名称长度不能超过 {max}")
    @ApiModelProperty(value = "部门名称",hidden=false,required=false,example = "")
    public String getDeptName(){return super.getDeptName();}
    
    @Override
    @Length(max = 100,groups ={Default.class,UpdateGroup.class}, message = "部门简称长度不能超过 {max}")
    @ApiModelProperty(value = "部门简称",hidden=false,required=false,example = "")
    public String getShortName(){return super.getShortName();}
    
    @Override
    @Length(max = 200,groups ={Default.class,UpdateGroup.class}, message = "部门负责人长度不能超过 {max}")
    @ApiModelProperty(value = "部门负责人",hidden=false,required=false,example = "")
    public String getDeptPrincipal(){return super.getDeptPrincipal();}
    
    @Override
    @Length(max = 255,groups ={Default.class,UpdateGroup.class}, message = "查询字符串长度不能超过 {max}")
    @ApiModelProperty(value = "查询字符串",hidden=false,required=false,example = "")
    public String getSearchStr(){return super.getSearchStr();}
    
    @Override
    @Length(max = 500,groups ={Default.class,UpdateGroup.class}, message = "描述长度不能超过 {max}")
    @ApiModelProperty(value = "描述",hidden=false,required=false,example = "")
    public String getRemark(){return super.getRemark();}
    
    @Override
    @Range(min = 0,max = 9223372036854775806L,groups ={Default.class,UpdateGroup.class}, message = "等级应该在{min}-{max}之间")
    @ApiModelProperty(value = "等级",hidden=false,required=false,example = "")
    public Long getLevelNum(){return super.getLevelNum();}
    
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
    public SysDepartment(){super();}
    /**带主键构造函数*/
    public SysDepartment(Long id){super(id);}
	//endregion

    private List children;

    @Override
    public String getTreeId() {
        return this.getDeptId();
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
